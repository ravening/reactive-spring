package com.rakesh.reactivespring.config;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RsaConfig {
    private final Cipher cipher;

    public RsaConfig() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public PrivateKey getPrivateKey(final String privateKeyStr) throws GeneralSecurityException {
        var privateKeyPem = privateKeyStr
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        var pkcs8EncodedKey = Base64.getDecoder().decode(privateKeyPem);
        var factory = KeyFactory.getInstance("RSA");

        return factory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));
    }

    public PublicKey getPublicKey(final String publicKeyStr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var privateKeyPem = publicKeyStr
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        var pkcs8EncodedKey = Base64.getDecoder().decode(privateKeyPem);
        var spec = new X509EncodedKeySpec(pkcs8EncodedKey);
        var kf = KeyFactory.getInstance(EncryptionUtility.RSA);

        return kf.generatePublic(spec);
    }
}
