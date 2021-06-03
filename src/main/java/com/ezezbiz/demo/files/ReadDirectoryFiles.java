package com.ezezbiz.demo.files;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
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

}
