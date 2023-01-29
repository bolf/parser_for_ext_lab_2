package me.blf.parser_for_ext_lab_2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ParserForExtLab2Application {

    public static void main(String[] args) {
        try {
            processGenome("/home/blf/Genome_1-1.txt", "Genome_1-1");
            processGenome("/home/blf/Genome_2-1.txt", "Genome_2-1");
            HikariCPDataSource.closeDS();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Success!");
    }

    public static void processGenome(String fileName, String genomeName) throws IOException {
        var genomeStr = Files.readString(Path.of(fileName)).replace("\n","");
        GenSeqDAO.insertSequences(genomeName,2,splitIntoShingles(genomeStr, 2));
        GenSeqDAO.insertSequences(genomeName,5,splitIntoShingles(genomeStr, 5));
        GenSeqDAO.insertSequences(genomeName,9,splitIntoShingles(genomeStr, 9));
    }

    public static List<String> splitIntoShingles(String txt, int size) {
        var lst = new ArrayList<String>();
        for (int i = 0; i + size <= txt.length(); i++) {
            lst.add(txt.substring(i, i + size));
        }
        return lst;
    }
}
