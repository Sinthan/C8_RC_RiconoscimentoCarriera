package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.mockito.Mockito;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.StudentManagement;
import controller.ViewReportAdminServlet;
import model.Report;
import model.ReportDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.StudentDAO;

class ViewReportAdminServletTest extends Mockito {

	ViewReportAdminServlet vras;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestDispatcher dsp;
	HttpSession sessione; 
	Student s;
	StudentDAO sDao;
	RequestRC rRC;
	RequestRCDAO rDao;
	Report report;
	ReportDAO reportDao;
	String pdfSaveFolder = "/DocumentsRequestRC";
	String pathC;
	String date;
	SimpleDateFormat sdf;
	String SAVE_DIR2;
	
	@BeforeEach
	void setUp() throws Exception {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		System.out.println(root);
		String projectFolder = "C8_RC_RiconoscimentoCarriera";
		int extraPathIndex = root.toString().indexOf(projectFolder);
		String catalinaRoot = root.toString().substring(0, extraPathIndex -1);
		System.setProperty("catalina.base", catalinaRoot + "/.metadata/");
		vras = new ViewReportAdminServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		sessione = mock(HttpSession.class);
		dsp = mock(RequestDispatcher.class);
		s= new Student();
		s.setEmail("g.rossi@studenti.unisa.it");
		rRC = new RequestRC();
		rRC.setStudentID(s.getEmail());
		rRC.setUniversityID("Università degli Studi di SALERNO");
		// Getting today's date
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		rRC.setSubmissionDate(sqlDate);
		rDao =  new RequestRCDAO();
		sDao = new StudentDAO();
	}
	
	@Test
	public void testReport1() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		when((request.getParameter("idRequestRC"))).thenReturn("1");
		s = sDao.doRetrieveStudentByEmail("g.rossi@studenti.unisa.it");
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("user")).thenReturn(s);
		when(request.getRequestDispatcher("/WEB-INF/GUIAdminRC/createReport.jsp")).thenReturn(dsp);
		vras.doGet(request, response);
	}
	
	@Test
	public void testReportWith() throws ServletException, IOException {
		
		s = sDao.doRetrieveStudentByEmail("g.c@studenti.unisa.it");
		rRC = rDao.doRetrieveRequestRCByStudentID(s.getEmail());
		//report = reportDao.doRetrieveReportByReportID(rRC.getReportID());
		
		when(request.getSession()).thenReturn(sessione);
		when((request.getParameter("idRequestRC"))).thenReturn(Integer.toString(rRC.getRequestRCID()));
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("user")).thenReturn(s);
		when(request.getRequestDispatcher("/WEB-INF/GUIAdminRC/createReport.jsp")).thenReturn(dsp);
		vras.doGet(request, response);
	}
	
	@Test
	public void testReportWithoutReport() throws ServletException, IOException {
		
		s = sDao.doRetrieveStudentByEmail("aaa@aaa.com");
		rRC = rDao.doRetrieveRequestRCByStudentID(s.getEmail());
		
		when(request.getSession()).thenReturn(sessione);
		when(request.getParameter("idRequestRC")).thenReturn(Integer.toString(rRC.getRequestRCID()));
		when(request.getSession()).thenReturn(sessione); 
		rRC.setReportID(0);
		
		when(request.getRequestDispatcher("/WEB-INF/GUIAdminRC/createReport.jsp")).thenReturn(dsp);
		vras.doGet(request, response);
	}
	
	@AfterClass
	public void afterC(){
		s = sDao.doRetrieveStudentByEmail("aaa@aaa.com");
		rRC = rDao.doRetrieveRequestRCByStudentID(s.getEmail());
		reportDao.deleteReport(rRC.getReportID());
	}

}
