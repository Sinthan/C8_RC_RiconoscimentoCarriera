package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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





public class RequestRCDAO implements RequestRCDAOInterface {

	Statement stmt = null;
	String sql = "";
	String error;
	@SuppressWarnings("static-access")
	

	Connection conn = (Connection) new DbConnection().getInstance().getConn();
	
    @Override
	public int insertRequestRC(RequestRC request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRequestRC(RequestRC request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReportID(int reportID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateState(int state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<RequestRC> doRetrieveAllRequestRCExams(int requestRCID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestRC doRetrieveRequestRCByRequestID(int requestRCID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestRC doRetrieveRequestRCByStudentID(String studentID) {
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
	public int deleteRequestRCByRequestID(int requestRCID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRequestRCByStudentID(int syudentID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
