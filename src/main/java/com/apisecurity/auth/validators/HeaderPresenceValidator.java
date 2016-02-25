package com.apisecurity.auth.validators;


import javax.servlet.http.HttpServletRequest;

public class HeaderPresenceValidator implements Validator {

    private String[] headerNames;
    private String missingHeader;

    public HeaderPresenceValidator(String... headerNames) {
        this.headerNames = headerNames;
    }

    public boolean isValid(HttpServletRequest request) {
        for (String headerName : headerNames) {
            if (request.getHeader(headerName) == null){
                missingHeader = headerName;
                return false;
            }
        }
        return true;
    }

    public String errorMessage() {
        return missingHeader+" is not present in the Request";
    }
}
