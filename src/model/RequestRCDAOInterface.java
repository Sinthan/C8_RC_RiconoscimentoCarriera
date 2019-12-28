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
	public int insertRequestRC(RequestRC request);
	
	
	/*
	 * Update information of requestRC
	 */
	public int updateRequestRC(RequestRC request);
	
	
	/*
	 * Update report's ID
	 */
	public int updateReportID(int reportID);
	
	
	/*
	 * Update the state of the request
	 */
	public int updateState(int state);
	
	/*
	 * Retrieve all requestRCs from the database
	 */
	public ArrayList<RequestRC> doRetrieveAllRequestRCBystate(State state);
	
	/*
	 *Retrieve one specific requestRC using ID of the request 
	 */
	public RequestRC doRetrieveRequestRCByRequestID(int requestRCID);
	
	
	/*
	 *Retrieve one specific requestRC using ID of the student 
	 */
	public RequestRC doRetrieveRequestRCByStudentID(String studentID);
	
	
	/*
	 *Delete one specific requestRC using ID of the request 
	 */
	public int deleteRequestRCByRequestID(int requestRCID);
	
	
	/*
	 *Delete one specific requestRC using ID of the student
	 */
	public int deleteRequestRCByStudentID(int syudentID);

	

}
