package com.apisecurity.auth.validators;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.apisecurity.auth.AppConstants.DATE;
import static com.apisecurity.auth.AppConstants.DATE_FORMAT;

public class DateFormatValidator implements Validator {

    public boolean isValid(HttpServletRequest request) {
        String date = request.getHeader(DATE);
        return hasValidFormat(date);
    }

    public String errorMessage() {
        return "InValid Date Format";
    }

    private boolean hasValidFormat(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            simpleDateFormat.parse(date);
            return true;

        } catch (ParseException e) {
            return false;
        }
    }
}
