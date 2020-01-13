package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controller.DbConnection;
import model.Admin;

public class AdminDAO implements AdminDAOInterface{
	
	Statement stmt = null;
	String sql = "";
	@SuppressWarnings("static-access")
	Connection conn = new DbConnection().getInstance().getConn();
	
	/**
	 * This method takes the validation of the Admin during the login phase
	 * @param email refers to the email used for registration
	 * @param password refers to the password used for registration
	 * @return returns a Admin object
	 */
	
	public Admin doRetrieveAdmin(String email, String password) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM user "
				  + "WHERE TRIM(LOWER(email)) = TRIM(?) AND TRIM(password) = TRIM(?) AND user_type = 2");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Admin a = new Admin();
				a.setEmail(rs.getString(1));
				a.setName(rs.getString(2));
				a.setSurname(rs.getString(3));
				a.setSex(rs.getString("sex").charAt(0));
				a.setPassword(rs.getString(5));
				a.setUserType(rs.getInt(6));
				return a;
			}
			return null;
		} catch (SQLException e) {
			System.out.println("doRetrieveAdmin: error while executing the query\n" + e);
			throw new RuntimeException(e);
		}
	}
}
