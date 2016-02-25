package com.apisecurity.auth.validators;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class DateFormatValidatorTest {
    @Test
    public void shouldValidateDateHeaderFormat() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        DateFormatValidator dateFormatValidator = new DateFormatValidator();
        when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:36:42");
        assertFalse(dateFormatValidator.isValid(request));
    }

}
