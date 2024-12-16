package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDMetadata;

import java.io.*;
import java.util.*;
/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PDFStreamDumper {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // 문서 카탈로그에서 시작
            COSDictionary catalog = document.getDocumentCatalog().getCOSObject();

            Set<COSBase> visited = new HashSet<>();
            System.out.println("=== COS 구조의 모든 Stream 탐색 시작 ===");
            dumpAllStreams(catalog, visited);
            System.out.println("=== COS 구조의 모든 Stream 탐색 종료 ===");

            // 추가로 메타데이터 영역 확인
            PDMetadata metadata = document.getDocumentCatalog().getMetadata();
            if (metadata != null) {
                System.out.println("\n=== Metadata Content 시작 ===");
                try (InputStream metaIs = metadata.createInputStream()) {
                    String metaContent = readAll(metaIs);
                    System.out.println(metaContent);
                }
                System.out.println("=== Metadata Content 종료 ===");
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

    private static void dumpAllStreams(COSBase base, Set<COSBase> visited) {
        if (base == null) return;
        if (visited.contains(base)) return;
        visited.add(base);

        if (base instanceof COSDictionary) {
            COSDictionary dict = (COSDictionary) base;
            for (Map.Entry<COSName, COSBase> entry : dict.entrySet()) {
                dumpAllStreams(entry.getValue(), visited);
            }

        } else if (base instanceof COSArray) {
            COSArray array = (COSArray) base;
            for (int i = 0; i < array.size(); i++) {
                dumpAllStreams(array.get(i), visited);
            }

        } else if (base instanceof COSObject) {
            COSObject cosObj = (COSObject) base;
            dumpAllStreams(cosObj.getObject(), visited);

        } else if (base instanceof COSStream) {
            // COSStream의 내용을 전부 출력
            COSStream stream = (COSStream) base;
            try (InputStream is = stream.createInputStream()) {
                String content = readAll(is);
                System.out.println("\n=== Stream Content Start ===");
                System.out.println(content);
                System.out.println("=== Stream Content End ===");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // COSString, COSName, COSInteger, COSFloat, COSBoolean는 단일 값이므로 출력하지 않아도 됨
    }

    private static String readAll(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int n;
        while ((n = is.read(buf)) > 0) {
            bos.write(buf, 0, n);
        }
        return bos.toString("UTF-8");
    }
}
