package com.rakesh.reactivespring.config;

public class EncryptionUtility {
    // PKCS#8 format
    public static final String BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----";
    public static final String END_PRIVATE_KEY = "-----END PRIVATE KEY-----";
    // PKCS#1 format

    public static final String BEGIN_RSA_PRIVATE_START = "-----BEGIN RSA PRIVATE KEY-----";
    public static final String END_RSA_PRIVATE_END = "-----END RSA PRIVATE KEY-----";

    // Public Key Format
    public static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
    public static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";

    // Error Message
    public static final String INVALID_PUBLIC_KEY = "Invalid Public Key!!!";
    public static final String INVALID_PRIVATE_KEY = "Invalid Private Key!!!";
    public static final String COULD_NOT_PARSE_PKS1 = "Could not parse a PKCS1 private key.";
    public static final String UTF_8 = "UTF-8";
    public static final String RSA = "RSA";
    public static final String AES_INSTANCE = "AES/CBC/PKCS5PADDING";
    public static final String AES = "AES";
}
