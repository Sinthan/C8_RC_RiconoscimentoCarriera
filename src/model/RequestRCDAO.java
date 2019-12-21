package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    /**
	 * Saves the request into the database.
	 * 
	 * @param request	the <tt>RequestRC</tt> object that will be saved.
	 * @return			<ul><li>a positive count of the number of rows affected (from INSERT, UPDATE, or DELETE)
	 *					<li>0 if no rows were affected
	 *					<li>-1 if the statement succeeded, but there is no update count information available</ul>
	 *					<li>-2 if the attributes of the passed argument aren't fully specified
	 * @throws 			SQLException
	 * @author 			Gianluca Rossi
	 */
	public int insertRequestRC(RequestRC request) throws SQLException {
		if (request.getUniversityID().equals("")
				|| request.getStudentID().equals("")) // Checks if attributes are set
			return -2;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		int result = 0;

		/* Adds the 5 parametric values in the REQUEST_RC table.
		 * The Request ID is automatically generated from the
		 * database, as it is defined as an auto increment value,
		 * so it's not required to create a new one here.
		 */
		String insertSQL = "INSERT INTO REQUEST_RC " +
				" (DATE_REQUEST, STATE, FK_UNIVERSITY, FK_USER, FK_EMAIL_UC) " +
				" VALUES (?, ?, ?, ?, ?)";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(insertSQL);			
			
			// Getting today's date
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
			
			// Setting parameters
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setInt(2, RCState.needsUCValidation.ordinal());
			preparedStatement.setString(3, request.getUniversityID());
			preparedStatement.setString(4, request.getStudentID());
			preparedStatement.setString(5, "EMAILUC@gmail.it");
			
			// Logging the operation
			System.out.println("insertRequestRC: "+ request.toString());

			result = preparedStatement.executeUpdate();	
			connection.commit();
		} finally {
			// Statement release
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return result;
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
