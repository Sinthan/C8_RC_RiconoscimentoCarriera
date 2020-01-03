package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.Downloader;
import controller.DownloaderRC;

class DownloaderRCTest {

	  private DownloaderRC servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new DownloaderRC();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void testDoGet() throws ServletException, IOException {
	    request.addParameter("pdfvalue", "12");
	    request.addParameter("pathpdf", "/DocumentsRequestRC/andrea1cella@gmail.com/CP.pdf");
	    servlet.doGet(request, response);
	  }
	  
	  @Test
	  public void testDoPost() throws ServletException, IOException {
	    request.addParameter("pdfvalue", "12");
	    request.addParameter("pathpdf", "/DocumentsRequestRC/andrea1cella@gmail.com/CP.pdf");
	    servlet.doPost(request, response);
	  }

}
