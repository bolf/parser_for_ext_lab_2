package me.blf.parser_for_ext_lab_2;

import java.sql.SQLException;
import java.util.List;

public class GenSeqDAO {
  public static void insertSequences(String genId, int seqLength, List<String> seqLst) {
      try (var conn = HikariCPDataSource.getConnection();
          var statement = conn.prepareStatement("insert into course_schema.ext_lab2_genome_sequences " +
                  "(genome_id, seq_length, aa_sequence) values(?, ?, ?);")){
          conn.setAutoCommit(false);
          for (String seq : seqLst) {
              statement.setString(1, genId);
              statement.setInt(2, seqLength);
              statement.setString(3, seq);
              statement.addBatch();
          }
          statement.executeBatch();
          conn.commit();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}
