package com.ezezbiz.demo.files.io;

import com.google.common.io.Resources;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReadDirectoryFiles {

    public static void main(String[] args) throws IOException {

        List<String> collect = getFilePaths("D:\\Downloads");
        collect.forEach(System.out::println);

        System.out.println(getFileNumber("D:\\Downloads\\조달청 나라장터 표준연계 API 인증서 만료 30일 전 안내.eml"));

        System.out.println(isFileNameMatched(
                "D:\\Downloads\\조달청 나라장터 표준연계 API 인증서 만료 30일 전 안내.eml" ,
                "D:\\Downloads\\PapyrusAxS2B(20190813)\\조달청 나라장터 표준연계 API 인증서 만료 30일 전 안내.eml"
        ));



    }


    private static List<String> getFilePaths(String filePath) throws IOException {
        Path start = Paths.get(filePath);
        List<String> collect;
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            collect = stream
                    .map(String::valueOf)
                    .sorted()
                    .collect(Collectors.toList());

            collect.forEach(System.out::println);
        }
        return collect;
    }

    private static Integer getFileNumber(String path) throws IOException {
        Stream<String> fileStream = Files.lines(Paths.get(path));
        return (int) fileStream.count();
    }

    private static boolean isFileNameMatched(String path1, String path2) {
        return FilenameUtils.getName(path1).equals(FilenameUtils.getName(path2));
    }

    /**
     * https://www.programmersought.com/article/98922172927/
     * https://m.blog.naver.com/tmondev/221388780914
     * https://offbyone.tistory.com/70
     * https://mycup.tistory.com/273
     */
    public static class SxssfWorkbook {
        public static void writeBigData() throws IOException {
            long startTime = System.currentTimeMillis (); // acquisition start time, in milliseconds
            InputStream inputStream = Resources.getResource("SampleData.xlsx").openStream();


            XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
            inputStream.close();

            SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
            wb.setCompressTempFiles(true);
            SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(1);
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
}
