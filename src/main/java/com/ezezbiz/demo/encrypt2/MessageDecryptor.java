package com.ezezbiz.demo.encrypt2;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class MessageDecryptor {
    public static void main(String[] args) throws Exception {
        // Load the private key
        PrivateKey privateKey = loadPrivateKey("privateKey.pem");

        // Assuming the encrypted message is passed as a program argument
        String encryptedMessage = args[0];

        // Decrypt the message
        String decryptedMessage = decrypt(privateKey, encryptedMessage);

        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static PrivateKey loadPrivateKey(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        privateKeyContent = privateKeyContent.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static String decrypt(PrivateKey privateKey, String encryptedMessage) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedMessageBytes);
    }
}
