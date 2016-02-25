package com.apisecurity.auth.validators;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.apisecurity.auth.AppConstants.*;

public class TimestampWithinLimitValidator implements Validator {

    long MAX_MILLIS = 5 * 60 * 1000;
    private SystemClock clock;

    public TimestampWithinLimitValidator(SystemClock clock) {
        this.clock = clock;
    }

    public boolean isValid(HttpServletRequest request) {
        String date = request.getHeader(DATE);
        return hasValidFormat(date);
    }

    public String errorMessage() {
        return "Request time is too skewed";
    }

    private boolean hasValidFormat(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date clientTimestamp = simpleDateFormat.parse(date);
            Date now = clock.now();
            long timeDifference = now.getTime() - clientTimestamp.getTime();
            return timeDifference < MAX_MILLIS;

        } catch (ParseException e) {
            return false;
        }
    }
}
