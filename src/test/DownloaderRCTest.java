package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.Downloader;
import controller.DownloaderRC;
import model.Student;

class DownloaderRCTest extends Mockito {

	   DownloaderRC servlet;
	   HttpServletRequest request;
	   HttpServletResponse response;
	   HttpSession session;
	   RequestDispatcher requestDisp;
	   Student student;
	   File fileF;
	  
	  /**
	   * Before.
	   */
	  @BeforeEach
	  public void setUp() {
	    servlet = new DownloaderRC();
	    request = mock(HttpServletRequest.class);
	    response = mock(HttpServletResponse.class);
	    session = mock(HttpSession.class);
	    student = new Student();
	    fileF = mock(File.class);
	    requestDisp = mock(RequestDispatcher.class);
	    student.setEmail("g.rossi31@studenti.unisa.it");
	    
	  }
	  
	  @Test
	  public void testDoGet() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("pdfvalue")).thenReturn("12");
		when(request.getParameter("pathpdf")).thenReturn("EnglishValidation//WebContent//DocumentsRequestRC//g.rossi31@studenti.unisa.it//");
		when(request.getSession().getAttribute("userRC")).thenReturn(student);
		when(fileF.length()).thenReturn((long) 1);
		when(request.getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp")).thenReturn(requestDisp);
	    servlet.doGet(request, response);
	    verify(requestDisp).forward(request, response);
	  }
	  
	  @Test
	  public void testDoGetFail() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("pdfvalue")).thenReturn("12");
		when(request.getParameter("pathpdf")).thenReturn("EnglishValidation//WebContent//DocumentsRequestRC//g.rossi31@studenti.unisa.it//");
		when(request.getSession().getAttribute("userRC")).thenReturn(student);
		when(fileF.length()).thenReturn((long) 0);
		when(request.getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp")).thenReturn(requestDisp);
	    servlet.doGet(request, response);
	    verify(requestDisp).forward(request, response);
	    
	  }
	  

}
