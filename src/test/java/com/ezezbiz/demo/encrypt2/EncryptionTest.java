package com.ezezbiz.demo.encrypt2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionTest {

    private static MessageEncryptor encryptor;
    private static MessageDecryptor decryptor;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    @BeforeAll
    public static void setup() throws Exception {
        encryptor = new MessageEncryptor();
        decryptor = new MessageDecryptor();
        publicKey = encryptor.loadPublicKey("publicKey.pem");
        privateKey = decryptor.loadPrivateKey("privateKey.pem");
    }

    @Test
    public void testEncryptionAndDecryption() throws Exception {
        // Original message
        String originalMessage = "임정철";

        // Encrypt the message
        String encryptedMessage = encryptor.encrypt(publicKey, originalMessage);
        System.out.println(encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decryptor.decrypt(privateKey, encryptedMessage);
        System.out.println(decryptedMessage);

        // Check if the original message matches the decrypted message
        assertEquals(originalMessage, decryptedMessage, "The decrypted message should match the original message");
    }
}
