package com.ezezbiz.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.operator.OperatorCreationException;

import org.apache.pdfbox.cos.*;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.util.*;
/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PDFSignatureAnalyzer {
    public static void main(String[] args) throws OperatorCreationException {
        String pdfPath = "D:\\S2B20241129-B00042.pdf";
        String targetFieldName = "customer_btn_1";

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfPath));

            // 1. 서명 정보 분석
            analyzeSignatureField(document, targetFieldName);

            // 2. token 값 탐색
            // 2-1. Document Information Custom Metadata에서 token 시도
            findTokenInDocumentInfo(document);

            // 2-2. COS 구조 전체 탐색으로 token 찾기 (필요하다면)
            // 만약 Document Info에서 찾지 못했다면 COS 구조를 통해 token 탐색 시도
            System.out.println("\n=== COS 구조를 통한 token 탐색 시작 ===");
            Set<COSBase> visited = new HashSet<>();
            findTokenInCOS(document.getDocumentCatalog().getCOSObject(), visited, 0);
            System.out.println("=== COS 구조를 통한 token 탐색 종료 ===");

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

    private static void analyzeSignatureField(PDDocument document, String targetFieldName) throws OperatorCreationException {
        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
        if (acroForm == null) {
            System.out.println("AcroForm이 없습니다. 해당 PDF에 폼 필드가 없을 수 있습니다.");
            return;
        }

        PDField field = acroForm.getField(targetFieldName);
        if (field == null) {
            System.out.println("해당 필드를 찾을 수 없습니다: " + targetFieldName);
            return;
        }

        if (field instanceof PDSignatureField) {
            PDSignatureField sigField = (PDSignatureField) field;
            PDSignature signature = sigField.getSignature();

            if (signature != null) {
                // 서명 메타정보 출력
                System.out.println("서명 필드명: " + sigField.getFullyQualifiedName());
                System.out.println("서명자 이름: " + signature.getName());
                System.out.println("서명 위치: " + signature.getLocation());
                System.out.println("서명 이유: " + signature.getReason());
                System.out.println("서명 날짜: " + (signature.getSignDate() != null ? signature.getSignDate().getTime() : "없음"));
                System.out.println("필터: " + signature.getFilter());
                System.out.println("SubFilter: " + signature.getSubFilter());

                int[] byteRange = signature.getByteRange();
                if (byteRange != null && byteRange.length == 4) {
                    System.out.println("ByteRange: " + byteRange[0] + ", " + byteRange[1] + ", "
                            + byteRange[2] + ", " + byteRange[3]);
                }

                // PKCS#7 서명 바이너리 추출
                byte[] signatureBytes = signature.getContents();
                if (signatureBytes != null && signatureBytes.length > 0) {
                    System.out.println("\nPKCS#7 바이너리 서명 데이터 길이: " + signatureBytes.length);

                    // BouncyCastle를 이용해 PKCS#7(CMS) 데이터 파싱
                    parseAndPrintPKCS7Info(signatureBytes);
                } else {
                    System.out.println("서명 바이너리 데이터가 없습니다.");
                }

            } else {
                System.out.println("해당 서명 필드에 서명 값이 없습니다.");
            }
        } else {
            System.out.println("해당 필드는 서명 필드가 아닙니다.");
        }
    }

    private static void parseAndPrintPKCS7Info(byte[] signatureBytes) throws OperatorCreationException {
        try {
            CMSSignedData cms = new CMSSignedData(signatureBytes);
            SignerInformationStore signers = cms.getSignerInfos();
            Collection<SignerInformation> signerInfos = signers.getSigners();

            for (SignerInformation signerInfo : signerInfos) {
                // 메시지 다이제스트 알고리즘
                String digestAlg = signerInfo.getDigestAlgOID();
                System.out.println("Digest Algorithm OID: " + digestAlg);

                // 인증서 추출
                Collection<?> certs = cms.getCertificates().getMatches(signerInfo.getSID());
                for (Object c : certs) {
                    org.bouncycastle.cert.X509CertificateHolder certHolder = (org.bouncycastle.cert.X509CertificateHolder) c;
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    X509Certificate cert = (X509Certificate) cf.generateCertificate(
                            new ByteArrayInputStream(certHolder.getEncoded()));

                    // 서명 검증
                    boolean verifies = signerInfo.verify(new JcaSimpleSignerInfoVerifierBuilder().build(cert));
                    System.out.println("서명 검증 결과: " + verifies);
                    System.out.println("서명자 DN: " + cert.getSubjectDN());
                }

                // 메시지 다이제스트(해시) 값
                byte[] contentDigest = signerInfo.getContentDigest();
                if (contentDigest != null) {
                    System.out.println("메시지 다이제스트(해시) 값(hex): " + bytesToHex(contentDigest));
                } else {
                    System.out.println("메시지 다이제스트를 가져올 수 없습니다.");
                }
            }
        } catch (CMSException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Document Information에서 token 시도
    private static void findTokenInDocumentInfo(PDDocument document) {
        PDDocumentInformation info = document.getDocumentInformation();
        if (info != null) {
            // Custom Metadata에 token이 있을 수 있음
            String token = info.getCustomMetadataValue("token");
            if (token != null) {
                System.out.println("\nDocument Information에서 token 발견: " + token);
            } else {
                System.out.println("\nDocument Information에서 token을 찾을 수 없습니다.");
            }
        } else {
            System.out.println("\nDocument Information이 없습니다.");
        }
    }

    // COS 구조 탐색을 통해 token 찾기
    // COSDictionary, COSArray 등을 재귀적으로 살펴봄
    private static void findTokenInCOS(COSBase base, Set<COSBase> visited, int indent) {
        if (base == null) {
            return;
        }

        if (visited.contains(base)) {
            return;
        }
        visited.add(base);

        if (base instanceof COSDictionary) {
            COSDictionary dict = (COSDictionary) base;
            for (Map.Entry<COSName, COSBase> entry : dict.entrySet()) {
                COSName key = entry.getKey();
                COSBase value = entry.getValue();

                if ("token".equals(key.getName())) {
                    // token 키를 찾았을 경우 값 출력
                    System.out.println("token 키 발견!");
                    if (value instanceof COSString) {
                        System.out.println("token 값: " + ((COSString)value).getString());
                    } else {
                        System.out.println("token 값(COS 타입): " + value);
                    }
                }

                findTokenInCOS(value, visited, indent + 1);
            }

        } else if (base instanceof COSArray) {
            COSArray array = (COSArray) base;
            for (int i = 0; i < array.size(); i++) {
                findTokenInCOS(array.get(i), visited, indent + 1);
            }
        } else if (base instanceof COSObject) {
            COSObject cosObj = (COSObject) base;
            findTokenInCOS(cosObj.getObject(), visited, indent + 1);
        }
        // COSString, COSName, COSInteger, COSFloat 등은 단일 값이므로 특별히 탐색하지 않아도 됨
    }
}
