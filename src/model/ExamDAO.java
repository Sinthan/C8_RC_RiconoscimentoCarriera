package model;
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

	Connection conn = (Connection) new DbConnection().getInstance().getConn();

	/**
	 * Saves the exam into the database.
	 * 
	 * @param exam	the <tt>Exam</tt> object that will be saved.
	 * @return		<ul><li>a positive value if the exam that wants to be saved is already present,
	 * 						the value corresponds to the examID of the already present exam record
	 * 				<li>-1 if one or more rows are affected
	 *				<li>-2 if no rows were affected
	 *				<li>-3 if the statement succeeded, but there is no update count information available
	 *				<li>-4 if the attributes of the passed argument aren't fully specified
	 * @author 		Gianluca Rossi
	 * @author 		Lorenzo Maturo
	 */
	public int insertExam(Exam exam) {
		if (exam.getName().equals("") || exam.getCFU() == -1 || exam.getProgramLink().equals("")) { // Checks if attributes are set
			System.out.println("Please set the Exam's name, cfu and program link before trying to add it to the database.");
			return -4;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = -2; // No rows affected by default

		/* Adds the 3 given parametric values in the EXAM table.
		 * The exam ID is automatically generated from the
		 * database, as it is defined as an auto increment value,
		 * so it's not required to create a new one here.
		 */
		String insertSQL = "INSERT INTO EXAM " +
				" (NAME, CFU, LINK_PROGRAM) " +
				" VALUES (?, ?, ?)";

		/* Selects the exams that match the 3 given parametric values
		 */
		String selectSQL = "SELECT * FROM EXAM "
				+ " WHERE NAME = ? AND CFU = ? AND LINK_PROGRAM = ?";
		try {  
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);
			// Setting parameters
			preparedStatement.setString(1,exam.getName());
			preparedStatement.setInt(2, exam.getCFU());
			preparedStatement.setString(3, exam.getProgramLink());
			ResultSet resSet = preparedStatement.executeQuery();

			if (resSet.next() && resSet.getInt(1) != 0) {	// Exam found
				int examID = resSet.getInt(1);
				System.out.println("Exam \"" + exam.getName() + "\" already present.");
				return examID;
			} else {		// Exam not already present in the database, proceeding with the insertion
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(insertSQL);			
				// Setting parameters
				preparedStatement.setString(1, exam.getName());
				preparedStatement.setInt(2, exam.getCFU());
				preparedStatement.setString(3, exam.getProgramLink());
				// Executing the insertion
				result = preparedStatement.executeUpdate();	
				connection.commit();
			}
		} catch(SQLException e) {
			new RuntimeException("Couldn't insert the exam \"" + exam.getName() + "\" in the database " + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		if (result > 0) { // SQL Insert succeeded
			return -1;
		} else if (result == 0) { // SQL Insert failed
			return -2;
		} else if (result == -1) { // SQL Insert succeeded without log
			return -3;
		}
		System.out.println("insertExam(result=" + result + ": " + exam.toString());	// Logging the operation
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

	// metodo che consente di rimuovere l'esame aggiunto in fase di test
	public void removeAddExamForTest() throws SQLException {
		String sql = "Delete FROM englishvalidation.exam  where name = 'ingegneria del test' and cfu = 9 and link_program= '//link di riferimento//'" ;
		Connection conn = (Connection) new DbConnection().getInstance().getConn();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		}catch (SQLException e) {
			e.getMessage();
		}
	}

}
