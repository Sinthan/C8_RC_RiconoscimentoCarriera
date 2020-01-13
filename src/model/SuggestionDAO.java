package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		int flag = 0;
		return flag;
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
	
	/**
	 * Delete old suggest       
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteOldSuggestions() {
	
		int flag = 0;
		return flag;	
	}
	
}
