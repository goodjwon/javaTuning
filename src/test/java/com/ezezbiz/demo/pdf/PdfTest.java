package com.ezezbiz.demo.pdf;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * https://tmxhsk99.tistory.com/191
 */
public class PdfTest {
    @Test
    public void testPdf() throws IOException {

        PDDocument doc = PDDocument.load(new File("d:\\Downloads\\S2B20211215-H00002.pdf"));
        PDFTextStripper textStripper=new PDFTextStripper();

        StringWriter textWriter=new StringWriter();
        textStripper.writeText(doc,textWriter);

        //Getting the PDDocumentInformation object
        PDDocumentInformation pdd = doc.getDocumentInformation();

        //Retrieving the info of a PDF document
        System.out.println("Author of the docum00ent is :"+ pdd.getAuthor());
        System.out.println("Title of the document is :"+ pdd.getTitle());
        System.out.println("Subject of the document is :"+ pdd.getSubject());

        System.out.println("Creator of the document is :"+ pdd.getCreator());
        System.out.println("Creation date of the document is :"+ pdd.getCreationDate());
        System.out.println("Modification date of the document is :"+ pdd.getModificationDate());
        System.out.println("Keywords of the document are :"+ pdd.getKeywords());
        System.out.println("getSignatureDictionaries of the document are :"+ doc.getSignatureDictionaries());
        System.out.println("getSignatureFields of the document are :"+doc.getSignatureFields());
        System.out.println("getLastSignatureDictionary of the document are :"+doc.getLastSignatureDictionary());

        doc.close();


        List<PDSignature> aaa = doc.getSignatureDictionaries();
        System.out.println(aaa.size());
//        aaa.forEach(a->{
//            System.out.println(a.getName());
//            System.out.println(a.getByteRange());
//            System.out.println(a.getContactInfo());
//            System.out.println(a.getContents());
//            System.out.println(a.getContents());
//            System.out.println(a.getCOSObject());
//            System.out.println(a.getFilter());
//            System.out.println(a.getLocation());
//        });


//        System.out.println(textWriter.toString());

    }
}
