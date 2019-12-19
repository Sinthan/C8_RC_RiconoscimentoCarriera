package model;

import java.util.ArrayList;

import model.RequestRC.RCState;
import model.RequestRCDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.DbConnection;

/*
 * RequestRCDAO
 * Class that retrieves and manages
 * informations about the RequestRC through the Database
 */
public class RequestRCDAO implements RequestRCDAOInterface{
	
	Statement stmt = null;
	String sql = "";
	String error;
	@SuppressWarnings("static-access")
	
	Connection conn = (Connection) new DbConnection().getInstance().getConn(); 
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
	public RequestRC doRetrieveRequestRCByStudentID(String studentID){
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM request_rc "
				  + "WHERE fk_user = ?");
			ps.setString(1, studentID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestRC r = new RequestRC();
				r.setRequestRCID(rs.getInt(1));
				r.setSubmissionDate(rs.getDate(2));
				r.setState(RCState.fromInteger(rs.getInt(3)));
				r.setUniversityID(rs.getString(4));
				r.setStudentID(rs.getString(5));
				r.setReportID(rs.getInt(6));
				return r;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
