package com.apisecurity.auth;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.lang.*;



public class HMACSignatureBuilder {
    private SecretKeySpec secretKeySpec;

    public HMACSignatureBuilder(String secretKey) {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        this.secretKeySpec = new SecretKeySpec(secretKeyBytes, AppConstants.HMAC_SHA_1);
    }

    public String createSignature(String stringToSign) {
        try {
            Mac mac = Mac.getInstance(AppConstants.HMAC_SHA_1);
            mac.init(secretKeySpec);

            byte[] rawHMAC = mac.doFinal(stringToSign.getBytes());
            return new String(DatatypeConverter.printBase64Binary(rawHMAC));

        } catch (Exception e) {
            String errorMessage = "Error creating HMAC signature: " + e.getMessage();
            throw new SecurityException(errorMessage, e);
        }

    }
}
