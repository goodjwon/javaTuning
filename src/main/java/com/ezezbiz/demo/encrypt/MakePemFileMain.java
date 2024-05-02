package com.ezezbiz.demo.encrypt;

import javax.crypto.Cipher;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MakePemFileMain {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        // Save the keys to PEM format files
        saveKey("publicKey.pem", "-----BEGIN PUBLIC KEY-----\n", Base64.getEncoder().encodeToString(publicKey.getEncoded()), "\n-----END PUBLIC KEY-----\n");
        saveKey("privateKey.pem", "-----BEGIN PRIVATE KEY-----\n", Base64.getEncoder().encodeToString(privateKey.getEncoded()), "\n-----END PRIVATE KEY-----\n");

        // Load the saved PEM format keys
        PublicKey loadedPublicKey = loadPublicKey("publicKey.pem");
        PrivateKey loadedPrivateKey = loadPrivateKey("privateKey.pem");

        // Testing encryption and decryption
        String originalMessage = "Hello, World!";
        String encodedSecretMessage = encrypt(loadedPublicKey, originalMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encodedSecretMessage);

        String decryptedMessage = decrypt(loadedPrivateKey, encodedSecretMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static void saveKey(String filepath, String header, String key, String footer) throws IOException {
        try (FileWriter out = new FileWriter(filepath)) {
            out.write(header);
            out.write(key);
            out.write(footer);
        }
    }

    private static PublicKey loadPublicKey(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        publicKeyContent = publicKeyContent.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey loadPrivateKey(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        privateKeyContent = privateKeyContent.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = encryptCipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(secretMessageBytes);
    }

    private static String decrypt(PrivateKey privateKey, String encryptedMessage) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedMessageBytes);
    }
}
