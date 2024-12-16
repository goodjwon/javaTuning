package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class PDFSignatureHexProcess {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            String hexString = extractSignatureHex(document);
            if (hexString == null) {
                System.out.println("서명 HEX 문자열을 추출하지 못했습니다.");
                return;
            }

            String base64String = processHexString(hexString);
            System.out.println("Base64 Encoded String: " + base64String);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF 문서로부터 서명 Contents를 HEX 문자열로 추출하는 메서드
     * 가정:
     *  - 첫 번째 필드(Fields[0])가 서명 필드이다.
     *  - 해당 서명 필드의 Annots[1]에 서명 딕셔너리가 존재한다.
     * 실제 상황에 따라 인덱스나 경로를 조정해야 한다.
     */
    private static String extractSignatureHex(PDDocument document) throws IOException {
        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
        if (acroForm == null) {
            System.out.println("AcroForm이 없습니다.");
            return null;
        }

        List<PDField> fields = acroForm.getFields();
        if (fields.isEmpty()) {
            System.out.println("AcroForm 필드가 없습니다.");
            return null;
        }

        PDField firstField = fields.get(0);
        if (!(firstField instanceof PDSignatureField)) {
            System.out.println("해당 필드는 서명 필드가 아닙니다.");
            return null;
        }

        PDSignatureField sigField = (PDSignatureField) firstField;
        List<PDAnnotationWidget> widgets = sigField.getWidgets();
        if (widgets.isEmpty()) {
            System.out.println("위젯 어노테이션이 없습니다.");
            return null;
        }

        PDAnnotationWidget widget = widgets.get(0);
        PDPage page = widget.getPage();
        if (page == null) {
            System.out.println("페이지 정보가 없습니다.");
            return null;
        }

        List<PDAnnotation> annots = page.getAnnotations();
        if (annots.size() <= 1) {
            System.out.println("Annots[1] 어노테이션이 없습니다.");
            return null;
        }

        PDAnnotation annot = annots.get(1);
        COSDictionary annotDict = annot.getCOSObject();

        // V 키 (서명 딕셔너리)
        COSBase vBase = annotDict.getDictionaryObject(COSName.V);
        if (!(vBase instanceof COSDictionary)) {
            System.out.println("V 키에 해당하는 서명 딕셔너리를 찾을 수 없습니다.");
            return null;
        }

        COSDictionary vDict = (COSDictionary) vBase;
        COSBase contentsBase = vDict.getDictionaryObject(COSName.CONTENTS);
        if (!(contentsBase instanceof COSString)) {
            System.out.println("/Contents 항목이 COSString 타입이 아닙니다.");
            return null;
        }

        COSString contentsString = (COSString) contentsBase;
        byte[] signatureBytes = contentsString.getBytes();
        return bytesToHex(signatureBytes);
    }

    /**
     * HEX 문자열을 처리하여:
     * 1) 불필요한 '0' 제거
     * 2) HEX → bytes 변환
     * 3) bytes → ISO-8859-1 문자열 디코딩
     * 4) 다시 ISO-8859-1로 인코딩한 뒤 Base64 인코딩
     */
    private static String processHexString(String hexString) {
        // 불필요한 0 제거
        hexString = removeTrailingZeros(hexString);

        // hex → bytes
        byte[] byteArray = hexToBytes(hexString);

        // bytes → ISO-8859-1 문자열
        String decodedString;
        try {
            decodedString = new String(byteArray, "ISO-8859-1");
        } catch (IOException e) {
            // 이 경우는 거의 없음(인코딩 에러), 기본적으로 ISO-8859-1은 모든 바이트를 수용
            decodedString = new String(byteArray);
        }

        // ISO-8859-1 로 다시 인코딩 후 Base64
        try {
            byte[] reEncodedBytes = decodedString.getBytes("ISO-8859-1");
            return Base64.getEncoder().encodeToString(reEncodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 바이트 배열을 HEX 문자열로 변환
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // hex 문자열 끝부분에 있는 '0'들을 제거하는 메서드 (필요시 수정)
    private static String removeTrailingZeros(String hex) {
        int i = hex.length() - 1;
        while (i >= 0 && hex.charAt(i) == '0') {
            i--;
        }
        return hex.substring(0, i + 1);
    }

    // HEX 문자열을 바이트 배열로 변환
    private static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int high = Character.digit(hex.charAt(i), 16);
            int low = Character.digit(hex.charAt(i + 1), 16);
            data[i / 2] = (byte) ((high << 4) + low);
        }
        return data;
    }
}
