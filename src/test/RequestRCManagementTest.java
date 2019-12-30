package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.RequestRCManagement;
import controller.UCRCRequestRedirector;
import model.Exam;
import model.RCState;
import model.RequestRC;
import model.Student;
import model.StudentDAO;
import model.UC;
import model.UCDAO;

class RequestRCManagementTest {

	RequestRCManagement rRCM;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	RequestRC rRC;
	
	
	@BeforeEach
	void setUp() throws Exception {
		rRCM = new RequestRCManagement();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		rRC = new RequestRC();
	}
	
	@Test
	public void returnRequestOK() {
		UCDAO ucDAO = new UCDAO();
		String email = "uc@unisa.it";
		String password = "password";
		UC userRC = ucDAO.doRetrieveUc(email, password);
		request.getSession().setAttribute("userRC",userRC);
		int requestRCID = 1;
		RequestRC rRC = requestRCDAOStub(requestRCID);
		assertNotNull(rRC);
	}
	
	@Test
	public void returnRequestFail() {
		UCDAO ucDAO = new UCDAO();
		String email = "uc@unisa.it";
		String password = "password";
		UC userRC = ucDAO.doRetrieveUc(email, password);
		request.getSession().setAttribute("userRC",userRC);
		int requestRCID = 567890;
		RequestRC rRC = requestRCDAOStub(requestRCID);
		assertNull(rRC);
	}
	

	public RequestRC requestRCDAOStub(int requestRCID) {
    Connection conn = (Connection) new DbConnection().getInstance().getConn(); 
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM request_rc "
				  + "WHERE id_request = ?");
			ps.setInt(1, requestRCID);
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
