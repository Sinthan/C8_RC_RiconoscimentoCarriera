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

import org.json.simple.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				File file = new File(SAVE_DIR);
				if( !file.mkdir() ) {
					file.mkdir();
				}
				
				File file2 = new File(SAVE_DIR + "/" + s.getEmail() );
				if( !file2.mkdir() ) {
					file.mkdir();	
				}
				
				filePart1.write(SAVE_DIR + "/" + s.getEmail() + "/" +  "ID.pdf");
				filePart2.write(SAVE_DIR + "/" + s.getEmail() + "/" +  "CP.pdf");
				
				request.setAttribute("cont","Apposto");
				
				
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
			int rowCount = (int) request.getSession().getAttribute("rowCount");
			Exam exam = new Exam();
			ExamDAO examDAO =  new ExamDAO();
			for (int i = 0; i < rowCount; i++) {
				// Gets the exam parameters from the form
				String name =(String) request.getSession().getAttribute("examName" + rowCount);
				int CFU = (int) request.getSession().getAttribute("CFU" + rowCount);
				String link = (String) request.getSession().getAttribute("programLink" + rowCount);
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
			for (Exam e : examsArray) {
				containsRel.setExamID();
				insertContainsRelation(containsRel);
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


