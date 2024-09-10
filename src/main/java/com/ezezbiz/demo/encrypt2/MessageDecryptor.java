package com.ezezbiz.demo.encrypt2;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class MessageDecryptor {

    // Load the private key from resources (src/main/resources)
    public PrivateKey loadPrivateKey(String resourcePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ClassLoader classLoader = MessageDecryptor.class.getClassLoader();
        try (InputStream keyInputStream = classLoader.getResourceAsStream(resourcePath)) {
            if (keyInputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            String privateKeyContent = new String(keyInputStream.readAllBytes())
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "");

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
    }

    // Decrypt the message using the private key
    public String decrypt(PrivateKey privateKey, String encryptedMessage) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedMessageBytes);
    }
}
