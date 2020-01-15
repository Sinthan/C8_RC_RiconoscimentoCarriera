package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Exam;
import model.RCState;
import model.Report;
import model.ReportDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.SenderMail;
import model.Student;
import model.StudentDAO;
import model.Suggestion;
import model.SuggestionDAO;
import model.ValidatedExam;
import model.ValidatedExamDAO;

/**
 * Servlet implementation class SaveReportServlet
 */
@WebServlet("/ReportManagementServlet")
public class ReportManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String projectPath = Utils.getProjectPath();
	String pdfSaveFolder = "/DocumentsRequestRC";
	String projectName = "/C8_RC_RiconoscimentoCarriera";
	String subject = "ESITO RICONOSCIMENTO CARRIERA PREGRESSA";
	String adminMail = "carrierapregressaunisa@gmail.com";
	String fileName = "Report.pdf";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportManagementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Getting the Session
		HttpSession sess = request.getSession();
		// Getting the RCRequest
		int requestRCID = Integer.parseInt(request.getParameter("idRequestRC"));
		RequestRCDAO rDao = new RequestRCDAO();
		ReportDAO repoDao = new ReportDAO();
		StudentDAO sDao = new StudentDAO();
		SuggestionDAO suggDao = new SuggestionDAO();
		ValidatedExamDAO vExamDao = new ValidatedExamDAO();
		SenderMail senderMail = new SenderMail();


		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		ArrayList<String> suggOverwrite = new ArrayList<String>(); 
		String note = request.getParameter("additionalNotes");
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(requestRCID);
		Student s =  sDao.doRetrieveStudentByEmail(req.getStudentID());
		String studentMail = req.getStudentID();
		PDFCreator pdfC = new PDFCreator(System.getProperty("user.dir") + "/" + "Report.pdf");
		String stName = sDao.doRetrieveStudentByEmail(studentMail).getName() + " " + sDao.doRetrieveStudentByEmail(studentMail).getSurname();
		String messageBody = "Gentile " + stName + ";\n" + "In allegato troverà il PDF contente l'esito della sua richiesta di riconoscimento carriera.\nBuona Giornata.";
		ArrayList<Integer> ValidatedCFUArray = new ArrayList();
		ArrayList<Integer> CFUExt = new ArrayList();
		Suggestion sugg = null;


		//Get row number
		int rows = Integer.parseInt(request.getParameter("rowCount"));
		//Get Report id
		int repoID = req.getReportID();

		int CFU;
		String examName;
		String mode;
		
		//Update Report if request exists
		if (req != null) {
			//if RCRequest is != null
			for(int i = 1; i < rows; i++) {
				ValidatedExam vExam = new ValidatedExam();
				examName = (String) request.getParameter("validatedExamName" + i);
				String CFUParam = request.getParameter("validatedExamCFU" + i);
				if( request.getParameter("suggOverwrite" + i ) != null ) {
					suggOverwrite.add("suggOverwrite" + i);
				}
				if (!(CFUParam == null) && !CFUParam.equals("")) {
					CFU = Integer.parseInt(CFUParam);
				} else {
					CFU = -1;
				}
				mode = (String) request.getParameter("validatedExamMode" + i);
				if (mode.length() > 5000) {
					goBackWithError("Impossibile salvare la bozza, la modalita di convalida dell'esame " + examName + " supera i 5000 caratteri", request, response);
					return;
				}
				vExam.setExamName(examName);
				vExam.setValidatedCFU(CFU);
				vExam.setValidationProcedure(mode);
				vExam.setReportID(repoID);
				
				vExamDao.updateValidatedExam(vExam);		
				examList.add(vExam);
			}
			if (note.length() > 5000) {
				goBackWithError("Impossibile salvare la bozza, la nota supera i 5000 caratteri", request, response);
				return;
			}
			repoDao.updateNote(repoID,note);

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
			fileSuggOW.delete();			
			fileSuggOW = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "suggOverwrite.txt");
			// Insert checked suggestion for exam 
			PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter( fileSuggOW, true )));
			for ( String sOW : suggOverwrite) {	
				writer.println( sOW );
			}
			writer.close();

			//Load checked suggestion
			suggOverwrite = new ArrayList<String>();
			if ( fileSuggOW.exists() ) {
				int i = 1;
				for ( ValidatedExam e : examList ) {
					Scanner scanner = new Scanner(fileSuggOW);
					while (scanner.hasNextLine()) { 
						String lineFromFile = scanner.nextLine(); 
						if ( lineFromFile.equals( "suggOverwrite" + i ) ) { 
							suggOverwrite.add( lineFromFile );
							break;
						} else if ( !scanner.hasNextLine() ) {
							suggOverwrite.add( null );
							break;
						}
					}
					scanner.close();
				}
			} else {
				for ( int i = 0; i < examList.size(); i++ ) {
					suggOverwrite.add( null );
				}
			}

			//Set checked suggestion in servlet
			request.setAttribute("suggOverwrite", suggOverwrite);
			//Set RCRequest id in request
			request.setAttribute("idRequestRC",requestRCID);
			//Set success message in request
			request.setAttribute("successCR", "Bozza salvata correttamente");

			if (request.getParameter("closeRCRequestBtn") != null) {
				// If generate report was pressed
				
				// Create a suggestion if it doesn't exist
				for(int i = 1; i < rows; i++) {
					ValidatedCFUArray.add(Integer.parseInt(request.getParameter("validatedExamCFU"+i)));
					CFUExt.add(Integer.parseInt(request.getParameter("externalExamCFU"+i)));
					// if suggestion doesn't exists add create it
					String universityName = req.getUniversityID();
					String examName2 = examList.get(i-1).getExamName();
					int externalCFU = CFUExt.get(i-1);
					if(suggDao.doRetrieveSuggestionByName(universityName, examName2, externalCFU) == null) {
						Date date = new Date(System.currentTimeMillis());
						sugg = new Suggestion(req.getUniversityID(),
											examList.get(i-1).getExamName(),
											CFUExt.get(i-1),
											ValidatedCFUArray.get(i-1),
											examList.get(i-1).getValidationProcedure(),
											date);
						suggDao.insertSuggestion(sugg);

					}
				}
				
				
				
				for(int i = 0 ; i < suggOverwrite.size() ; i++ ) {
					sugg = new Suggestion();
					if( suggOverwrite.get(i) != null ) {
						sugg.setUniversityName(req.getUniversityID());
						sugg.setExamName(examList.get(i).getExamName());
						sugg.setExternalStudentCFU( Integer.parseInt(request.getParameter("externalExamCFU" + (i+1) ) ) ) ;
						sugg.setValidatedCFU(examList.get(i).getValidatedCFU());
						sugg.setValidationMode(examList.get(i).getValidationProcedure());
						Suggestion suggestion = suggDao.doRetrieveSuggestionByName(req.getUniversityID(), sugg.getExamName(), sugg.getExternalStudentCFU());
						sugg.setValidationDate(suggestion.getValidationDate());
					}
				}

				pdfC.createReportPdf(examList, ValidatedCFUArray, stName, CFUExt, note);
				senderMail.sendMailWithAttached(adminMail,studentMail,subject,messageBody, fileName);
				
				// Update request status
				rDao.updateState(RCState.approved, requestRCID);
				
				// Redirect back to rc requests list and show feedback
				request.setAttribute("succCR", "Richiesta chiusa correttamente");
				RequestDispatcher dis = request.getRequestDispatcher("/AdminHome");
				dis.forward(request, response);
			} else {
				// If save draft was pressed
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewReportAdminServlet");
				requestDispatcher.forward(request, response);
			}
		} else {
			//if RCRequest = null
			goBackWithError("Impossibile caricare la pagina, errore nel recupero della richiesta selezionata, si prega di riprovare.", request, response);
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

	private void goBackWithError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(message);
		request.setAttribute("errorVRA1", message);
		response.sendRedirect("/EnglishValidation/AdminHome?errorVRA1=" + message);;
		return;
	}
}
