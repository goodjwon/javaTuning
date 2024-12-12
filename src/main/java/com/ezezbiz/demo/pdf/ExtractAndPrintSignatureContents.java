package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExtractAndPrintSignatureContents {
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

            // Fields[0] 접근
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

                        // 1. 바이트 길이 출력
                        System.out.println("서명 바이너리 데이터 길이: " + signatureBytes.length);

                        // 2. HEX 문자열 변환 후 출력
                        String hexString = bytesToHex(signatureBytes);
                        System.out.println("서명 데이터 (HEX): " + hexString);

                        // 3. UTF-8 시도 (대부분 의미없는 문자일 가능성)
                        try {
                            String utf8String = new String(signatureBytes, "UTF-8");
                            System.out.println("서명 데이터 (UTF-8 해석): " + utf8String);
                        } catch (Exception e) {
                            // UTF-8 변환 실패 시 무시
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
}
