package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.log.SysoCounter;

import controller.DbConnection;

/**
 * <tt>ValidatedExamDAO</tt>
 * <p>
 * Implementation of the ValidatedExamDAOInterface, manages and retrieves data related to
 * <tt>ValidatedExam</tt> objects from the MySQL database.
 * 
 * @author	Gianluca Rossi
 * @see		ValidatedExam
 */

public class ValidatedExamDAO implements ValidatedExamDAOInterface {

	/**
	 * Inserts the specified <tt>ValidatedExam</tt> object into the database.
	 * 
	 * @param	vExam	the <tt>ValidatedExam</tt> object that will be inserted.
	 * @return			<ul><li>a positive count if the insertion succeeded
	 *					<li>0 if nothing was added to the database
	 *					<li>-1 if the insertion succeeded, but the database didn't return any information about the number of inserted rows
	 *					<li>-2 if the attributes of the passed argument aren't fully specified</ul>
	 */
	@Override
	public int insertValidatedExam(ValidatedExam exam) {
		if (exam.getExamName().equals("") || exam.getValidatedCFU() == -1) { // Checks if attributes are set
			System.out.println("Please set the Exam's name and cfu before trying to add it to the database.");
			return -4;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = -2; // No rows affected by default

		
		String insertSQL = "INSERT INTO VALIDATEDEXAM " +
				" (NAME_EXAM, CFU_CONVALIDATED, MODE_VALIDATION,FK_ID_REPORT) " +
				" VALUES (?, ?, ?, ?)";

		// Selects the exams that match the 2 given parametric values
		String selectSQL = "SELECT * FROM EXAM "
				+ " WHERE NAME = ? AND FK_ID_REPORT = ?";
		try {  
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);
			// Setting parameters
			preparedStatement.setString(1,exam.getExamName());
			preparedStatement.setInt(2, exam.getReportID());
			ResultSet resSet = preparedStatement.executeQuery();

			if (resSet.first()) {	// Exam found
				int examID = resSet.getInt(1);
				System.out.println("Exam \"" + exam.getExamName() + "\" already present.");
				return examID;
			} else {		// Exam not already present in the database, proceeding with the insertion
				System.out.println("Exam \"" + exam.getExamName() + "\" not present, adding it to the database.");
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(insertSQL);			
				// Setting parameters
				preparedStatement.setString(1, exam.getExamName());
				preparedStatement.setInt(2, exam.getValidatedCFU());
				preparedStatement.setString(3, exam.getValidationProcedure());
				preparedStatement.setInt(4, exam.getReportID());
				// Executing the insertion
				result = preparedStatement.executeUpdate();	
				connection.commit();
			}
		} catch(SQLException e) {
			System.out.println("insertValidatedExam: error while executing the query\n" + e);
			new RuntimeException("Couldn't insert the exam \"" + exam.getExamName() + "\" in the database " + e);
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
	 * Updates the specified <tt>ValidatedExam</tt> object into the database.
	 * 
	 * @param	vExam	the <tt>ValidatedExam</tt> object that will be updated.
	 * @return			<ul><li>a positive count if the update succeeded
	 *					<li>0 if nothing was altered into the database
	 *					<li>-1 if the update succeeded, but the database didn't return any information about the number of updated rows
	 *					<li>-2 if the attributes of the passed argument aren't fully specified</ul>
	 */
	@Override
	public int updateValidatedExam(ValidatedExam vExam) {

		if(vExam==null) {
			System.out.println("updateValidatedExam: Void Exam passed");
			return -1;
		} else if(doRetrieveValidatedExam(vExam.getReportID(),vExam.getExamName())==null){
			System.out.println("updateValidatedExam: exam doesn't exists in DB");
			return -2;
		}else {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			// Selects the exams that match the 2 given parametric values
			String updateSQL = "UPDATE validate_exam SET CFU_CONVALIDATED = ?, MODE_VALIDATION = ?" 
					+ " WHERE (FK_ID_REPORT = ? AND NAME_EXAM = ?); ";
			try { 
				
				connection = DbConnection.getInstance().getConn();
				preparedStatement = connection.prepareStatement(updateSQL);
				
				// Setting parameters
				preparedStatement.setInt(1,vExam.getValidatedCFU());
				preparedStatement.setString(2, vExam.getValidationProcedure());
				preparedStatement.setInt(3,vExam.getReportID());
				preparedStatement.setString(4, vExam.getExamName());
				preparedStatement.executeUpdate();
				connection.commit();
				connection.close();
				return 0;
					
				
			} catch(SQLException e) {
				System.out.println("updateValidatedExam: error while executing the query\n" + e);
				new RuntimeException("Couldn't find the exam \"" + vExam.getExamName() + "\" in the database " + e);
			} finally {
				// Statement release
				if(preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return 0;
		}
		
	
	}

	/**
	 * Retrieves a <tt>ValidatedExam</tt> object that matches the given <tt>Report</tt>
	 * (identified through an ID), and the given exam name.
	 * 
	 * @param	reportID	the report ID number that the <tt>ValidatedExam</tt> object must have
	 * @param	examName	the exam name (used in the external university) that the
	 * 						<tt>ValidatedExam</tt> object must have
	 * @return				a <tt>ValidatedExam</tt> object if found, a null object otherwise
	 * @see	Report
	 */
	@Override
	public ValidatedExam doRetrieveValidatedExam(int reportID, String examName) {
		if( reportID < 0 || examName.equals("") ) {
			System.out.println("reportID or exam name is wrong");
			return null;
		
		} else {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ValidatedExam exam = new ValidatedExam(); 
			
			// Selects the exams that match the 2 given parametric values
			String selectSQL = "SELECT * FROM VALIDATE_EXAM "
					+ " WHERE FK_ID_REPORT = ? AND NAME_EXAM = ?";
			try { 
				
				connection = DbConnection.getInstance().getConn();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				// Setting parameters
				preparedStatement.setInt(1, reportID);
				preparedStatement.setString(2, examName);
				ResultSet resSet = preparedStatement.executeQuery();

				if (resSet.next()) {	// Exam found
					exam.setReportID(resSet.getInt("FK_ID_REPORT"));
					exam.setExamName(resSet.getNString("NAME_EXAM"));
					exam.setValidatedCFU(resSet.getInt("CFU_CONVALIDATED"));
					exam.setValidationProcedure(resSet.getNString("MODE_VALIDATION"));
					exam.setVExamID(resSet.getInt("ID_EXAM_VALIDATE"));
					System.out.println("doretrieve:"+exam);
					return exam;
					
				} else {		// Exam not  present in the database
					System.out.println("Exam \"" + exam.getExamName() + "\" not present");
					preparedStatement.close();
					return null;
				}
			} catch(SQLException e) {
				System.out.println("doRetrieveValidatedExam: error while executing the query\n" + e);
				new RuntimeException("Couldn't find the exam \"" + exam.getExamName() + "\" in the database " + e);
			} finally {
				// Statement release
				if(preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return null;

			
		}
	}

	/**
	 * Retrieves all the <tt>ValidatedExam</tt> objects related to a specific <tt>Report</tt>
	 * (identified through an ID).
	 * 
	 * @param	reportID	the <tt>Report</tt> ID number that the <tt>ValidatedExam</tt> object must have
	 * @return				an <tt>ArrayList</tt> containing the <tt>ValidatedExams</tt> objects
	 * 						that match the given report ID
	 * @see	Report
	 */
	@Override
	public ArrayList<ValidatedExam> doRetrieveValidatedExamsByReportID(int reportID) {
		if (reportID < 0) { // Checks if parameter is a valid ID
			System.out.println("doRetrieveRequestRCByRequestID: Please enter a valid RequestRC ID.");
			return null;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<ValidatedExam> exams = new ArrayList<ValidatedExam>();
		ValidatedExam exam = null; 

		// Selects the RCRequest tuples that match the specified ID
		String selectSQL = "SELECT * FROM VALIDATE_EXAM "
				+ " WHERE FK_ID_REPORT = ?";
		try {
			
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);			
			// Setting the parameter
			preparedStatement.setInt(1, reportID);
			// Executing the selection
			ResultSet resSet = preparedStatement.executeQuery();

			// If exams are found construct ArrayList
			while (resSet.next()) {
				exam = new ValidatedExam();
				exam.setExamName(resSet.getNString("NAME_EXAM"));
				exam.setReportID(reportID);
				exam.setValidatedCFU(resSet.getInt("CFU_CONVALIDATED"));
				exam.setValidationProcedure(resSet.getString("MODE_VALIDATION"));
				exam.setVExamID(resSet.getInt("ID_EXAM_VALIDATE"));
				exams.add(exam);
			}
			return exams;
		} catch(SQLException e) {
			System.out.println("doRetrieveValidatedExamsByReportID: error while executing the query\n" + e);
			new RuntimeException("Couldn't retrieve the RequestRC " + reportID + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}



	@Override
	public int deleteValidatedExam(ValidatedExam vExam) {
		if (vExam.getVExamID()< 0) { // Checks if parameter is a valid ID
			System.out.println("deleteValidatedExam: Please enter a valid ExamID.");
			return -1;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = 0;
		
		String deleteSQL = "DELETE * FROM VALIDATE_EXAM WHERE ID_EXAM_VALIDATE = ?";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(deleteSQL);			
			// Setting parameter
			preparedStatement.setInt(1, vExam.getVExamID());
			//cheking if exam exist
			if(doRetrieveValidatedExam(vExam.getVExamID(),vExam.getExamName())==null) {
				System.out.println("Exam don't exist already");
				return -2;
			}
			// Executing the deletion
						
			result = preparedStatement.executeUpdate();							
			connection.commit();						
			return 0;
	} catch(SQLException e) {
		System.out.println("deleteValidatedExam: error while executing the query\n" + e);
		new RuntimeException("Couldn't delete the Exam " + e);
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
}

