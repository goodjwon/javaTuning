package com.ezezbiz.demo.tuning.file.excel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * https://www.programmersought.com/article/98922172927/
 * https://m.blog.naver.com/tmondev/221388780914
 * https://offbyone.tistory.com/70
 * https://mycup.tistory.com/273
 */
public class SxssfWorkbook {
    public static void writeBigData() throws IOException {
        long startTime = System.currentTimeMillis (); // acquisition start time, in milliseconds
        FileInputStream inputStream = new FileInputStream("C:\\temp\\template.xls");
        XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
        inputStream.close();

        SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
        wb.setCompressTempFiles(true);
        SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
        sh.setRandomAccessWindowSize (100); // keep 100 rows in memory, exceeding rows will be flushed to disk, each loaded into memory 100
        for (int rowNum = 0; rowNum <500000; rowNum ++) {// 500000 is the number of rows
            Row row = sh.createRow(rowNum);
            for (int cellNum = 0; cellNum <10; cellNum ++) {// 10 Number of columns
                Cell cell = row.createCell(cellNum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        FileOutputStream outFile = new FileOutputStream("C:\\temp\\tempsxssf.xlsx");
        wb.write(outFile);
        outFile.close();
        wb.dispose();// dispose of temporary files backing this workbook on disk
        long endTime = System.currentTimeMillis (); // acquisition start time, in milliseconds
        long duration = endTime - startTime;
        System.out.println ( "Long run time:" + Double.valueOf (duration) / 1000 + "s");
    }

    public static void main(String[] args) throws Throwable {
        writeBigData();
    }

}
