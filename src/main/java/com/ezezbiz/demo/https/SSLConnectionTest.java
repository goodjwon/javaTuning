package com.ezezbiz.demo.https;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLConnectionTest {

    // 테스트할 Freebill URL
    private static final String TEST_URL = "https://s2b.freebill.co.kr";

    public static void main(String[] args) {
        try {
            System.out.println("Freebill SSL 연결 테스트를 시작합니다...");

            // SSL 검증을 우회하는 설정 적용
            setupTrustAllCerts();

            // HTTPS 연결 테스트
            testHttpsConnection();

            System.out.println("SSL 연결 테스트가 성공적으로 완료되었습니다!");
        } catch (Exception e) {
            System.out.println("SSL 연결 테스트 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 모든 SSL 인증서를 신뢰하도록 설정
     */
    private static void setupTrustAllCerts() throws Exception {
        // 모든 인증서를 신뢰하는 TrustManager 구현
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // 검증 생략
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // 검증 생략
                    }
                }
        };

        // SSL 컨텍스트 생성 및 설정
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        // 기본 SSL 소켓 팩토리 설정
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // 호스트명 검증 비활성화
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        System.out.println("모든 SSL 인증서를 신뢰하도록 설정했습니다.");
    }

    /**
     * HTTPS 연결 테스트
     */
    private static void testHttpsConnection() throws Exception {
        URL url = new URL(TEST_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        // 연결 설정
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        System.out.println("응답 코드: " + responseCode);

        // 응답 내용 읽기
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 응답의 일부만 출력 (너무 길 수 있으므로)
            String responseStr = response.toString();
            int maxLength = Math.min(responseStr.length(), 100);
            System.out.println("응답 내용 (처음 " + maxLength + "자): "
                    + responseStr.substring(0, maxLength) + "...");
        }

        // 연결 닫기
        connection.disconnect();
    }
}