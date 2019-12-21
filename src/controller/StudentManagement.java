package controller;


import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RequestRCDAO;
import model.Student;
import model.RequestRC;

import javax.servlet.http.Part;

import org.apache.catalina.User;
import org.json.simple.JSONObject;

import com.itextpdf.text.log.SysoCounter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ContainsRelation;
import model.ContainsRelationDAO;
import model.Exam;
import model.ExamDAO;
import model.FilePDF;
import model.FilePDFDAO;
import model.RequestRC;
import model.SortByName;
import model.Student;
import model.University;
import model.UniversityDAO;
import java.util.Collections;
import java.util.Comparator;


/**
 * Servlet implementation class StudentManagement
 */



@WebServlet("/StudentManagement")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // max file size 10 MB


public class StudentManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UniversityDAO uniDAO = new UniversityDAO();
	static String SAVE_DIR = "./DocumentsRC";
	static String SAVE_DIR2 = "C:\\Users\\" + System.getProperty("user.name");
	String relativePathPDF = "/DocumentsRequestRC";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int flag = (int) request.getSession().getAttribute("flag");
		
        if(flag == 0) {
            HttpSession sessione = request.getSession(true);

            Student s = (Student) sessione.getAttribute("user");
            String email = s.getEmail();
            
            try {
                RequestRCDAO rDAO =  new RequestRCDAO();
                RequestRC rRC = rDAO.doRetrieveRequestRCByStudentID(email);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(rRC.getSubmissionDate());
                  
                    request.setAttribute("rRCDate", date);
                    request.setAttribute("rRCState", rRC.getState().toString());
                    
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                    
            }catch (Exception e) {
                e.getMessage();
            }
        } else if( flag==1 ){
			//accede al primo form
			List<University> universities = uniDAO.doRetrieveAllUniversity();
			Collections.sort(universities , new SortByName());
			request.getSession().setAttribute("universities", universities);
			RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
			dis.forward(request, response);
		}
		else if( flag==2 ){ //primo form della compilazione delle richieste
			RequestDispatcher dis = null;
			List<University> universities = uniDAO.doRetrieveAllUniversity();
			Collections.sort(universities , new SortByName());
			request.getSession().setAttribute("universities", universities);
			Part filePart1 = request.getPart("file1");  //documento di riconoscimento
			Part filePart2 = request.getPart("file2");  //documento carriera pregressa
			String UniSel = request.getParameter("universit�");  //universit� selezionata
			if( UniSel.equals("defaultUni") ) {
				request.setAttribute("errorCR1", "Universit� non selezionata.");
				dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			}else if( !filePart1.getContentType().equals("application/pdf") ){
				request.setAttribute("errorCR1","Formato file non accettato, inserire file in formato PDF.");
				dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			}else if( !filePart2.getContentType().equals("application/pdf") ) {
				request.setAttribute("errorCR1","Formato file non accettato, inserire file in formato PDF.");
				dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			}else {
				Student s = (Student) request.getSession().getAttribute("user");
				
				File file1 = new File(SAVE_DIR2 + "");
				if( !file1.mkdir() ) {
					file1.mkdir();	
				}
				
				File file2 = new File(SAVE_DIR2 + "\\" + s.getEmail());
				if( !file2.mkdir() ) {
					file2.mkdir();	
				}
				
				try {
					filePart1.write(SAVE_DIR2 + "\\" + s.getEmail() + "\\" + "ID.pdf");
				}catch(Exception msg) {
					System.out.println(msg.getLocalizedMessage());
				}
				try {
					filePart2.write(SAVE_DIR2 + "\\" + s.getEmail() + "\\" + "CP.pdf");
				}catch(Exception msg) {
					System.out.println(msg.getLocalizedMessage());
				}
			
				// Initialize FilePDF and RequestRC Object
				FilePDF filePDF1 = new FilePDF();
				FilePDF filePDF2 = new FilePDF();
				RequestRC newRequestRC = new RequestRC();
				
				// Populate Request
				newRequestRC.setUniversityID(UniSel);
				newRequestRC.setStudentID(s.getEmail());
				
				// Populate FilePFD
				filePDF1.setPDFLink(SAVE_DIR2 + "\\" + s.getEmail() + "\\" + "ID.pdf");
				filePDF2.setPDFLink(SAVE_DIR2 + "\\" + s.getEmail() + "\\" + "ID.pdf");
				
				// Set FilePDF and RequestRC in the session
				request.getSession().setAttribute("newRequestRC", newRequestRC);
				request.getSession().setAttribute("filePDF1", filePDF1);
				request.getSession().setAttribute("filePDF2", filePDF2);
				
				dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp");
				dis.forward(request, response);
			}
			
		} else if (flag == 3) { // Exams list insertion page (createRCRequest2.jsp)
			// Adding the request to the database
			RequestRC newReq = (RequestRC) request.getSession().getAttribute("newRequestRC");
			RequestRCDAO reqDAO =  new RequestRCDAO();
            int insertRequestRCResult = reqDAO.insertRequestRC(newReq);
			// Getting RequestRC ID
			String studentMail = newReq.getStudentID();
			RequestRC dbRCRequest = reqDAO.doRetrieveRequestRCByStudentID(studentMail);
			int reqRCID = dbRCRequest.getRequestRCID();
            // Adding the exams to the database
			System.out.println("rowCount" + request.getParameter("rowCount"));
			int rowCount = Integer.parseInt(request.getParameter("rowCount"));
			Exam exam = new Exam();
			ExamDAO examDAO =  new ExamDAO();
			for (int i = 1; i <= rowCount; i++) {
				// Gets the exam parameters from the form
				String n = "examName" + i;
				System.out.println("n " + n);
				String name = (String) request.getParameter(n);
				System.out.println("examName " + name);
				int CFU = Integer.parseInt(request.getParameter("CFU" + i));
				System.out.println("CFU " + CFU);
				String link = (String) request.getParameter("programLink" + i);
				System.out.println("programLink " + link);
				// Sets the exam attributes and adds it to the database
				exam.setName(name);
				exam.setCFU(CFU);
				exam.setProgramLink(link);
	            int insertExamResult = examDAO.insertExam(exam);
			}
			// Adding the contains relations to the database.
			ArrayList<Exam> examsArray = examDAO.doRetrieveAllExamsByIDRequestRC(reqRCID);
			ContainsRelation containsRel = new ContainsRelation();
			containsRel.setRequestRCID(reqRCID);
			ContainsRelationDAO containsRelDAO = new ContainsRelationDAO();
			for (Exam e : examsArray) {
				containsRel.setExamID(e.getExamID());
				containsRelDAO.insertContainsRelation(containsRel);
			}
			// Adding the PDF files to the database.
			FilePDFDAO pdfDAO = new FilePDFDAO();
			FilePDF file1 = (FilePDF) request.getSession().getAttribute("filePDF1");
			FilePDF file2 = (FilePDF) request.getSession().getAttribute("filePDF2");
			file1.setRequestRCID(reqRCID);
			file2.setRequestRCID(reqRCID);
			int insertFile1Result = pdfDAO.insertFilePDF(file1);
			int insertFile2Result = pdfDAO.insertFilePDF(file2);
			
			// Redirecting to the "view request status" page
			RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
			dis.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


