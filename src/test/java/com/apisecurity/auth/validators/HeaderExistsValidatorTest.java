package com.apisecurity.auth.validators;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class HeaderExistsValidatorTest {
    @Test
    public void shouldCheckExistenceOfSpecifiedHeaders() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Auth")).thenReturn("BADKEYID==:0dMJ2PnHPvV6tpMiJTfRUSII+Mk=");
        when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:36:42 +0000");

        HeaderPresenceValidator validator = new HeaderPresenceValidator("Auth", "Date");
        assertTrue(validator.isValid(request));
    }

    @Test
    public void shouldIndicateIfRequiredHeadersAreMissing() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:36:42 +0000");

        HeaderPresenceValidator validator = new HeaderPresenceValidator("Auth", "Date");
        assertFalse(validator.isValid(request));
    }
}
