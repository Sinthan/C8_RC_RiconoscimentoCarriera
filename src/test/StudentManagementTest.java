package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.StudentManagement;
import model.RCState;
import model.RequestRC;

class StudentManagementTest extends Mockito {
	
	StudentManagement sm;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	RequestRC rRC;
	
	

	@BeforeEach
	void setUp() throws Exception {
		sm = new StudentManagement();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		rRC = new RequestRC();
	}
	
	@Test
	public void returnRequestStatusOK() {
		String studentID = "g.damiano@studenti.unisa.it";
		RequestRC rRC = requestRCDAOStub(studentID);
		assertNotNull(rRC);
		
	}
	
	@Test
	public void returnRequestStatusFail() {
		String studentID = "a.cassese9@studenti.unisa.it";
		RequestRC rRC = requestRCDAOStub(studentID);
		assertNull(rRC);
		
	}
	
	// stub che preleva una richiesta dal DB
	public RequestRC requestRCDAOStub(String studentID) {
		
		Connection conn = (Connection) new DbConnection().getInstance().getConn(); 
		
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

}
