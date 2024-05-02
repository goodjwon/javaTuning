package com.ezezbiz.demo.encrypt2;

import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        // Save the keys to PEM format files
        saveKey("publicKey.pem", "-----BEGIN PUBLIC KEY-----\n", Base64.getEncoder().encodeToString(publicKey.getEncoded()), "\n-----END PUBLIC KEY-----\n");
        saveKey("privateKey.pem", "-----BEGIN PRIVATE KEY-----\n", Base64.getEncoder().encodeToString(privateKey.getEncoded()), "\n-----END PRIVATE KEY-----\n");
    }

    private static void saveKey(String filepath, String header, String key, String footer) throws IOException {
        try (FileWriter out = new FileWriter(filepath)) {
            out.write(header);
            out.write(key);
            out.write(footer);
        }
    }
}
