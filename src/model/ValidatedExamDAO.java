package model;

import java.util.ArrayList;

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
	 * @return			0 if the insertion succeeded, -1 otherwise.
	 */
	@Override
	public int insertValidatedExam(ValidatedExam vExam) {
		return 0;
	}

	/**
	 * Updates the specified <tt>ValidatedExam</tt> object into the database.
	 * 
	 * @param	vExam	the <tt>ValidatedExam</tt> object that will be updated.
	 * @return			0 if the update succeeded, -1 otherwise.
	 */
	@Override
	public int updateValidatedExams(ValidatedExam vExam) {
		return 0;
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
		return null;
	}

	/**
	 * Retrieves all the <tt>ValidatedExam</tt> objects related to a specific <tt>Report</tt>
	 * (identified through an ID).
	 * 
	 * @param	reportID	the report ID number that the <tt>ValidatedExam</tt> object must have
	 * @return				an <tt>ArrayList</tt> containing the <tt>ValidatedExams</tt> objects
	 * 						that match the given report ID
	 * @see	Report
	 */
	@Override
	public ArrayList<ValidatedExam> doRetrieveValidatedExamsByReportID(int reportID) {
		return null;
	}
}
