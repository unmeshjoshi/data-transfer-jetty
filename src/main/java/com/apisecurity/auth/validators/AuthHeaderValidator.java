package com.apisecurity.auth.validators;


import javax.servlet.http.HttpServletRequest;

import static com.apisecurity.auth.AppConstants.*;

public class AuthHeaderValidator implements Validator {

    public boolean isValid(HttpServletRequest request) {
        Boolean containsKeyId = false;
        Boolean containsHMACString = false;
        String auth = request.getHeader(AUTH);
        String[] split = auth.split(AUTH_VALUE_SEPARATOR);
        Boolean hasValidSeparator = split.length == 2;
        if(hasValidSeparator){
            containsKeyId = split[0]!=null && split[0].length() != 0;
            containsHMACString = split[1]!=null && split[1].length() != 0;
        }
        return hasValidSeparator && containsKeyId && containsHMACString;
    }

    public String errorMessage() {
        return "Authorization Header is not Present";
    }
}
