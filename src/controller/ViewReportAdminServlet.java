package controller;

import java.io.File;
import java.io.IOException;
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
import model.Report;
import model.ReportDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.StudentDAO;
import model.Suggestion;
import model.SuggestionDAO;
import model.ValidatedExam;
import model.ValidatedExamDAO;

/**
 * Servlet implementation class ReportManagement
 */
@WebServlet("/ViewReportAdminServlet")
public class ViewReportAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String projectPath = Utils.getProjectPath();
	String pdfSaveFolder = "/DocumentsRequestRC";
	String projectName = "/C8_RC_RiconoscimentoCarriera";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewReportAdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see set as attribute the validatedExam arraylist 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the RCRequest
		int requestRCID = Integer.parseInt(request.getParameter("idRequestRC"));
		RequestRCDAO rDao = new RequestRCDAO();
		ReportDAO repoDao = new ReportDAO();
		StudentDAO sDao = new StudentDAO();
		ValidatedExamDAO eDao = new ValidatedExamDAO();

		// Setting the exams
		ArrayList<Exam> examsList = new ArrayList<Exam>();
		ExamDAO examDAO = new ExamDAO();
		examsList = examDAO.doRetrieveAllExamsByRequestRCID(requestRCID);
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(requestRCID);
		Report report = new Report();
		Student s =  sDao.doRetrieveStudentByEmail(req.getStudentID());

		if (req != null) {
			// if RequestRC is != null
			if (!examsList.isEmpty()) {
				// if exams list != empty
				// Setting the RequestRC ID
				request.setAttribute("idRequestRC", requestRCID);
				if(req.getReportID() == 1) {
					ValidatedExam e = new ValidatedExam();
					int result;
					result = repoDao.createReport();
					int reportID = repoDao.doRetrieveLastReportID();
					result = rDao.insertReportID(reportID, req.getRequestRCID());
					for(int i = 0; i < examsList.size(); i++){
						e.setExamName(examsList.get(i).getName());
						e.setReportID(reportID);
						e.setValidationProcedure(null);
						result = eDao.insertValidatedExam(e);
					}

				} else {			
					report = repoDao.doRetrieveReportByReportID(req.getReportID());
				}
				req = rDao.doRetrieveRequestRCByRequestID(requestRCID);;
				request.setAttribute("examsList", examsList);
				// Setting the university
				String universityName = req.getUniversityID();;
				// Setting the suggestions
				SuggestionDAO suggDAO = new SuggestionDAO();
				ArrayList<Suggestion> suggList = new ArrayList<Suggestion>();
				ArrayList<ValidatedExam> validatedExamList = new ArrayList<ValidatedExam>();
				eDao = new ValidatedExamDAO(); 
				//Load suggestion list and validated exams list
				for (Exam e : examsList) {
					suggList.add(suggDAO.doRetrieveSuggestionByName(universityName, e.getName()));
					validatedExamList.add(eDao.doRetrieveValidatedExam(req.getReportID(), e.getName()));
				}
				
				// Control if folder DocumentsRequestRC is present in the project
				File dir = new File(projectPath + "/WebContent" + pdfSaveFolder);
				if( !dir.mkdir() ) {
					dir.mkdir();	
				}
				
				// Control if folder of Students document is present in DocumentsRequestRC
				dir = new File(projectPath + "/WebContent" + pdfSaveFolder + "/" + s.getEmail());
				if( !dir.mkdir() ) {
					dir.mkdir();	
				}
				// Control if file of checked suggestion is present in the folder of the student
				File fileSuggOW = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "suggOverwrite.txt");
				
				ArrayList<String> suggOverwrite = new ArrayList<String>();
				if ( fileSuggOW.exists() ) {
					int i = 1;
					for ( Exam e : examsList ) {
						Scanner scanner = new Scanner( fileSuggOW );
						while ( scanner.hasNextLine() ) { 
							String lineFromFile = scanner.nextLine(); 
							if ( lineFromFile.equals( "suggOverwrite" + i ) ) { 
								suggOverwrite.add( lineFromFile );
								break;
							} else if ( !scanner.hasNextLine() ) {
								suggOverwrite.add( null );
								break;
							}
						}
						i++;
						scanner.close();
					}
				} else {
					for ( int i = 0; i < examsList.size(); i++ ) {
						suggOverwrite.add( null );
					}
				}
				
				//Set request attribute
				request.setAttribute("suggOverwrite", suggOverwrite);
				request.setAttribute("suggList", suggList);
				request.setAttribute("studentName", s.getName() + " " + s.getSurname());
				request.setAttribute("note", report.getNote());
				request.setAttribute("validatedExamList", validatedExamList);
				request.getSession().setAttribute("validatedExamList", validatedExamList);
				//Redirect
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIAdminRC/createReport.jsp");
				requestDispatcher.forward(request, response);
			}else {
				//if exams list is empty
				goBackWithError("Impossibile caricare la pagina, errore nel recupero degli esami della richiesta, si prega di riprovare.", request, response);
			}
		} else {
			//if the RCRequest not found
			goBackWithError("Impossibile caricare la pagina, errore nel recupero della richiesta selezionata, si prega di riprovare.", request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void goBackWithError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(message);
		request.setAttribute("errorVRA1", message);
		RequestDispatcher dis = request.getRequestDispatcher("/AdminHome");
		dis.forward(request, response);
	}

}
