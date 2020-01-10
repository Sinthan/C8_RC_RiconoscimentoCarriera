package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import controller.DbConnection;
import controller.StudentManagement;
import model.RCState;
import model.RequestRC;
import model.Student;

class StudentManagementTest extends Mockito {
	
	StudentManagement sm;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestDispatcher dsp;
	HttpSession sessione; 
	Part part;
	Part filePart1;
	Part filePart2;
	File file1 = new File("file1.pdf");
	File file2 = new File("file2.pdf");
	Student s;
	RequestRC rRC;
	
	

	@BeforeEach
	void setUp() throws Exception {
		sm = new StudentManagement();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		sessione = mock(HttpSession.class);
		dsp = mock(RequestDispatcher.class);
		part = mock(Part.class);
		s= new Student();
		rRC = new RequestRC();
	}
	
	@Test
	public void returnRequestStatusOK() throws ServletException, IOException {
		s.setEmail("g.damiano@studenti.unisa.it");
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("flag")).thenReturn(0);
		when(sessione.getAttribute("user")).thenReturn(s);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void returnUniversityList() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("flag")).thenReturn(1);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC1Test() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getAttribute("file1")).thenReturn(part);
		when(request.getAttribute("file2")).thenReturn(part);
		when(request.getParameter("universita")).thenReturn("defaultUni");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC1Test2() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getPart("file1")).thenReturn(part);
		when(request.getPart("file2")).thenReturn(part);
		when(request.getParameter("universita")).thenReturn("Università degli Studi di NAPOLI Federico II");
		when(filePart1.getContentType()).thenReturn("text");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
}
