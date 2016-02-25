package com.apisecurity.auth.validators;

import javax.servlet.http.HttpServletRequest;

public interface Validator{
    boolean isValid(HttpServletRequest request);
    String errorMessage();
}