package model;
import model.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.DbConnection;

/**
 * ExamDAO is used for communication between Exam and DB
 */ 

public class ExamDAO implements ExamDAOInterface{

	Statement stmt = null;
	String sql = "";
	String error;
	@SuppressWarnings("static-access")

	Connection conn = (Connection) new DbConnection().getInstance().getConn();

	/**
	 * Saves the exam into the database.
	 * 
	 * @param exam	the <tt>Exam</tt> object that will be saved.
	 * @return		<ul><li>-1 if one or more rows are affected (from INSERT, UPDATE, or DELETE)
	 *				<li>-2 if no rows were affected
	 *				<li>-3 if the statement succeeded, but there is no update count information available</ul>
	 *				<li>-4 if the attributes of the passed argument aren't fully specified
	 *				<li>-5 if the exam already exists.
	 * @throws 		SQLException
	 * @author 		Gianluca Rossi
	 */
	public int insertExam(Exam exam) {
		if (exam.getName().equals("") || exam.getCFU() == -1 || exam.getProgramLink().equals("")) // Checks if attributes are set
			return -4;
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = -2;

		/* Adds the 3 parametric values in the EXAM table.
		 * The exam ID is automatically generated from the
		 * database, as it is defined as an auto increment value,
		 * so it's not required to create a new one here.
		 */
		String insertSQL = "INSERT INTO EXAM " +
				" (NAME, CFU, LINK_PROGRAM) " +
				" VALUES (?, ?, ?)";
		try {  
//			connection = DbConnection.getInstance().getConn();
//			PreparedStatement ps = connection.prepareStatement(
//					" SELECT * FROM EXAM WHERE NAME = ? AND CFU = ? AND LINK_PROGRAM = ?");
//			ps.setString(1,exam.getName());
//			ps.setInt(2, exam.getCFU());
//			ps.setString(3, exam.getProgramLink());
//			ResultSet r = ps.executeQuery();
//
//			if (!r.wasNull()) {  // Exam found
//				int examID = r.getInt(1);
//				System.out.println("Exam already present, id:" + examID);
//				return examID;
//			} else { 
			// Exam not already present
				connection = DbConnection.getInstance().getConn();
				preparedStatement = connection.prepareStatement(insertSQL);			
				// Setting parameters
				preparedStatement.setString(1, exam.getName());
				preparedStatement.setInt(2, exam.getCFU());
				preparedStatement.setString(3, exam.getProgramLink());
				// Logging the operation
				System.out.println("insertExam: "+ exam.toString());

				result = preparedStatement.executeUpdate();	
				connection.commit();
			
		} catch(SQLException e) {
			new RuntimeException(e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		if (result > 0) {
			return -1;
		} else if (result == 0) {
			return -2;
		} else if (result == -1) {
			return -3;
		}
		return result;
	}

	/**
	 * retrieve all exams 
	 * @return arraylist of exams
	 */
	public ArrayList<Exam> doRetrieveAllExamsByIDRequestRC(int requestRCID){
		return null;
	}

	/**
	 * retrieve exam 
	 * @return -1 if insert failed, 0 if ok 
	 */
	public int doRetrieveExam(int requestRCID, int ExamID) {
		int flag = 0;
		return flag; 
	}

	/**
	 * delete exam 
	 * @return -1 if insert failed, 0 if ok 
	 */
	public int deleteExamsByRequestID(int id) {
		int flag = 0;
		return flag;
	}

	public int doRetrieveMaxExamID() {
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT MAX(ID_EXAM) FROM EXAM");
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				int examID = r.getInt(1);
				return examID;
			}
		} catch(SQLException e) {
			new RuntimeException(e);
		}
		return -1;
	}

}
