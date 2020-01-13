package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
	   Student student;
	  
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
	    student.setEmail("g.rossi31@studenti.unisa.it");
	    
	  }
	  
	  @Test
	  public void testDoGet() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("pdfvalue")).thenReturn("12");
		when(request.getParameter("pathpdf")).thenReturn("EnglishValidation//WebContent//DocumentsRequestRC//g.rossi31@studenti.unisa.it//");
		when(request.getSession().getAttribute("userRC")).thenReturn(student);
	    servlet.doGet(request, response);
	  }
	  

}
