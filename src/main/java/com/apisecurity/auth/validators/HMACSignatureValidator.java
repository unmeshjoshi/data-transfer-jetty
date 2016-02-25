package com.apisecurity.auth.validators;


import com.apisecurity.auth.ClientKey;
import com.apisecurity.auth.HMACSignatureBuilder;
import com.apisecurity.auth.StringToSignBuilder;

import javax.servlet.http.HttpServletRequest;

public class HMACSignatureValidator {

    public boolean isValid(ClientKey clientKey, String signature, HttpServletRequest request) {
        return matchSignature(clientKey, signature, request);
    }

    private boolean matchSignature(ClientKey clientKey, String signature, HttpServletRequest request) {
        HMACSignatureBuilder signatureBuilder = new HMACSignatureBuilder(clientKey.getKey());
        String stringToSign = new StringToSignBuilder(request).build();
        String hmacHash = signatureBuilder.createSignature(stringToSign);
        return hmacHash.equals(signature);
    }

    public String getErrorMessage(){
        return "Invalid Authentication Details";
    }
}
