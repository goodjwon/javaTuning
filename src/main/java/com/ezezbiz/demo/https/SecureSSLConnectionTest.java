package com.ezezbiz.demo.https;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class SecureSSLConnectionTest {

    // 테스트할 Freebill URL
    private static final String TEST_URL = "https://s2b.freebill.co.kr";
    // 인증서 파일 경로 (프로젝트 내 상대 경로)
    private static final String CERT_FILE_PATH = "resources/certs/SFSRootCAG2.cer";
    // 인증서 다운로드 URL
    private static final String CERT_DOWNLOAD_URL = "https://www.amazontrust.com/repository/SFSRootCAG2.cer";

    public static void main(String[] args) {
        try {
            System.out.println("Freebill SSL 연결 테스트를 시작합니다...");
            System.out.println("Java 버전: " + System.getProperty("java.version"));

            // 필요 시 인증서 다운로드
            ensureCertificateExists();

            // 특정 인증서를 신뢰하도록 설정 (Starfield 인증서)
            setupTrustSpecificCert();

            // HTTPS 연결 테스트
            testHttpsConnection();

            System.out.println("SSL 연결 테스트가 성공적으로 완료되었습니다!");
        } catch (Exception e) {
            System.out.println("SSL 연결 테스트 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 인증서 파일이 존재하는지 확인하고, 없으면 다운로드
     */
    private static void ensureCertificateExists() throws Exception {
        File certFile = new File(CERT_FILE_PATH);

        // 인증서 파일이 존재하지 않으면 다운로드
        if (!certFile.exists()) {
            System.out.println("인증서 파일이 존재하지 않습니다. 다운로드를 시작합니다...");

            // 디렉토리 생성
            Path certDir = Paths.get(certFile.getParent());
            if (!Files.exists(certDir)) {
                Files.createDirectories(certDir);
                System.out.println("인증서 디렉토리 생성: " + certDir);
            }

            // 인증서 다운로드
            URL website = new URL(CERT_DOWNLOAD_URL);

            try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                 FileOutputStream fos = new FileOutputStream(certFile)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }

            System.out.println("인증서 다운로드 완료: " + certFile.getAbsolutePath());
        } else {
            System.out.println("인증서 파일이 이미 존재합니다: " + certFile.getAbsolutePath());
        }
    }

    /**
     * 특정 인증서를 신뢰하도록 설정
     */
    private static void setupTrustSpecificCert() throws Exception {
        // 인증서 파일 로드
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert;

        try (InputStream certStream = Files.newInputStream(Paths.get(CERT_FILE_PATH))) {
            cert = (X509Certificate) cf.generateCertificate(certStream);
            System.out.println("로드된 인증서: " + cert.getSubjectX500Principal().getName());
            System.out.println("인증서 서명 알고리즘: " + cert.getSigAlgName());
        }

        // KeyStore 생성 및 인증서 추가
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("starfield", cert);

        // TrustManager 설정
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        // SSL 컨텍스트 설정
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        // 기본 SSL 소켓 팩토리 설정
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // 호스트명 검증은 기본값 사용 (활성화)
        System.out.println("특정 인증서를 신뢰하도록 설정했습니다. 호스트명 검증이 활성화되어 있습니다.");
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

        // SSL 정보 출력
        System.out.println("연결 시작...");

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        System.out.println("응답 코드: " + responseCode);
        System.out.println("사용 중인 Cipher Suite: " + connection.getCipherSuite());

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