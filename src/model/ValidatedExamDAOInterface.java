package model;

import java.util.ArrayList;

/**
 * <tt>ValidatedExamDAOInterface</tt>
 * <p>
 * The DAO interface for the various implementations of ValidatedExamDAO.
 * Defines the CRUD operations.
 * 
 * @author	Gianluca Rossi
 * */

public interface ValidatedExamDAOInterface {

	public int insertValidatedExam(ValidatedExam vExam);
	
	public int updateValidatedExams(ValidatedExam vExam);
	
	public ValidatedExam doRetrieveValidatedExam(int reportID, String examName);
	
	public ArrayList<ValidatedExam> doRetrieveValidatedExamsByReportID(int reportID);
}
