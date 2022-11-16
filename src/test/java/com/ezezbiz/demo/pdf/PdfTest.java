package com.ezezbiz.demo.pdf;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class PdfTest {
    @Test
    public void testPdf() throws IOException {

        PDDocument doc = PDDocument.load(new File("d:\\Downloads\\S2B20190929-G00009.pdf"));
        PDFTextStripper textStripper=new PDFTextStripper();

        StringWriter textWriter=new StringWriter();
        textStripper.writeText(doc,textWriter);
        doc.close();


        List<PDSignature> aaa = doc.getSignatureDictionaries();
        System.out.println(aaa.size());
        aaa.forEach(a->{
            System.out.println(a.getName());
            System.out.println(a.getByteRange());
            System.out.println(a.getContactInfo());
            System.out.println(a.getContents());
            System.out.println(a.getContents());
            System.out.println(a.getCOSObject());
            System.out.println(a.getFilter());
            System.out.println(a.getLocation());
        });

//        System.out.println(textWriter.toString());

    }
}
