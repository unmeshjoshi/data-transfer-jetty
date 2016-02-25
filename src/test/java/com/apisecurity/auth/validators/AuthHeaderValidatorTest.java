package com.apisecurity.auth.validators;


import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AuthHeaderValidatorTest {
    @Test
    public void shouldValidateAuthHeaderFormat() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Auth")).thenReturn("0dMJ2PnHPvV6tpMiJTfRUSII:0dMJ2PnHPvV6tpMiJTfRUSII+Mk=");

        AuthHeaderValidator validator = new AuthHeaderValidator();
        assertTrue(validator.isValid(request));
    }

    @Test
    public void shouldIndicateInvalidAuthHeaderFormat() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Auth")).thenReturn("==0dMJ2PnHPvV6tpMiJTfRUSII+Mk=");

        AuthHeaderValidator validator = new AuthHeaderValidator();
        assertFalse(validator.isValid(request));
    }

    @Test
    public void shouldReturnFalseIfNoKeyIdIsPresentInAuthHeader(){
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Auth")).thenReturn(":0dMJ2PnHPvV6tpMiJTfRUSII+Mk=");

        AuthHeaderValidator validator = new AuthHeaderValidator();
        assertFalse(validator.isValid(request));
    }

    @Test
    public void shouldReturnFalseIfNoHmacStringIsPresentInAuthHeader(){
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Auth")).thenReturn("==0dMJ2PnHPvV6tpMiJTfRUSII+Mk=:");

        AuthHeaderValidator validator = new AuthHeaderValidator();
        assertFalse(validator.isValid(request));
    }
}
