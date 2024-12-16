package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.cos.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PDFStructureDumper {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // 문서 카탈로그(Catalog)에 접근 (PDF 구조의 시작점)
            COSDictionary catalogDict = document.getDocumentCatalog().getCOSObject();

            System.out.println("=== PDF COS 구조 출력 시작 ===");
            // 방문한 객체를 기록할 Set
            Set<COSBase> visited = new HashSet<>();
            printCOSObject(catalogDict, 0, visited);
            System.out.println("=== PDF COS 구조 출력 종료 ===");

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

    /**
     * COSObject를 재귀적으로 탐색하면서 구조를 출력하는 메서드
     */
    private static void printCOSObject(COSBase base, int indent, Set<COSBase> visited) {
        if (base == null) {
            printIndent(indent);
            System.out.println("null");
            return;
        }

        // 이미 방문한 객체라면 순환참조 방지를 위해 중단
        if (visited.contains(base)) {
            printIndent(indent);
            System.out.println("[Already visited, skipping to prevent infinite loop]");
            return;
        }
        visited.add(base);

        if (base instanceof COSDictionary) {
            COSDictionary dict = (COSDictionary) base;
            printIndent(indent);
            System.out.println("COSDictionary {");
            for (Map.Entry<COSName, COSBase> entry : dict.entrySet()) {
                printIndent(indent + 1);
                System.out.println(entry.getKey().getName() + ":");
                printCOSObject(entry.getValue(), indent + 2, visited);
            }
            printIndent(indent);
            System.out.println("}");

        } else if (base instanceof COSArray) {
            COSArray array = (COSArray) base;
            printIndent(indent);
            System.out.println("COSArray [");
            for (int i = 0; i < array.size(); i++) {
                printCOSObject(array.get(i), indent + 1, visited);
            }
            printIndent(indent);
            System.out.println("]");

        } else if (base instanceof COSString) {
            printIndent(indent);
            System.out.println("COSString: " + ((COSString) base).getString());

        } else if (base instanceof COSName) {
            printIndent(indent);
            System.out.println("COSName: " + ((COSName) base).getName());

        } else if (base instanceof COSInteger) {
            printIndent(indent);
            System.out.println("COSInteger: " + ((COSInteger) base).intValue());

        } else if (base instanceof COSFloat) {
            printIndent(indent);
            System.out.println("COSFloat: " + ((COSFloat) base).floatValue());

        } else if (base instanceof COSBoolean) {
            printIndent(indent);
            System.out.println("COSBoolean: " + ((COSBoolean) base).getValue());

        } else if (base instanceof COSNull) {
            printIndent(indent);
            System.out.println("COSNull");

        } else if (base instanceof COSObject) {
            // COSObject는 보통 다른 객체를 가리키는 reference, 참조 대상을 풀어서 재귀 탐색
            COSObject cosObj = (COSObject) base;
            printIndent(indent);
            System.out.println("COSObject (reference, obj#: " + cosObj.getObjectNumber() + ")");
            printCOSObject(cosObj.getObject(), indent + 1, visited);
        } else {
            printIndent(indent);
            System.out.println("Unknown COS type: " + base.getClass().getName());
        }
    }

    private static void printIndent(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }
}

