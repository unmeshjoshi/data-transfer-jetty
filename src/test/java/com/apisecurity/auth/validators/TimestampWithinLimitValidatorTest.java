package com.apisecurity.auth.validators;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.apisecurity.auth.AppConstants.DATE_FORMAT;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class TimestampWithinLimitValidatorTest {

    @Test
    public void shouldCheckIfRequestTimeTooSkewed() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        SystemClock clock = Mockito.mock(SystemClock.class);
        when(clock.now()).thenReturn(date("Tue, 01 Jan 2013 19:05:00 +0000"));
        TimestampWithinLimitValidator dateFormatValidator = new TimestampWithinLimitValidator(clock);
        when(request.getHeader("Date")).thenReturn("Tue, 01 Jan 2013 19:00:00 +0000");
        assertFalse(dateFormatValidator.isValid(request));
    }

    private Date date(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.parse(date);
    }
}