package com.apisecurity.auth;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class HMACAuthorizationHeader {
    private String keyId;
    private String signature;

    HMACAuthorizationHeader(String keyId, String signature) {
        this.keyId = keyId;
        this.signature = signature;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getSignature() {
        return signature;
    }

    public static HMACAuthorizationHeader createFrom(HttpServletRequest request) {
        String auth = request.getHeader("Auth");
        String[] authValue = auth.split(":");
        return new HMACAuthorizationHeader(authValue[0], unEscapeSignature(authValue));

    }

    private static String unEscapeSignature(String[] authValue) {
        String signature = null;
        try {
            signature = URLDecoder.decode(authValue[1], "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
            //should never come here, as we are passing character encoding as utf-8.
        }
        return signature;
    }
}
