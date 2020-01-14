package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import controller.DbConnection;
import model.Suggestion;

	/**
	 * SuggestionDAO is used for communication between Student and DB
	 *
	 */
public class SuggestionDAO implements SuggestionDAOInterface {

	/**
	 * Insert new suggestion  
	 * 
	 * @return -1 if insert failed, 0 if insert ok
	 */
	public int insertSuggestion(Suggestion suggestion) {
		
		if(suggestion==null) {
			System.out.println("insertSuggestion: passed null Sugestion");
			return-1;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = -2; // No rows affected by default

		String insertSQL = "INSERT INTO SUGGESTION " +
				" (NAME_UNIVERSITY, NAME_EXAM_EXTERN, NUMBER_CFU_EXTERN, VALIDATED_CFU, MODE_VALIDATION, DATE_VALIDATION) " +
				" VALUES (?, ?, ?, ?, ?, ?)";

		// Selects the sugestion that match the 3 given parametric values
		String selectSQL = "SELECT * FROM SUGGESTION "
				+ " NAME_UNIVERSITY = ? AND NAME_EXAM_EXTERN = ? AND NUMBER_CFU_EXTERN = ?";
		try {  
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);
			java.sql.Date date = (Date) suggestion.getValidationDate();
			// Setting parameters
			preparedStatement.setString(1,suggestion.getUniversityName());
			preparedStatement.setString(2, suggestion.getExamName());
			preparedStatement.setInt(3,suggestion.getExternalStudentCFU());

			ResultSet resSet = preparedStatement.executeQuery();

			if (resSet.first()) {	// suggestion found
				int examID = resSet.getInt(1);
				System.out.println("suggestion already present.");
				return examID;
			} else {		// Suggestion not already present in the database, proceeding with the insertion
				System.out.println("Suggestion not present, adding it to the database.");
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(insertSQL);			
				// Setting parameters
				preparedStatement.setString(1,suggestion.getUniversityName());
				preparedStatement.setString(2, suggestion.getExamName());
				preparedStatement.setInt(3,suggestion.getExternalStudentCFU());
				preparedStatement.setInt(4,suggestion.getValidatedCFU());
				preparedStatement.setString(5,suggestion.getValidationMode());
				preparedStatement.setDate(6, date);
				// Executing the insertion
				result = preparedStatement.executeUpdate();	
				connection.commit();
			}
		} catch(SQLException e) {
			System.out.println("insertSuggstion: error while executing the query\n" + e);
			new RuntimeException("Couldn't insert the Suggestion in the database " + e);
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
		System.out.println("insertSuggestion(result=" + result + ": " + suggestion.toString());	// Logging the operation
		return result;
	}
	
	
	/**
	 * Retrieves the <tt>Suggestion</tt> object that matches the given exam name and university
	 * 
	 * @param	univerityName	the <tt>University</tt> name that the <tt>Suggestion</tt> object must match
	 * @param	examName		the <tt>Exam</tt> name that the <tt>Suggestion</tt> object must match
	 * @return					the <tt>Suggestion</tt> object if found, null otherwise
	 * @author 	Gianluca Rossi
	 */
	@Override
	public Suggestion doRetrieveSuggestionByName(String universityName, String examName) {
		if (universityName.equals("")
				|| examName.equals("")) { // Checks if parameter is a valid ID
			System.out.println("doRetrieveSuggestionByName: Please enter a valid name for the university and/or the exam.");
			return null;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Suggestion sugg = null;

		// Selects the Suggestion tuples that match the specified university and exam names
		String selectSQL = "SELECT * FROM SUGGESTION "
				+ " WHERE NAME_UNIVERSITY = ? AND NAME_EXAM_EXTERN = ?";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);			
			// Setting the parameters
			preparedStatement.setString(1, universityName);
			preparedStatement.setString(2, examName);
			// Executing the selection
			ResultSet resSet = preparedStatement.executeQuery();
			
			// If a Suggestion is found construct it
			if (resSet.next()) {
				sugg = new Suggestion();
				sugg.setExamName(examName);
				sugg.setUniversityName(universityName);
				sugg.setExternalStudentCFU(resSet.getInt("NUMBER_CFU_EXTERN"));
				sugg.setValidatedCFU(resSet.getInt("VALIDATED_CFU"));
				sugg.setValidationMode(resSet.getString("MODE_VALIDATION"));
				sugg.setValidationDate(resSet.getDate("DATE_VALIDATION"));
			}
		} catch(SQLException e) {
			System.out.println("doRetrieveSuggestionByName: error while executing the query\n" + e);
			new RuntimeException("Couldn't retrieve the Suggestion for the exam " + examName + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return sugg;
	}
	@Override
	public Suggestion doRetrieveSuggestionByName(String universityName, String examName, int CFU ) {
		if (universityName.equals("")
				|| examName.equals("") || CFU==0) { // Checks if parameter is a valid ID
			System.out.println("doRetrieveSuggestionByName: Please enter a valid name for the university and/or the exam.");
			return null;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Suggestion sugg = null;

		// Selects the Suggestion tuples that match the specified university and exam names
		String selectSQL = "SELECT * FROM SUGGESTION "
				+ " WHERE NAME_UNIVERSITY = ? AND NAME_EXAM_EXTERN = ? AND NUMBER_CFU_EXTERN = ?";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);			
			// Setting the parameters
			preparedStatement.setString(1, universityName);
			preparedStatement.setString(2, examName);
			preparedStatement.setInt(3, CFU);
			
			
			// Executing the selection
			ResultSet resSet = preparedStatement.executeQuery();
			
			// If a Suggestion is found construct it
			if (resSet.next()) {
				sugg = new Suggestion();
				sugg.setExamName(examName);
				sugg.setUniversityName(universityName);
				sugg.setExternalStudentCFU(resSet.getInt("NUMBER_CFU_EXTERN"));
				sugg.setValidatedCFU(resSet.getInt("VALIDATED_CFU"));
				sugg.setValidationMode(resSet.getString("MODE_VALIDATION"));
				sugg.setValidationDate(resSet.getDate("DATE_VALIDATION"));
			}
		} catch(SQLException e) {
			System.out.println("doRetrieveSuggestionByName: error while executing the query\n" + e);
			new RuntimeException("Couldn't retrieve the Suggestion for the exam " + examName + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return sugg;
	}
	
	/**
	 * Updates the specified <tt>ValidatedExam</tt> object into the database.
	 * 
	 * @param	vExam	the <tt>ValidatedExam</tt> object that will be updated.
	 * @return			<ul><li>a positive count if the update succeeded
	 *					<li>0 if nothing was altered into the database
	 *					<li>-1 if the update succeeded, but the database didn't return any information about the number of updated rows
	 *					<li>-2 if the attributes of the passed argument aren't fully specified</ul>
	 *@author 	Lorenzo Maturo
	 */
	@Override
	public int updateSuggestion(Suggestion sugg) {

		if(sugg==null) {
			System.out.println( "updateSuggestion: Void sugg passed" );
			return -1;
		} else if( doRetrieveSuggestionByName(sugg.getUniversityName(), sugg.getExamName(), sugg.getExternalStudentCFU() )==null){
			System.out.println( "updateSuggestion: sugg doesn't exists in DB" );
			return -2;
		}else {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			// Selects the sugg 
			String updateSQL = "UPDATE suggestion SET VALIDATED_CFU = ?, MODE_VALIDATION = ?, DATE_VALIDATION = ?" 
					+ " WHERE ( NAME_UNIVERSITY = ? AND NAME_EXAM_EXTERN = ? AND NUMBER_CFU_EXTERN = ? ); ";
			try { 
				
				connection = DbConnection.getInstance().getConn();
				preparedStatement = connection.prepareStatement(updateSQL);
				
				//Set Data
				Calendar calendar = Calendar.getInstance();
				java.util.Date currentDate = calendar.getTime();
				java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
				
				// Setting parameters
				preparedStatement.setInt(1, sugg.getValidatedCFU());
				preparedStatement.setString(2, sugg.getValidationMode());
				preparedStatement.setDate(3, sqlDate);
				preparedStatement.setString(4, sugg.getUniversityName());
				preparedStatement.setString(5, sugg.getExamName());
				preparedStatement.setInt(6, sugg.getExternalStudentCFU());
				preparedStatement.executeUpdate();
				connection.commit();
					
				
			} catch(SQLException e) {
				System.out.println("updateSuggestion: error while executing the query\n" + e);
				new RuntimeException("Couldn't find the sugg \"" + sugg.getExamName() + "\" in the database " + e);
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
	 * Delete old suggest       
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteOldSuggestions() {
	
		int flag = 0;
		return flag;	
	}
	
}
