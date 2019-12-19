package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ReportDAO.
 *interacts with the database in order 
 *to manage the university name.
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
	public List<University> doRetrieveAllUniversity() {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT* FROM university");
			ArrayList<University> universityNames = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				University u = new University();
				u.setName(rs.getString(1));
				System.out.println(u.getName());
				universityNames.add(u);
			}
			return universityNames;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
