package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Exam;
import model.ExamDAO;
import model.FilePDF;
import model.FilePDFDAO;
import model.RCState;
import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.StudentDAO;
import model.Suggestion;
import model.SuggestionDAO;

/**
 * Servlet implementation class AdminManagement
 */
@WebServlet("/AdminManagement")
public class AdminManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String projectPath = Utils.getProjectPath();
	String pdfSaveFolder = "/DocumentsRequestRC";
	String projectName = "/C8_RC_RiconoscimentoCarriera";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int flag = 0;
	
		// Control of the next statement to be executed
		if ( request.getParameter("flag")!=null ) {
			flag = Integer.parseInt(request.getParameter("flag"));
		}else if ( request.getAttribute("flag") != null ) {
			flag = (int) request.getAttribute("flag");		 
		}
				
		if (flag == 1) {
			RCState state = RCState.isBeingDiscussed;
			RequestRCDAO reqDao = new RequestRCDAO();
			ArrayList<RequestRC> reqList  =  reqDao.doRetrieveAllRequestRCBystate(state);
			request.setAttribute("reqList", reqList );
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIAdminRC/homeRCPCD.jsp");		
			requestDispatcher.forward(request, response);
			return;
			
			
		} else if (flag == 2) {
			// Getting the RCRequest and FilePDF
			RequestRCDAO reqDAO = new RequestRCDAO();
			FilePDFDAO pdfDAO = new FilePDFDAO();
			ArrayList<FilePDF> filesPDF = new ArrayList<FilePDF>();
			int requestRCID = Integer.parseInt(request.getParameter("idRequestRC"));
			RequestRC req = reqDAO.doRetrieveRequestRCByRequestID(requestRCID);
			filesPDF = pdfDAO.doRetrieveAllFilePDFByIDRequestRC(req.getRequestRCID());
			
			for ( int i=0 ; i<filesPDF.size() ; i++ ) {
				int indexWC = filesPDF.get(i).getPDFLink().indexOf("/DocumentsRequestRC/");
				if (indexWC > -1) {
					String relativePath = filesPDF.get(i).getPDFLink().substring(indexWC+1, filesPDF.get(i).getPDFLink().length() );
					if (relativePath.indexOf("CP.pdf") > 1) {
						request.getSession().setAttribute("pathCP", relativePath);
					}
				} else {
					System.out.println("Invalid path, can't show PDF");
				}
			}
			
			// Setting the student name attribute
			String studentMail = req.getStudentID();
			StudentDAO sDAO = new StudentDAO();
			Student s = sDAO.doRetrieveStudentByEmail(studentMail);
			String studentName = s.getName() + " " + s.getSurname();
			request.setAttribute("studentName", studentName);
			
			// Setting the submission date
			String date = Utils.getFormattedDate(req.getSubmissionDate());
			request.setAttribute("submissionDate", date);
			
			// Getting the exams
			ExamDAO examDAO = new ExamDAO();
			ArrayList<Exam> examList= examDAO.doRetrieveAllExamsByRequestRCID(requestRCID);
			request.setAttribute("examList", examList);
			request.getSession().setAttribute("examList", examList);
			
			// Getting the university
			String universityName = req.getUniversityID();
			request.setAttribute("universityName", universityName);
			
			// Getting the suggestion
			SuggestionDAO suggDAO = new SuggestionDAO();
			ArrayList<Suggestion> suggList = new ArrayList<Suggestion>();
			for (Exam e : examList) {
				suggList.add(suggDAO.doRetrieveSuggestionByName(universityName, e.getName()));
			}
			
			// Getting sended Mail
			File dir = new File(projectPath + "/WebContent" + pdfSaveFolder);
			if( !dir.mkdir() ) {
				dir.mkdir();	
			}
			
			// Control if folder of Students document is present in DocumentsRequestRC
			dir = new File(projectPath + "/WebContent" + pdfSaveFolder + "/" + s.getEmail() );
			if( !dir.mkdir() ) {
				dir.mkdir();	
			}
			// Control if file of sended mail is present in the folder of the student
			File fileM = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "mailRequest.txt");
			
			// Create a new ArrayList of exam sended to Professor
			ArrayList<String> mailsSended = new ArrayList<String>();
			if( fileM.exists() ) {
				for (Exam e : examList) {
					Scanner scanner = new Scanner(fileM);
					while ( scanner.hasNextLine() ) { 
						String lineFromFile = scanner.nextLine(); 
						if ( lineFromFile.equals( e.getName() ) ) { 
							mailsSended.add(lineFromFile);
							break;
						}else if( !scanner.hasNextLine() ) {
							mailsSended.add(null);
							break;
						}
					}
					scanner.close();
				}
			}else {
				for (int i = 0; i < examList.size(); i++) {
					mailsSended.add(null);
				}
			}
			
			request.setAttribute("idRequestRC", requestRCID);
			request.setAttribute("suggList", suggList);
			request.setAttribute("mailsSended", mailsSended);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIAdminRC/viewRCRequestAdmin.jsp");
			requestDispatcher.forward(request, response);
			return;
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
