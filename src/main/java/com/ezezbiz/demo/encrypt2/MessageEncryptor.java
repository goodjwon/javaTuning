package com.ezezbiz.demo.encrypt2;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MessageEncryptor {

    // Load the public key from resources (src/main/resources)
    public PublicKey loadPublicKey(String resourcePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ClassLoader classLoader = MessageEncryptor.class.getClassLoader();
        try (InputStream keyInputStream = classLoader.getResourceAsStream(resourcePath)) {
            if (keyInputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            String publicKeyContent = new String(keyInputStream.readAllBytes())
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        }
    }

    // Encrypt the message using the public key
    public String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedMessageBytes);
    }
}
