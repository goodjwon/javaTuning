package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.*;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.cos.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */

public class ExtractSignatureContents {
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

            // Fields[0]
            PDField firstField = fields.get(0);

            // 필드가 SignatureField인지 확인
            if (firstField instanceof PDSignatureField) {
                PDSignatureField sigField = (PDSignatureField) firstField;

                // SignatureField는 하나 이상의 위젯(Widget Annotation)을 가질 수 있으며,
                // 일반적으로 getWidgets().get(0)으로 해당 위젯 Annotation에 접근 가능
                List<PDAnnotationWidget> widgets = sigField.getWidgets();
                if (widgets.isEmpty()) {
                    System.out.println("위젯 어노테이션이 없습니다.");
                    return;
                }

                PDAnnotationWidget widget = widgets.get(0);

                // Widget Annotation이 속한 페이지
                PDPage page = widget.getPage();
                if (page == null) {
                    System.out.println("페이지 정보를 가져올 수 없습니다.");
                    return;
                }

                // 해당 페이지의 주석(Annotations) 가져오기
                List<PDAnnotation> annots = page.getAnnotations();
                // Annots[1]
                if (annots.size() <= 1) {
                    System.out.println("해당 인덱스(1)에 어노테이션이 없습니다.");
                    return;
                }

                PDAnnotation annot = annots.get(1);

                // 어노테이션의 COSDictionary에 접근
                COSDictionary annotDict = annot.getCOSObject();
                // V 키를 통해 Value(서명 딕셔너리) 접근
                COSBase vBase = annotDict.getDictionaryObject(COSName.V);
                if (vBase instanceof COSDictionary) {
                    COSDictionary vDict = (COSDictionary) vBase;

                    // V 딕셔너리는 PDSignature를 표현하는 딕셔너리
                    // 여기에서 /Contents 키를 얻을 수 있음
                    COSBase contentsBase = vDict.getDictionaryObject(COSName.CONTENTS);
                    if (contentsBase instanceof COSString) {
                        COSString contentsString = (COSString) contentsBase;
                        byte[] signatureBytes = contentsString.getBytes();
                        System.out.println("서명 바이너리 데이터 길이: " + signatureBytes.length);
                        // 필요하다면 서명 값(PKCS#7)을 파일로 저장하거나 파싱 가능
                    } else {
                        System.out.println("/Contents 항목을 찾지 못했거나 COSString 타입이 아닙니다.");
                    }

                } else {
                    System.out.println("V 키에 해당하는 서명 딕셔너리를 찾을 수 없습니다.");
                }

            } else {
                System.out.println("해당 필드는 SignatureField가 아닙니다.");
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
