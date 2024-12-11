package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFSignatureInfo {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // 문서 내 모든 서명 정보 가져오기
            List<PDSignature> signatures = document.getSignatureDictionaries();

            for (PDSignature sig : signatures) {
                // 서명자 이름
                String name = sig.getName();
                // 서명한 이유
                String reason = sig.getReason();
                // 서명한 위치
                String location = sig.getLocation();
                // 서명한 날짜
                java.util.Calendar signDate = sig.getSignDate();

                System.out.println("서명자 이름: " + name);
                System.out.println("서명 이유: " + reason);
                System.out.println("서명 위치: " + location);
                System.out.println("서명 날짜: " + (signDate != null ? signDate.getTime() : "정보 없음"));

                // 서명 검증 로직(인증서 검증 등)은 별도 구현 필요
                // 실제 서명 검증을 위해서는 인증서 체인, CRL/OCSP 검증 등을 수행하는 추가 작업이 필요합니다.
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
