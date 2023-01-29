package me.blf.parser_for_ext_lab_2;

import java.sql.SQLException;

public class DbWriter {
  public static void write() {
      try {
          var conn = HikariCPDataSource.getConnection();
          var stst = conn.prepareStatement("select * from course_schema.account_transactions_23_01_2023");
          var dataSet = stst.executeQuery();
          while (dataSet.next()) {
              System.out.println(dataSet.getString("phone_number"));
          }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}