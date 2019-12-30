package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.StudentManagement;
import controller.UCRCRequestRedirector;
import model.Exam;
import model.RCState;
import model.RequestRC;

class UCRCRequestRedirectorTest extends Mockito {
	  
	
	UCRCRequestRedirector uc;
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	RequestRC rRC;
	Exam e;
	
	

	@BeforeEach
	void setUp() throws Exception {
		uc = new UCRCRequestRedirector();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		rRC = new RequestRC();
		e = new Exam();
	}
	
	@Test
	public void returnRequestStudentOK() {
		String studentID = "a.cella8@studenti.unisa.it";
		RequestRC rRC = requestRCDAOStub(studentID);
		assertNotNull(rRC);
	}
	
	@Test
	public void returnRequestStudentFail() {
		String studentID = "a.dfgsbnmdl@studenti.unisa.it";
		RequestRC rRC = requestRCDAOStub(studentID);
		assertNull(rRC);
	}
	
	@Test
	public void returnRequestExamsOK() {
		int examID = 1;
		ArrayList<Exam> list = examDAOStub(examID);
		assertNotNull(rRC);
	}
	
	@Test
	public void returnRequestExamsFail() {
		int examID = 456789;
		ArrayList<Exam> list = examDAOStub(examID);
		assertNotNull(rRC);
	}
	
	

	// Stub che preleva gli esami dal DB
	public ArrayList<Exam> examDAOStub(int examID) {
		ArrayList<Exam> list = new ArrayList<Exam>();
		try {
			Connection connection = DbConnection.getInstance().getConn();
			PreparedStatement ps = connection.prepareStatement(
					" SELECT  * FROM exam " 
							+ "WHERE id_exam  = ?");
			ps.setInt(1, examID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exam e = new Exam();
				e.setExamID(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setCFU(rs.getInt(3));
				e.setProgramLink(rs.getString(4));
				list.add(e);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}


	// Stub che preleva una richiesta dal DB
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
