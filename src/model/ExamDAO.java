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
	 * @return		<ul><li>a positive count of the number of rows affected (from INSERT, UPDATE, or DELETE)
	 *				<li>0 if no rows were affected
	 *				<li>-1 if the statement succeeded, but there is no update count information available</ul>
	 *				<li>-2 if the attributes of the passed argument aren't fully specified
	 * @throws 		SQLException
	 * @author 		Gianluca Rossi
	 */
	public int insertExam(Exam exam) {
		if (exam.getName().equals("") || exam.getCFU() == -1 || exam.getProgramLink().equals("")) // Checks if attributes are set
			return -2;
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = 0;

		/* Adds the 3 parametric values in the EXAM table.
		 * The exam ID is automatically generated from the
		 * database, as it is defined as an auto increment value,
		 * so it's not required to create a new one here.
		 */
		String insertSQL = "INSERT INTO EXAM " +
				" (NAME, CFU, LINK_PROGRAM) " +
				" VALUES (?, ?, ?)";
		try {
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
		return result;
	}
	
	/**
	 * retrieve all exams 
	 * @return arraylist of exams
	 */
	public ArrayList<Exam> doRetrieveAllExamsByIDRequestRC(int requestRCID){
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM EXAM WHERE ");
			ArrayList<Exam> examsList = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exam e = new Exam();
				e.setName(rs.getString(1));
				e.setExamID(rs.get)
				System.out.println(e.getName());
				examsList.add(e);
			}
			return examsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
	
}
