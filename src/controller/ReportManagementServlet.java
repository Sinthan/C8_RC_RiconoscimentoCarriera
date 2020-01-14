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
import javax.servlet.http.HttpSession;

import model.Exam;
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
 * Servlet implementation class SaveReportServlet
 */
@WebServlet("/ReportManagementServlet")
public class ReportManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String projectPath = Utils.getProjectPath();
	String pdfSaveFolder = "/DocumentsRequestRC";
	String projectName = "/C8_RC_RiconoscimentoCarriera";
	  
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
		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		ArrayList<String> suggOverwrite = new ArrayList<String>(); 
		String note = request.getParameter("additionalNotes");
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(requestRCID);
		Student s =  sDao.doRetrieveStudentByEmail(req.getStudentID());
		
		
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
				examName =(String) request.getParameter("validatedExamName" + i);
				String CFUParam = request.getParameter("validatedExamCFU" + i);
				if( request.getParameter("suggOverwrite" + i ) != null ) {
					suggOverwrite.add("suggOverwrite" + i);
				}
				if (!CFUParam.equals("")) {
					CFU = Integer.parseInt(CFUParam);
				} else {
					CFU = -1;
				}
				mode = (String) request.getParameter("validatedExamMode" + i);
				vExam.setExamName(examName);
				vExam.setValidatedCFU(CFU);
				vExam.setValidationProcedure(mode);
				vExam.setReportID(repoID);
				vExamDao.updateValidatedExam(vExam);		
				examList.add(vExam);
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
				// Redirect back to rc requests list and show feedback
				for( int i = 0 ; i< suggOverwrite.size() ; i++ ) {
					Suggestion sugg = new Suggestion();
					if( suggOverwrite.get(i) != null ) {
						sugg.setExamName( req.getUniversityID() );
						sugg.setExamName(examList.get(i).getExamName());
						sugg.setExternalStudentCFU( Integer.parseInt(request.getParameter("externalExamCFU" + (i+1) ) ) ) ;
						sugg.setValidatedCFU(examList.get(i).getValidatedCFU());
						sugg.setValidationMode(examList.get(i).getValidationProcedure());
						Suggestion suggestion = suggDao.doRetrieveSuggestionByName( req.getUniversityID() , sugg.getExamName() , sugg.getExternalStudentCFU());
						sugg.setValidationDate(suggestion.getValidationDate());
						
					}
				}
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
		request.setAttribute("errorCR", message);
		RequestDispatcher dis = request.getRequestDispatcher("/AdminHome");
		dis.forward(request, response);
	}

}
