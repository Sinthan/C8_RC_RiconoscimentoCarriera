package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DbConnection;

/**
 * ReportDAO.
 *interacts with the database in order 
 *to manage the report data for a prefressa 
 *career recognition request
 */

public class ReportDAO implements ReportDAOInterface {

	/**
	* Insert the Report into the database.
	* @param Report
	* @return returns the int value of the state generated by the query execution.
	*/
	@Override
	public int insertReport(Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* Update the Report into the database.
	* @param Report
	* @return returns the int value of the state generated by the query execution.
	*/
	@Override
	public int updateReport(Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* Update the list of validated exams into the database by Report id and the new exam list.
	* @param Report
	* @param validatedExamsList is an arrayList that contains validated Report exams
	* @return returns the int value of the state generated by the query execution.
	*/
	@Override
	public int updateValidatedExamsList(int reportID, ArrayList<ValidatedExam> validatedExamsList) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* Update the note into the database by report id and the new note.
	* @param reportID is the id of the Report.
	* @param note notes contains the annotations that the PCD has inserted regarding the Report.
	* @return returns the int value of the state generated by the query execution.
	*/
	@Override
	public int updateNote(int reportID, String note) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* get Report by report id.
	* @param reportID is the id of the Report.
	* @return returns the report that has the id equal to reportID.
	*/
	@Override
	public Report doRetrieveReportByReportID(int reportID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Get the list of validated exams by Report id.
	* @param reportID is the id of the Report..
	* @return returns an set of validated exams related to the report.
	*/
	@Override
	public ArrayList<ValidatedExam> doRetrieveValidatedExamsByReportID(int reportID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes the <tt>Report</tt> that matches the specified ID, and the related records of the
	 * <tt>ValidatedExams</tt> objects from the database.
	 * 
	 * @param reportID	the ID of the <tt>Report</tt> object that will be deleted.
	 * @return			<ul><li>a positive count if the deletion succeeded
	 *					<li>0 if nothing was added to the database
	 *					<li>-1 if the deletion succeeded, but the database didn't return any information about the number of deleted rows
	 *					<li>-2 if the passed parameter is not a valid <tt>Report</tt> ID</ul>
	 * @author 			Gianluca Rossi
	 */
	@Override
	public int deleteReport(int reportID) {
		if (reportID < 0) { // Checks if parameter is a valid ID
			System.out.println("deleteReport: Please enter a valid report ID.");
			return -2;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = 0;

		/* Deletes the Report related to the a RequestRC whose ID matches the given parametric value.
		 * Deletes the relative ValidatedExam records as well.
		 */
		String deleteSQL = "DELETE FROM REPORT WHERE ID_REPORT = ?";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(deleteSQL);			
			// Setting parameter
			preparedStatement.setInt(1, reportID);
			// Executing the deletion
			result = preparedStatement.executeUpdate();	
			connection.commit();
			System.out.println("deleteReport(result=" + result + ")");		// Logging the operation
		} catch(SQLException e) {
			new RuntimeException("Couldn't delete the RequestRC " + e);
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
