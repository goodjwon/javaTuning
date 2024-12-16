package com.ezezbiz.demo.pdf;

import java.io.IOException;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
/**
 * 작성자 : 박정원
 * 작성일 : 2024.12
 * 실패파일
 */
public class PdfSignature {
    public static void main(String args[]) {

        // Create a Document object.
        PDDocument pdDocument = new PDDocument();

        // Create a Page object
        PDPage pdPage = new PDPage();
        // Add the page to the document and save the document to a desired file.
        pdDocument.addPage(pdPage);
        try {

            PDSignature pdSignature = new PDSignature();
            pdSignature.setFilter(PDSignature.FILTER_VERISIGN_PPKVS);
            pdSignature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_SHA1);
            pdSignature.setName("KSCodes");
            pdSignature.setLocation("WFH");
            pdSignature.setReason("Sample Signature test");
            pdSignature.setSignDate(Calendar.getInstance());
            pdDocument.addSignature(pdSignature);

            pdDocument.save("D:\\signature-sample.pdf");
            pdDocument.close();
            System.out.println("PDF saved to the location !!!");

        } catch (IOException ioe) {
            System.out.println("Error while saving pdf" + ioe.getMessage());
        }
    }
}
