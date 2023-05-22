package com.ezezbiz.demo.encrypt;

import javax.crypto.Cipher;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
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

        // 키를 PEM 형식으로 파일에 저장
        saveKey("publicKey.pem", "-----BEGIN PUBLIC KEY-----\n", Base64.getEncoder().encodeToString(publicKey.getEncoded()), "\n-----END PUBLIC KEY-----\n");
        saveKey("privateKey.pem", "-----BEGIN PRIVATE KEY-----\n", Base64.getEncoder().encodeToString(privateKey.getEncoded()), "\n-----END PRIVATE KEY-----\n");

        // 저장한 PEM 형식의 키를 다시 불러옴
        PublicKey loadedPublicKey = loadPublicKey("publicKey.pem");
        PrivateKey loadedPrivateKey = loadPrivateKey("privateKey.pem");

        // 암호화 및 복호화 테스트
        String originalMessage = "Hello, World!";
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, loadedPublicKey);
        byte[] secretMessageBytes = encryptCipher.doFinal(originalMessage.getBytes());
        String encodedSecretMessage = Base64.getEncoder().encodeToString(secretMessageBytes);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encodedSecretMessage);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, loadedPrivateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encodedSecretMessage));
        String decryptedMessage = new String(decryptedMessageBytes);

        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    // 키를 PEM 형식으로 파일에 저장하는 메소드
    private static void saveKey(String filepath, String header, String key, String footer) throws IOException {
        FileWriter out = new FileWriter(filepath);
        out.write(header);
        out.write(key);
        out.write(footer);
        out.close();
    }

    // 공개키를 PEM 형식의 파일에서 불러오는 메소드
    private static PublicKey loadPublicKey(String filepath) throws Exception {
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        publicKeyContent = publicKeyContent.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 비공개키를 PEM 형식의 파일에서 불러오는 메소드
    private static PrivateKey loadPrivateKey(String filepath) throws Exception {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(filepath)));
        privateKeyContent = privateKeyContent.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        System.out.println(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}