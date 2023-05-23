package com.ezezbiz.demo.encrypt2;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MessageEncryptor {
    public static void main(String[] args) throws Exception {
        // Load the public key
        PublicKey publicKey = loadPublicKey("publicKey.pem");

        // Encrypt the message
        String originalMessage = "Hello, World!";
        String encodedSecretMessage = encrypt(publicKey, originalMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encodedSecretMessage);
    }

    private static PublicKey loadPublicKey(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        publicKeyContent = publicKeyContent.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = encryptCipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(secretMessageBytes);
    }
}
