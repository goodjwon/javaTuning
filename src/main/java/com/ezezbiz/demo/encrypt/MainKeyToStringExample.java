package com.ezezbiz.demo.encrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MainKeyToStringExample {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        // 키를 문자열로 변환
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 저장한 키를 다시 불러옴
        PublicKey loadedPublicKey = loadPublicKey(publicKeyString);
        PrivateKey loadedPrivateKey = loadPrivateKey(privateKeyString);

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

    // 공개키를 문자열에서 불러오는 메소드
    private static PublicKey loadPublicKey(String keyString) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(keyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 비공개키를 문자열에서 불러오는 메소드
    private static PrivateKey loadPrivateKey(String keyString) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(keyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(byteKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
