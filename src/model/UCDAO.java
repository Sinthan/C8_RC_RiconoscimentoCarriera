package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DbConnection;

public class UCDAO implements UCDAOInterface{
	
	Statement stmt = null;
	String sql = "";
	@SuppressWarnings("static-access")
	Connection conn = new DbConnection().getInstance().getConn();
	
	/**
	 * This method takes the validation of the UC during the login phase
	 * @param email refers to the email used for registration
	 * @param password refers to the password used for registration
	 * @return returns a UC object
	 */
	
	public UC doRetrieveUc(String email, String password) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM uc "
				  + "WHERE TRIM(LOWER(email)) = TRIM(?) AND TRIM(password) = TRIM(?)");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UC u = new UC();
				u.setEmail(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setTelephone(rs.getString(3));
				u.setFax(rs.getString(4));
				return u;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("doRetrieveUc: error while executing the query\n" + e);
			throw new RuntimeException(e);
		}
	}
}
