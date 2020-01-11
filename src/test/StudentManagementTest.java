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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.AfterEach;
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

import controller.StudentManagement;

import model.FilePDF;

import controller.Utils;
import model.RCState;

import model.RequestRC;
import model.RequestRCDAO;
import model.Student;

import static org.junit.Assert.assertEquals;

class StudentManagementTest extends Mockito {
	
	StudentManagement sm;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestDispatcher dsp;
	HttpSession sessione; 
	Part part1;
	Part part2;
	Student s;
	FilePDF filePdf;
	RequestRC rRC;
	RequestRCDAO reqDAO;
	String pdfSaveFolder = "/DocumentsRequestRC";
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
		s.setEmail("g.rossi@studenti.unisa.it");
		rRC = new RequestRC();
		rRC.setStudentID(s.getEmail());
		rRC.setUniversityID("Universit� degli Studi di SALERNO");
		reqDAO =  new RequestRCDAO();
	}
	
	//@Test
	public void returnRequestStatusOK() throws ServletException, IOException {
		s.setEmail("g.damiano@studenti.unisa.it");
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
	
	//@Test
	public void returnUniversityList() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione);
		when(sessione.getAttribute("flag")).thenReturn(1);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	//@Test
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
	
	//@Test
	public void createRequestRC1Test2() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(2);
		when(request.getPart("file1")).thenReturn(part);
		when(request.getPart("file2")).thenReturn(part);
		when(request.getParameter("universita")).thenReturn("Universit� degli Studi di NAPOLI Federico II");
		when(filePart1.getContentType()).thenReturn("text");
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
		when(request.getParameter("universita")).thenReturn("Universit� degli Studi di NAPOLI Federico II");
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
        when(request.getParameter("universita")).thenReturn("Universit� degli Studi di NAPOLI Federico II");
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
	
	@Test
	public void createRequestRC2NoPreviousPageFilePDF1Data() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF1")).thenReturn("lol");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2NoPreviousPageRequestRCData() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn(null);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2NoPreviousPageFilePDF2Data() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF1")).thenReturn(null);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2InvalidExamName() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF1")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF2")).thenReturn("lol");
		when(request.getParameter("rowCount")).thenReturn("1");
		int currentExamRow = 1;
		// TODO
		when(request.getParameter("examName" + currentExamRow)).thenReturn("||AnalisiI*£#");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("-2");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2InvalidCFU() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF1")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF2")).thenReturn("lol");
		when(request.getParameter("rowCount")).thenReturn("1");
		int currentExamRow = 1;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Analisi I");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("99");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2InvalidExamLink() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF1")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF2")).thenReturn("lol");
		when(request.getParameter("rowCount")).thenReturn("1");
		int currentExamRow = 1;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Analisi I");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("9");
		when(request.getParameter("programLink" + currentExamRow)).thenReturn("//www.unisa.it");
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@Test
	public void createRequestRC2Ok() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(3);
		when(request.getSession().getAttribute("newRequestRC")).thenReturn(rRC);
		when(request.getSession().getAttribute("filePDF1")).thenReturn("lol");
		when(request.getSession().getAttribute("filePDF2")).thenReturn("lol");
		when(request.getParameter("rowCount")).thenReturn("4");
		int currentExamRow = 1;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Analisi Matematica I");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("9");
		when(request.getParameter("programLink" + currentExamRow)).thenReturn("https://unisa.it");
		currentExamRow = 2;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Algoritmi e strutture dati I");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("9");
		when(request.getParameter("programLink" + currentExamRow)).thenReturn("http://cs-informatica.dieti.unina.it/index.php/it/corsi-di-laurea/insegnamenti/laurea-triennale/15-corsi-di-laurea/corsi/148-algoritmi-e-strutture-dati-i");
		currentExamRow = 3;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Programmazione 1");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("12");
		when(request.getParameter("programLink" + currentExamRow)).thenReturn("www.sito.it/dati/esame/prog1");
		currentExamRow = 4;
		when(request.getParameter("examName" + currentExamRow)).thenReturn("Chimica");
		when(request.getParameter("CFU" + currentExamRow)).thenReturn("9");
		when(request.getParameter("programLink" + currentExamRow)).thenReturn("ftp://unisa.it");
		//when(request.getAttribute("newRequestRC")).thenReturn(rRC);
		//rRC = reqDAO.doRetrieveRequestRCByStudentID("g.rossi@studenti.unisa.it");
		int reqRCID = rRC.getRequestRCID();
		FilePDF file = new FilePDF("/WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "fake.pdf", reqRCID);
		when(request.getSession().getAttribute("filePDF1")).thenReturn(file);
		when(request.getSession().getAttribute("filePDF2")).thenReturn(file);
		
		
		//reqDAO.deleteRequestRCByRequestID(reqRCID);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp")).thenReturn(dsp);
		sm.doGet(request, response);
		verify(dsp).forward(request, response);
	}
	
	@AfterEach
	public void tearDown() {
		reqDAO.deleteRequestRCByStudentID(s.getEmail());
	}

	@Test
	public void viewRequestRCStatus() throws ServletException, IOException {
		when(request.getSession()).thenReturn(sessione); 
		when(sessione.getAttribute("flag")).thenReturn(4);
		when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp")).thenReturn(dsp);
		sm.doPost(request, response);
		verify(dsp).forward(request, response);
	}
}
