package com.ezezbiz.demo.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PDFTextExtractor {
    public static void main(String[] args) {
        // 추출할 PDF 파일 경로
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            // PDF 문서 로드
            document = PDDocument.load(new File(pdfPath));

            // PDF 문서에서 텍스트를 추출하기 위해 PDFTextStripper 객체 생성
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 페이지 범위 지정(원하는 경우)
            // pdfStripper.setStartPage(1);
            // pdfStripper.setEndPage(5);

            // 텍스트 추출
            String extractedText = pdfStripper.getText(document);

            // 결과 출력
            System.out.println(extractedText);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 문서 닫기
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
