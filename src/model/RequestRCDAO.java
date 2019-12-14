package model;

import java.util.ArrayList;
import model.RequestRCDAOInterface;

/*
 * RequestRCDAO
 * Class that retrieves and manages
 * informations about the RequestRC through the Database
 */
public class RequestRCDAO implements RequestRCDAOInterface{
	
	
	@Override
	public int insertRequestRC() {
		return 0;
	}	
	
	
	@Override
	public int updateRequestRC() {
		return 0;
	}	
	

	@Override
	public int updateReportID() {
		return 0;
	}	
	
	
	@Override
	public int updateState() {
		return 0;
	}	
	

	@Override
	public ArrayList<RequestRC> doRetrieveAllRequestRCExams(){
		return null;
	}	
	
	
	@Override
	public RequestRC doRetrieveRequestRCByRequestID(){
		return null;
	}
		
	
	@Override
	public RequestRC doRetrieveRequestRCByStudentID(){
		return null;
	}
	
	
	@Override
	public int deleteRequestRCByRequestID() {
		return 0;
	}
		
	
	@Override
	public int deleteRequestRCByStudentID() {
		return 0;
	}
	
	
}
