package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import controller.StudentRCRequestRedirector;
import model.Student;

class StudentRCRequestRedirectorTest extends Mockito {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession sessione;
	RequestDispatcher requestDisp;
	StudentRCRequestRedirector sRCR;
	PrintWriter print;
	Student s;
	

	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		sessione = mock(HttpSession.class);
		requestDisp = mock(RequestDispatcher.class);
		print =  new PrintWriter("test");
		sRCR= new StudentRCRequestRedirector();
		s = new Student();
	}

	@Test
	void Flag1RequestNotNullTest() throws ServletException, IOException {
		when(response.getWriter()).thenReturn(print);
		when(request.getContextPath()).thenReturn("test");
		when(request.getParameter("flag")).thenReturn("1");
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("user")).thenReturn(s);
		s.setEmail("a.cassese9@studenti.unisa.it");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp")).thenReturn(requestDisp);
		sRCR.doGet(request, response);
		verify(requestDisp).forward(request, response);
	}
	
	@Test
	void Flag1RequestNullTest() throws ServletException, IOException {
		when(response.getWriter()).thenReturn(print);
		when(request.getContextPath()).thenReturn("test");
		when(request.getParameter("flag")).thenReturn("1");
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("user")).thenReturn(s);
		s.setEmail("a.cassese8@studenti.unisa.it");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(requestDisp);
		sRCR.doGet(request, response);
		verify(requestDisp).forward(request, response);  
	}
	
	@Test
	void Flag2SoloRedirect() throws ServletException, IOException {
		when(response.getWriter()).thenReturn(print);
		when(request.getContextPath()).thenReturn("test");
		when(request.getParameter("flag")).thenReturn("2");
		when(request.getRequestDispatcher("/_areaStudent/viewRequest.jsp")).thenReturn(requestDisp);
		sRCR.doGet(request, response);
		verify(requestDisp).forward(request, response);  
	}

}
