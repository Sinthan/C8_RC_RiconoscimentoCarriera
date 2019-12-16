package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controller.DbConnection;
import model.Secretary;

public class SecretaryDAO implements SecretaryDAOInterface{
	
	Statement stmt = null;
	String sql = "";
	@SuppressWarnings("static-access")
	Connection conn = new DbConnection().getInstance().getConn();
	
	/**
	 * This method takes the validation of the secretary during the login phase
	 * @param email refers to the email used for registration
	 * @param password refers to the password used for registration
	 * @return returns a secretary object
	 */
	public Secretary doRetrieveSecretary(String email, String password) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM user "
				  + "WHERE TRIM(LOWER(email)) = TRIM(?) AND TRIM(password) = TRIM(?) AND user_type = 1");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Secretary s = new Secretary();
				s.setEmail(rs.getString(1));
				s.setName(rs.getString(2));
				s.setSurname(rs.getString(3));
				s.setSex(rs.getString("sex").charAt(0));
				s.setPassword(rs.getString(5));
				s.setUserType(rs.getInt(6));
				return s;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
