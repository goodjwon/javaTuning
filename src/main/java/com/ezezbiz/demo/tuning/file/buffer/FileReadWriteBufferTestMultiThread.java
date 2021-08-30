package com.ezezbiz.demo.tuning.file.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileReadWriteBufferTestMultiThread {
    public static void main(String[] args) throws FileNotFoundException {
        MultiThread[] mt = new MultiThread[3];
        for (int i = 0; i < 3; i++) {
            mt[i] = new MultiThread(i);
            mt[i].start();
        }
    }
}

class MultiThread extends Thread {
    private final int i;

    public MultiThread(int i) {
        this.i = i;
    }

    public void run() {
        try {
            File tempFile = new File("/Users/jwon/Downloads/VirtualBox-6.1.16-140961-OSX.dmg");
            FileInputStream in = new FileInputStream(tempFile);

            String fileName = "/Users/jwon/Downloads/" + i + "_VirtualBox-6.1.16-140961-OSX_copy.dmg";
            File outFile = new File(fileName);
            FileOutputStream os = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024 * 4];
            int read = 0, bytesBuffer = 0, i = 0;

            Thread.sleep(1000);

            while ((read = in.read(buffer)) > -1) {
                os.write(buffer, 0, read);
                bytesBuffer += read;
                System.out.println("bytesBufferd is" + bytesBuffer);
                i++;
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
