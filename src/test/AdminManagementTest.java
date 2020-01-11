/**
 * 
 */
package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import controller.AdminManagement;

/**
 * @author qletto
 *
 */
class AdminManagementTest extends Mockito {
	
	
	AdminManagement am;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestDispatcher dsp;
	HttpSession sessione;


	@BeforeEach
	void setUp() throws Exception {
		am = new AdminManagement();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		sessione = mock(HttpSession.class);
		dsp = mock(RequestDispatcher.class);
	}

	@Test
	void testInvalidPDFPath() throws ServletException, IOException {
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(7));
		when(request.getSession()).thenReturn(sessione);
		when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
		am.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	void testInvalidRCRequest() throws ServletException, IOException {
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(-1));
		when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
		am.doPost(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	void testNoExamsRCRequest() throws ServletException, IOException {
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(5));
		when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
		am.doPost(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	void testNoExamsNoPDFRCRequest() throws ServletException, IOException {
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(6));
		when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
		am.doPost(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	void testEmailsSent() throws ServletException, IOException {
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(4));
		when(request.getSession()).thenReturn(sessione);
		when(request.getRequestDispatcher("/WEB-INF/GUIAdminRC/viewRCRequestAdmin.jsp")).thenReturn(dsp);
		am.doPost(request, response);
		verify(dsp).forward(request, response);
	}

}
