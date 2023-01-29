package me.blf.parser_for_ext_lab_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ParserForExtLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(ParserForExtLab2Application.class, args);
        //1 прочитать строку из файла
        //2 получить все последовательности 2, записать, 5, записать, 9 записать
//        используя последовательность символов-шинглов (k=2, 5, 9)
//        например, для последовательности аминокислот TCAGACTT при k=2 у
//        нас получается набор элементов: {TC, CA, AG, GA, AC, CT, TT}
        try {
            var strLst = Files.readAllLines(Path.of("/home/blf/Genome_2-1.txt"));
            getShingles(strLst.get(0), 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> getShingles(String txt, int size) {

        Arrays.stream(txt.split(""))
            .forEach(s ->{} );

        return null;
    }

}
