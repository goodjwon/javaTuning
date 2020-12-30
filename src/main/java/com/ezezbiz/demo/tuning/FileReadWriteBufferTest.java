package com.ezezbiz.demo.tuning;
import java.io.*;

public class FileReadWriteBufferTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        File tempFile = new File("/Users/jwon/Downloads/VirtualBox-6.1.16-140961-OSX.dmg");
        FileInputStream in = new FileInputStream(tempFile);

        File outFile = new File("/Users/jwon/Downloads/VirtualBox-6.1.16-140961-OSX_copy.dmg");
        FileOutputStream os = new FileOutputStream(outFile);

        byte[] buffer = new byte[1024*4];
        int read = 0, bytesBuffer = 0, i = 0;

        Thread.sleep(1000);

        while ((read = in.read(buffer)) > -1){
            os.write(buffer, 0, read);
            bytesBuffer += read;
            System.out.println("bytesBufferd is" + bytesBuffer);
            i++;
        }
        os.flush();
        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}
