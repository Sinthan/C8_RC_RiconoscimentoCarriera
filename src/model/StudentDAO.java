package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controller.DbConnection;
import model.Student;

/**
 * StudentDAO is used for communication between Student and DB
 *
 */
public class StudentDAO implements StudentDAOInterface {

	
	Statement stmt = null;
	String sql = "";
	String error;
	@SuppressWarnings("static-access")
	
	Connection conn = (Connection) new DbConnection().getInstance().getConn();
	
	/**
	 * This method takes the validation of the student during the login phase
	 * @param email refers to the email used for registration
	 * @param password refers to the password used for registration
	 * @return returns a student object
	 */
	
	@Override
	public Student doRetrieveStudent(String email, String password) {
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM user "
				  + "WHERE TRIM(LOWER(email)) = TRIM(?) AND TRIM(password) = TRIM(?) AND user_type = 0");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Student s = new Student();
				s.setEmail(rs.getString(1));
				s.setName(rs.getString(2));
				s.setSurname(rs.getString(3));
				s.setSex(rs.getString("sex").charAt(0));
				s.setPassword(rs.getString(5));
				s.setUserType(rs.getInt(6));
				return s;
			}else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method takes care of registering a new student in the system
	 * @param email refers to the email inserted by the student in the system interface 
	 * @param name refers to the name entered by the student in the system interface
	 * @param surname refers to the surname entered by the student in the system interface
	 * @param userType refers to the userType assigned to the specific user to whom the function is dedicated
	 * @param sex refers to sex, chosen through radio button, during recording
	 * @param password refers to the password entered by the student in the system interface
	 * @param date refers to the system date at the time of registration
	 */
	@Override
	public int insertStudent(String email, String name, String surname, int userType, char sex, String password, Date date) {
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					 " SELECT  email FROM user WHERE TRIM(LOWER(email)) = TRIM(?) ");
	          ps.setString(1,email.toLowerCase());
	          ResultSet r = ps.executeQuery();
	          
	          if (r.wasNull()) {
	        	  return 0;
	        	  
	          } else {
	            int count = r.last() ? r.getRow() : 0;
	            if (count == 0) {
	            	PreparedStatement ps2 = conn.prepareStatement(" INSERT INTO user "
	            			+ " (email, name, surname, sex, password, user_type, date_registration) "
	            			+ " VALUES " + " (?, ?, ?, ?, ?, ?, ?) ");
	            	ps2.setString(1, email.toLowerCase());
	            	ps2.setString(2, name);
	            	ps2.setString(3, surname);
	            	ps2.setString(4, String.valueOf(sex));
	            	ps2.setString(5, password);
	            	ps2.setInt(6, userType);
	            	ps2.setDate(7,date);
	            	ps2.executeUpdate();
	            	return 1;
	            	}
	          }
	  		} catch (SQLException e) {
	  			throw new RuntimeException("Impossibile effettuare la registrazione"+ e);
	  		}
	      
		return 0;
	}
	/**
	 * Insert new student  
	 * 
	 * @return -1 if insert failed, 0 if insert ok
	 */
	public int insertStudent(Student s) {
		
		int flag = 0;
		return flag;
	}
	
	/**
	 * Retrieve the student through his email        
	 * @return student object
	 */
	public Student doRetrieveStudentByEmail(String email) {
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM user "
				  + "WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Student s = new Student();
				s.setEmail(email);
				s.setName(rs.getString(2));
				s.setSurname(rs.getString(3));
				s.setSex(rs.getString(4).charAt(0));
				s.setPassword(rs.getString(5));
				s.setUserType(rs.getInt(6));
				s.setRegistrationDate(rs.getDate(7));
				return s;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Delete student having his email        
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteStudentByEmail(String email) {
		
		int flag = 0;
		return flag;
		
	}
	
	/**
	 * Delete external student when his registration date is too old        
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteOldExternalStudent() {
		
		int flag = 0;
		return flag;
		
	}
	
	
	
}
