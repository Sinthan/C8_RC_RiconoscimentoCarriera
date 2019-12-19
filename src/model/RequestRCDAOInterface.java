package model;

import java.util.ArrayList;

/*
 * RequestRCDAOInterface
 * Interface for the RequestRCDAO that
 * retrieve and manages informations from the RequestRCDAO
 */
public interface RequestRCDAOInterface {
	
	/*
	 * Add new requestRC in the database
	 */
	public int insertRequestRC();
	
	
	/*
	 * Update information of requestRC
	 */
	public int updateRequestRC();
	
	
	/*
	 * Update report's ID
	 */
	public int updateReportID();
	
	
	/*
	 * Update the state of the request
	 */
	public int updateState();
	
	
	/*
	 * Retrieve all the requestRCs from the database
	 */
	public ArrayList<RequestRC> doRetrieveAllRequestRCExams();
	
	
	/*
	 *Retrieve one specific requestRC using ID of the request 
	 */
	public RequestRC doRetrieveRequestRCByRequestID();
	
	
	/*
	 *Retrieve one specific requestRC using ID of the student 
	 */
	public RequestRC doRetrieveRequestRCByStudentID(String studentID);
	
	
	/*
	 *Delete one specific requestRC using ID of the request 
	 */
	public int deleteRequestRCByRequestID();
	
	
	/*
	 *Delete one specific requestRC using ID of the student
	 */
	public int deleteRequestRCByStudentID();
	

}
