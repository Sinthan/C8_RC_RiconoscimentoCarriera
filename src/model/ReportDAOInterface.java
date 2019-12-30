package model;

import java.util.ArrayList;

/**
 * ReportDAOInterface.
 * Provides the interface to ReportDAO.
 */

public interface ReportDAOInterface {
	
	/**
	* Insert the Report into the database.
	* @param Report
	* @return returns the int value of the state generated by the query execution.
	*/
	public int insertReport(Report report);
	
	/**
	* Update the Report into the database.
	* @param Report
	* @return returns the int value of the state generated by the query execution.
	*/
	public int updateReport(Report report);
	
	
	/**
	* Update the list of validated exams into the database by Report id and the new exam list.
	* @param Report
	* @param validatedExamsList is an arrayList that contains validated Report exams
	* @return returns the int value of the state generated by the query execution.
	*/
	
	public int updateValidatedExamsList(int reportID, ArrayList<ValidatedExam> validatedExamsList);
	
	/**
	* Update the note into the database by report id and the new note.
	* @param reportID is the id of the Report.
	* @param note notes contains the annotations that the PCD has inserted regarding the Report.
	* @return returns the int value of the state generated by the query execution.
	*/
	public int updateNote(int reportID, String note);
	
	/**
	* get Report by report id.
	* @param reportID is the id of the Report.
	* @return returns the report that has the id equal to reportID.
	*/
	public Report doRetrieveReportByReportID(int reportID);
	
	/**
	* Get the list of validated exams by Report id.
	* @param reportID is the id of the Report..
	* @return returns an set of validated exams related to the report.
	*/
	public ArrayList<ValidatedExam> doRetrieveValidatedExamsByReportID(int reportID);
	
	/**
	* Delete Report into the database by Report id.
	* @param reportID is the id of the Report.
	* @return returns the int value of the state generated by the query execution.
	*/
	public int deleteReport(int reportID);	
}
