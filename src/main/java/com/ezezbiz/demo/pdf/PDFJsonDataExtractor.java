package com.ezezbiz.demo.pdf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PDFJsonDataExtractor {
    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // PDF의 Catalog(최상위)로부터 COS 구조 탐색 시작
            COSDictionary catalog = document.getDocumentCatalog().getCOSObject();

            Set<COSBase> visited = new HashSet<>();
            System.out.println("=== COS Stream 탐색 및 JSON 파싱 시작 ===");
            findAndParseJsonData(catalog, visited);
            System.out.println("=== COS Stream 탐색 및 JSON 파싱 종료 ===");

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

    private static void findAndParseJsonData(COSBase base, Set<COSBase> visited) {
        if (base == null) return;
        if (visited.contains(base)) return;
        visited.add(base);

        if (base instanceof COSDictionary) {
            COSDictionary dict = (COSDictionary) base;
            for (Map.Entry<COSName, COSBase> entry : dict.entrySet()) {
                findAndParseJsonData(entry.getValue(), visited);
            }

        } else if (base instanceof COSArray) {
            COSArray array = (COSArray) base;
            for (int i = 0; i < array.size(); i++) {
                findAndParseJsonData(array.get(i), visited);
            }

        } else if (base instanceof COSObject) {
            COSObject cosObj = (COSObject) base;
            findAndParseJsonData(cosObj.getObject(), visited);

        } else if (base instanceof COSStream) {
            // COSStream을 디코드하고 문자열 변환
            COSStream stream = (COSStream) base;
            try (InputStream is = stream.createInputStream()) {
                String content = readAll(is);

                System.out.println("=== Stream Content Start ===");
                System.out.println(content);
                System.out.println("=== Stream Content End ===");

                // "hash"와 "token" 키가 모두 있을 가능성을 체크
                if (content.contains("\"hash\"") && content.contains("\"token\"")) {
                    System.out.println("\n=== JSON 형식 데이터 발견 ===");
                    System.out.println("원문: " + content);

                    // JSON 파싱 시도
                    parseJsonAndPrint(content);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // COSString, COSName, COSInteger, COSFloat, COSBoolean는 단일 값이므로 무시
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

    private static void parseJsonAndPrint(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonString);

            JsonNode hashNode = root.get("hash");
            JsonNode tokenNode = root.get("token");

            if (hashNode != null && tokenNode != null && hashNode.isTextual() && tokenNode.isTextual()) {
                String hashValue = hashNode.asText();
                String tokenValue = tokenNode.asText();

                System.out.println("hash: " + hashValue);
                System.out.println("token: " + tokenValue);
            } else {
                System.out.println("hash 또는 token 키가 없거나 문자열 형태가 아닙니다.");
            }
        } catch (Exception e) {
            System.out.println("JSON 파싱 실패.");
            e.printStackTrace();
        }
    }
}
