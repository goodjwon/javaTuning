package com.ezezbiz.demo.encrypt2;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAExampleBothWays {
    public static void main(String[] args) throws Exception {
        // 1️⃣ 키쌍 생성
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("🔑 공개키: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("🔒 개인키: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        String plainText = "Hello Asymmetric Encryption!";
        System.out.println("\n원문: " + plainText);

        // -----------------------------
        // (1) 공개키로 암호화 → 개인키로 복호화
        // -----------------------------
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedByPublic = encryptCipher.doFinal(plainText.getBytes());
        String encryptedText1 = Base64.getEncoder().encodeToString(encryptedByPublic);
        System.out.println("\n📦 공개키로 암호화된 데이터: " + encryptedText1);

        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedByPrivate = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText1));
        System.out.println("🔓 개인키로 복호화 결과: " + new String(decryptedByPrivate));

        // -----------------------------
        // (2) 개인키로 암호화 → 공개키로 복호화
        // -----------------------------
        Cipher encryptWithPrivate = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptWithPrivate.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedByPrivate = encryptWithPrivate.doFinal(plainText.getBytes());
        String encryptedText2 = Base64.getEncoder().encodeToString(encryptedByPrivate);
        System.out.println("\n📦 개인키로 암호화된 데이터(서명 개념): " + encryptedText2);

        Cipher decryptWithPublic = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptWithPublic.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedByPublic = decryptWithPublic.doFinal(Base64.getDecoder().decode(encryptedText2));
        System.out.println("🔓 공개키로 복호화 결과(검증 개념): " + new String(decryptedByPublic));
    }
}
