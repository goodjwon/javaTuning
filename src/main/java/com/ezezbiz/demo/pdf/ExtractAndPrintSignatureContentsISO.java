package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */

public class ExtractAndPrintSignatureContentsISO {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            if (acroForm == null) {
                System.out.println("AcroForm이 없습니다.");
                return;
            }

            List<PDField> fields = acroForm.getFields();
            if (fields.isEmpty()) {
                System.out.println("AcroForm 필드가 없습니다.");
                return;
            }

            // Fields[0] 접근 (사용 환경에 따라 인덱스 조정 필요)
            PDField firstField = fields.get(0);

            // 서명 필드인지 확인
            if (firstField instanceof PDSignatureField) {
                PDSignatureField sigField = (PDSignatureField) firstField;

                List<PDAnnotationWidget> widgets = sigField.getWidgets();
                if (widgets.isEmpty()) {
                    System.out.println("위젯 어노테이션이 없습니다.");
                    return;
                }

                PDAnnotationWidget widget = widgets.get(0);

                // 위젯이 속한 페이지
                PDPage page = widget.getPage();
                if (page == null) {
                    System.out.println("페이지 정보가 없습니다.");
                    return;
                }

                List<PDAnnotation> annots = page.getAnnotations();
                // Annots[1]에 접근 (사용 환경에 따라 인덱스 조정 필요)
                if (annots.size() <= 1) {
                    System.out.println("Annots[1] 어노테이션이 없습니다.");
                    return;
                }

                PDAnnotation annot = annots.get(1);
                COSDictionary annotDict = annot.getCOSObject();

                // V 키(서명 딕셔너리)
                COSBase vBase = annotDict.getDictionaryObject(COSName.V);
                if (vBase instanceof COSDictionary) {
                    COSDictionary vDict = (COSDictionary) vBase;

                    // Contents 키 추출
                    COSBase contentsBase = vDict.getDictionaryObject(COSName.CONTENTS);
                    if (contentsBase instanceof COSString) {
                        COSString contentsString = (COSString) contentsBase;
                        byte[] signatureBytes = contentsString.getBytes();

                        // 원본 바이너리 데이터 길이
                        System.out.println("서명 바이너리 데이터 길이: " + signatureBytes.length);

                        // 1. 바이트 -> HEX 문자열 변환
                        String hexString = bytesToHex(signatureBytes);
                        System.out.println("서명 데이터 (HEX): " + hexString);

                        // 2. HEX -> 바이트 복원
                        byte[] restoredBytes = hexToBytes(hexString);

                        // 3. ISO-8859-1 인코딩으로 문자열 변환 시도
                        try {
                            String isoString = new String(restoredBytes, "ISO-8859-1");
                            System.out.println("HEX 복원 후 ISO-8859-1 해석: " + isoString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("/Contents 항목이 COSString 타입이 아닙니다.");
                    }
                } else {
                    System.out.println("V 키에 해당하는 서명 딕셔너리를 찾을 수 없습니다.");
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

    // 바이트 배열을 HEX 문자열로 변환하는 메서드
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // HEX 문자열을 바이트 배열로 변환하는 메서드
    private static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int high = Character.digit(hex.charAt(i), 16);
            int low = Character.digit(hex.charAt(i+1), 16);
            data[i / 2] = (byte) ((high << 4) + low);
        }
        return data;
    }
}
