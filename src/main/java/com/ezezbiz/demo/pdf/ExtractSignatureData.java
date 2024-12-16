package com.ezezbiz.demo.pdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

import java.io.File;
import java.io.IOException;

/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 *  String targetFieldName = "customer_btn_1"; 는 의미 있음.
 */

public class ExtractSignatureData {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";
        String targetFieldName = "customer_btn_1";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            if (acroForm == null) {
                System.out.println("AcroForm이 없습니다.");
                return;
            }

            PDField field = acroForm.getField(targetFieldName);
            if (field == null) {
                System.out.println("해당 필드를 찾을 수 없습니다: " + targetFieldName);
                return;
            }

            if (field instanceof PDSignatureField) {
                PDSignatureField sigField = (PDSignatureField) field;
                PDSignature signature = sigField.getSignature();

                if (signature != null) {
                    // PKCS#7 바이너리 데이터 추출
                    byte[] signatureBytes = signature.getContents();

                    // signatureBytes는 PKCS#7(CMS) 형식의 바이너리 데이터
                    // 이를 파싱하기 위해 BouncyCastle 사용 예시(아래 코드 참조)

                    if (signatureBytes != null && signatureBytes.length > 0) {
                        System.out.println(signatureBytes);
                        System.out.println("PKCS#7 바이너리 서명 데이터 길이: " + signatureBytes.length);
                        // 이 데이터로부터 해시 알고리즘, 해시값, 인증서, 서명자 정보 추출 가능
                    } else {
                        System.out.println("서명 바이너리 데이터가 없습니다.");
                    }

                } else {
                    System.out.println("해당 서명 필드에 서명 값이 없습니다.");
                }
            } else {
                System.out.println("해당 필드는 서명 필드가 아닙니다.");
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
