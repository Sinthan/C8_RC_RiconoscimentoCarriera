package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import controller.DbConnection;
import controller.StudentManagement;
import controller.Utils;
import model.RCState;
import model.RequestRC;
import model.Student;

//import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(System.class)
class StudentManagementTest extends Mockito {
	
	StudentManagement sm;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestDispatcher dsp;
	HttpSession sessione; 
	Part part1;
	Part part2;
	Student s;
	RequestRC rRC;
	String pathC;
	String date;
	SimpleDateFormat sdf;

	@BeforeEach
	void setUp() throws Exception {
		sm = new StudentManagement();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		sessione = mock(HttpSession.class);
		dsp = mock(RequestDispatcher.class);
		part1 = mock(Part.class);
		part2 = mock(Part.class);
		s= new Student();
		rRC = mock(RequestRC.class);
		pathC = System.getProperty("catalina.base");
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		date = new String();
	}
	
	@Test
	public void returnRequestStatusOK1() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		s.setEmail("ggg@studenti.unisa.it");
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(0);
		when(sessione.getAttribute("user")).thenReturn(s);
		when(request.getRequestDispatcher("/_areaStudent/signUp.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void returnRequestStatusOK2() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		s.setEmail("g.rossi31@studenti.unisa.it");
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
		when(request.getAttribute("file1")).thenReturn(part1);
		when(request.getAttribute("file2")).thenReturn(part2);
		when(request.getParameter("universita")).thenReturn("defaultUni");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC1Test2() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getPart("file1")).thenReturn(part1);
		when(request.getPart("file2")).thenReturn(part2);
		when(request.getParameter("universita")).thenReturn("Università degli Studi di NAPOLI Federico II");
		when(part1.getContentType()).thenReturn("application/xml");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC1Test3() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getPart("file1")).thenReturn(part1);
		when(request.getPart("file2")).thenReturn(part2);
		when(request.getParameter("universita")).thenReturn("Università degli Studi di NAPOLI Federico II");
		when(part1.getContentType()).thenReturn("application/pdf");
		when(part2.getContentType()).thenReturn("application/xml");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	

	
	@Test
	public void createRequestRC1Test4() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getPart("file1")).thenReturn(part1);
		when(request.getPart("file2")).thenReturn(part2);
		when(request.getParameter("universita")).thenReturn("Università degli Studi di NAPOLI Federico II");
		when(part1.getContentType()).thenReturn("application/pdf");
		when(part2.getContentType()).thenReturn("application/pdf");
		when(sessione.getAttribute("user")).thenReturn(s);
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("catalina.base")).thenReturn(pathC);
		assertEquals(pathC, System.getProperty("catalina.base"));
		
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
		
	}
	
}
