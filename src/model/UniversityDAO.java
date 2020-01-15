package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * ReportDAO. interacts with the database in order to manage the university name.
 */
import controller.DbConnection;

/**
 * @return returns a set of university name.
 */
public class UniversityDAO implements UniversityDAOInterface {

  Statement stmt = null;
  String sql = "";
  String error;
  @SuppressWarnings("static-access")

  Connection conn = (Connection) new DbConnection().getInstance().getConn();

  @Override
  public ArrayList<University> doRetrieveAllUniversity() {
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT* FROM university");
      ArrayList<University> universityNames = new ArrayList<>();
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        University u = new University();
        u.setName(rs.getString(1));
        universityNames.add(u);
      }
      return universityNames;
    } catch (SQLException e) {
      System.out.println("doRetrieveAllUniversity: error while executing the query\n" + e);
      throw new RuntimeException(e);
    }
  }

}
