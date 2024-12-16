package com.ezezbiz.demo.pdf;


import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */

public class PDFCustomDataFinder {

    public static void main(String[] args) {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // COS 구조 시작점: Catalog
            COSDictionary catalog = document.getDocumentCatalog().getCOSObject();

            // 방문한 객체 추적 (무한루프 방지)
            Set<COSBase> visited = new HashSet<>();
            System.out.println("=== COS Stream 탐색 시작 ===");
            findJsonLikeData(catalog, visited);
            System.out.println("=== COS Stream 탐색 종료 ===");

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

    private static void findJsonLikeData(COSBase base, Set<COSBase> visited) {
        if (base == null) return;
        if (visited.contains(base)) return;
        visited.add(base);

        if (base instanceof COSDictionary) {
            COSDictionary dict = (COSDictionary) base;
            for (Map.Entry<COSName, COSBase> entry : dict.entrySet()) {
                findJsonLikeData(entry.getValue(), visited);
            }

        } else if (base instanceof COSArray) {
            COSArray array = (COSArray) base;
            for (int i = 0; i < array.size(); i++) {
                findJsonLikeData(array.get(i), visited);
            }

        } else if (base instanceof COSObject) {
            COSObject cosObj = (COSObject) base;
            findJsonLikeData(cosObj.getObject(), visited);

        } else if (base instanceof COSStream) {
            // COSStream 디코드 후 내용 검색
            COSStream stream = (COSStream) base;
            try (InputStream is = stream.createInputStream()) {
                String content = readAll(is);

                System.out.println(content);
                // "hash" 와 "token" 키워드가 모두 들어있는지 간단히 체크
                if (content.contains("\"hash\"")) {
                    System.out.println("\n=== JSON 유사 데이터 발견 ===");
                    System.out.println("원문: " + content);

                    // JSON 파싱 시도
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(content);

                        // hash, token 값 추출
                        JsonNode hashNode = root.get("hash");
                        JsonNode tokenNode = root.get("token");

                        if (hashNode != null && tokenNode != null && hashNode.isTextual() && tokenNode.isTextual()) {
                            System.out.println("hash 값: " + hashNode.asText());
                            System.out.println("token 값: " + tokenNode.asText());
                        } else {
                            System.out.println("JSON 형식이나 hash/token 키가 문자열 타입이 아닐 수 있습니다.");
                        }

                    } catch (Exception e) {
                        System.out.println("JSON 파싱에 실패했습니다.");
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // COSString, COSName, COSInteger, COSFloat, COSBoolean은 여기선 탐색 종료
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
