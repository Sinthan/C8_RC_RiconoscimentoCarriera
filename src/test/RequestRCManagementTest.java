package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.RequestRCManagement;
import controller.UCRCRequestRedirector;
import model.Admin;
import model.Exam;
import model.RCState;
import model.RequestRC;
import model.Student;
import model.StudentDAO;
import model.UC;
import model.UCDAO;

class RequestRCManagementTest extends Mockito {

	RequestRCManagement rRCM;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	RequestDispatcher requestDisp;
	RequestRC rRC;
	UC uc;
	Admin admin;
	Exam exam;
	File fileM;
	ArrayList<Exam> exams;
	Student student;
	
	
	@BeforeEach
	void setUp() throws Exception {
		rRCM = new RequestRCManagement();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		requestDisp = mock(RequestDispatcher.class);
		session = mock(HttpSession.class);
		rRC = mock(RequestRC.class);
		uc = mock(UC.class);
		admin = mock(Admin.class);
		exam = new Exam();
		exams = new ArrayList<Exam>();
		student = new Student();
		fileM = mock(File.class);
		
		//aggiunta di un esame all'arrayList
		//exams.add(exam);
		
	}
	
	/*@Test
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
	}*/
	
	@BeforeEach
	public void setUpSystem() {	
		String user =System.getProperty("user.name");
		System.setProperty("catalina.base","C:\\Users\\"+user+"\\ISworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0");
	}
	
	@Test
	public void userIsUCStatusRCTrueTest() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("user")).thenReturn(uc);
		when(request.getSession().getAttribute("reqRC")).thenReturn(rRC);
		when(request.getRequestDispatcher("/UCManagement")).thenReturn(requestDisp);
		when(request.getParameter("RequestRCstate")).thenReturn("true");
		when(request.getSession().getAttribute("exams")).thenReturn(exams);
		rRCM.doGet(request, response);
		verify(requestDisp).forward(request, response);	
	}
	
	@Test
	public void userIsUCStatusRCFalseTest() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("user")).thenReturn(uc);
		when(request.getSession().getAttribute("reqRC")).thenReturn(rRC);
		when(request.getRequestDispatcher("/UCManagement")).thenReturn(requestDisp);
		when(request.getParameter("RequestRCstate")).thenReturn("false");
		when(request.getParameter("popupText")).thenReturn("rifiuto test");
		when(request.getSession().getAttribute("userRC")).thenReturn(student);
		rRCM.doGet(request, response);
		verify(requestDisp).forward(request, response);	
	}
	
	@Test
	public void userIsAdminTestFileEx() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("user")).thenReturn(admin);
		when(request.getParameter("requestRCID")).thenReturn("7"); 
		when(request.getParameter("recipient-name")).thenReturn("testmail@unisa.it");
		when(request.getParameter("message-text")).thenReturn("test test test");
		when(request.getParameter("exam-selected")).thenReturn("esameTest");
		when(request.getSession().getAttribute("examList")).thenReturn(exams);
		when(fileM.exists()).thenReturn(true);
		rRCM.doGet(request, response);
	}
	
	@Test
	public void userIsAdminTestFileNotEx() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("user")).thenReturn(admin);
		when(request.getParameter("requestRCID")).thenReturn("7"); 
		when(request.getParameter("recipient-name")).thenReturn("testmail@unisa.it");
		when(request.getParameter("message-text")).thenReturn("test test test");
		when(request.getParameter("exam-selected")).thenReturn("esameTest");
		when(request.getSession().getAttribute("examList")).thenReturn(exams);
		when(fileM.exists()).thenReturn(false);
		rRCM.doGet(request, response);
	}
	
}
