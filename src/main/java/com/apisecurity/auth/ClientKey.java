package com.apisecurity.auth;

public final class ClientKey {
  private String keyId;
  private String key;

  private ClientKey(String keyId, String key) {
    this.keyId = keyId;
    this.key = key;
  }

  public String getKeyId() {
    return keyId;
  }

  public String getKey() {
    return key;
  }

  public static ClientKey valueOf(String id, String value) {
    return new ClientKey(id, value);
  }

}

