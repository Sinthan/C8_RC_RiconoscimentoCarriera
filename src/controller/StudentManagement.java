package controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ContainsRelation;
import model.ContainsRelationDAO;
import model.Exam;
import model.ExamDAO;
import model.FilePDF;
import model.FilePDFDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.SortByName;
import model.Student;
import model.University;
import model.UniversityDAO;


/**
 * Servlet implementation class StudentManagement
 */



@WebServlet("/StudentManagement")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // max file size 10 MB


public class StudentManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UniversityDAO uniDAO = new UniversityDAO();
	static String SAVE_DIR = "./DocumentsRC";
	String pdfSaveFolder = "/DocumentsRequestRC";
	String projectName = "/C8_RC_RiconoscimentoCarriera";

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int flag = (int) request.getSession().getAttribute("flag");

		if(flag == 0) {
			HttpSession sessione = request.getSession();

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
				e.printStackTrace();
			}
		} else if(flag==1){ 
			//accede al primo form
			List<University> universities = uniDAO.doRetrieveAllUniversity();
			Collections.sort(universities , new SortByName());
			request.getSession().setAttribute("universities", universities);
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
			dis.forward(request, response);
			
		} else if(flag==2){ //primo form della compilazione delle richieste 
			RequestDispatcher dis = null;
			List<University> universities = uniDAO.doRetrieveAllUniversity();
			Collections.sort(universities , new SortByName());
			request.getSession().setAttribute("universities", universities);
			Part filePart1 = request.getPart("file1");  //documento di riconoscimento
			Part filePart2 = request.getPart("file2");  //documento carriera pregressa
			String UniSel = request.getParameter("universita");  //universita selezionata
			if( UniSel.equals("defaultUni") ) {
				request.setAttribute("errorCR1", "Universitit&#224; non selezionata.");
				dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			} else if(!filePart1.getContentType().equals("application/pdf") ){
				request.setAttribute("errorCR1","Formato file non accettato, inserire file in formato PDF.");
				dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			} else if(!filePart2.getContentType().equals("application/pdf") ) {
				request.setAttribute("errorCR1","Formato file non accettato, inserire file in formato PDF.");
				dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
			} else {
				Student s = (Student) request.getSession().getAttribute("user");

				//Get  the project path
				String SAVE_DIR2= Utils.getProjectPath();

				//Control if path of the project is empty or equals to null
				if(  SAVE_DIR2.equals("") || SAVE_DIR2.equals(null)) {
					request.setAttribute("errorCR1","Impossibile salvare il file, path non trovato.");
					dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
					dis.forward(request, response);
				}

				//Control if folder DocumentsRequestRC is present in the project
				File file1 = new File(SAVE_DIR2 + "/WebContent" + pdfSaveFolder);
				if( !file1.mkdir() ) {
					file1.mkdir();	
				}


				//Control if folder of Students document is present in DocumentsRequestRC
				File file2 = new File(SAVE_DIR2 + "/WebContent" + pdfSaveFolder + "/" + s.getEmail());
				if( !file2.mkdir() ) {
					file2.mkdir();	
				}

				//Save ID
				try {
					filePart1.write(SAVE_DIR2 + "/WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "ID.pdf");
				}catch(Exception msg) {
					System.out.println(msg.getLocalizedMessage());
				}

				//Save CP Document
				try {
					filePart2.write(SAVE_DIR2 + "/WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "CP.pdf");
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

				// Populate FilePDF
				filePDF1.setPDFLink("/WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "ID.pdf");
				filePDF2.setPDFLink("/WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "CP.pdf");

				// Set FilePDF and RequestRC in the session
				request.getSession().setAttribute("newRequestRC", newRequestRC);
				request.getSession().setAttribute("filePDF1", filePDF1);
				request.getSession().setAttribute("filePDF2", filePDF2);

				//Redirect to second page of RequestRC
				dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp");
				dis.forward(request, response);
			}
		} else if (flag == 3) { // Exams list insertion page (createRCRequest2.jsp)
			RequestDispatcher dis = null;
			// Checks if the data from the previous page is still available in the session
			if (request.getSession().getAttribute("newRequestRC") == null
					|| request.getSession().getAttribute("filePDF1") == null
					|| request.getSession().getAttribute("filePDF2") == null) {
				System.out.println("Dati della createRCRequest1 non trovati.");
				request.setAttribute("errorCR1","Alcuni dati sono andati persi, si prega di ricompilare la richiesta.");
				dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
				dis.forward(request, response);
				return;
			}
			String name;
			int CFU;
			String link;
			int examsInserted = Integer.parseInt(request.getParameter("rowCount"));
			for (int currentExamRow = 1; currentExamRow <= examsInserted; currentExamRow++) {
				// Gets the exam parameters from the form
				name = (String) request.getParameter("examName" + currentExamRow);
				CFU = Integer.parseInt(request.getParameter("CFU" + currentExamRow));
				link = (String) request.getParameter("programLink" + currentExamRow);
				// Checks if the parameters have a valid format
				if (!name.matches("^(\\w+\\s?\\-?)*(\\-?\\s*\\w*)*$")) {
					request.setAttribute("errorCR2","Il nome dell&#8217;esame alla riga " + currentExamRow
							+ " non rispetta un formato valido, sono ammessi solo caratteri alfanumerici"
							+ " pi&#249; i caratteri \"-\" e \"_\". La lunghezza deve essere compresa tra i 2 e i 50 caratteri");
					dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp");
					dis.forward(request, response);
					return;
				} else if (CFU < 0 || CFU > 31) {
					request.setAttribute("errorCR2","I CFU dell&#8217;esame alla riga " + currentExamRow
							+ " non rispettano un formato valido, sono ammessi solo caratteri numerici"
							+ " La lunghezza deve essere di 1 o 2 cifre");
					dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp");
					dis.forward(request, response);
					return;
				} else if (!link.matches("^(?:(http(s)?|ftp):\\/\\/)?([-a-zA-Z0-9@:%._\\\\+~#=]+(\\.[a-zA-Z0-9]{1,6})+)(\\/([-a-zA-Z0-9()@:%_\\\\+.~#?&=]*))*")) {
					request.setAttribute("errorCR2","Il link dell&#8217;esame alla riga " + currentExamRow
							+ " non rispetta un formato valido oppure la lunghezza non &#232; compresa tra i 4 e i 256 caratteri");
					dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest2.jsp");
					dis.forward(request, response);
					return;
				}
			}
			// Adding the request to the database
			RequestRC newReq = (RequestRC) request.getSession().getAttribute("newRequestRC");
			RequestRCDAO reqDAO =  new RequestRCDAO();
			int insertRequestRCResult = reqDAO.insertRequestRC(newReq);
			// Getting RequestRC ID
			String studentMail = newReq.getStudentID();
			RequestRC dbRCRequest = reqDAO.doRetrieveRequestRCByStudentID(studentMail);
			int reqRCID = dbRCRequest.getRequestRCID();
			// Initializing ContainsRelation between a RequestRC and its Exams 
			ContainsRelationDAO containsRelDAO = new ContainsRelationDAO();
			ContainsRelation containsRel = new ContainsRelation();
			containsRel.setRequestRCID(reqRCID);
			// Adding the exams to the database
			Exam exam = new Exam();
			ExamDAO examDAO =  new ExamDAO();
			int lastExamID = examDAO.doRetrieveMaxExamID();
			int nextExamID = lastExamID + 1;
			int insertExamResult;
			int containsRelResult;
			ArrayList<Integer> insertExamResults = new ArrayList<Integer>();
			ArrayList<Integer> insertContainsRelationResults = new ArrayList<Integer>();
			for (int currentExamRow = 1; currentExamRow <= examsInserted; currentExamRow++) {
				// Gets the exam parameters from the form
				name = (String) request.getParameter("examName" + currentExamRow);
				CFU = Integer.parseInt(request.getParameter("CFU" + currentExamRow));
				link = (String) request.getParameter("programLink" + currentExamRow);
				if (!link.startsWith("http://") && !link.startsWith("https://") && !link.startsWith("ftp://")) {
				    link = "http://" + link;
				}
				// Sets the exam attributes
				exam.setName(name);
				exam.setCFU(CFU);
				exam.setProgramLink(link);
				// Adding the contains relations and the exam to the database.
				insertExamResult = examDAO.insertExam(exam);
				insertExamResults.add(insertExamResult);
				if (insertExamResult >= 0) { // Exam record already present in the database
					containsRel.setExamID(insertExamResult); // Sets the examID of the exam already stored
				} else { // Exam isn't present in the database
					containsRel.setExamID(nextExamID); // Sets the examID of the exam just added
					nextExamID++;
				}
				containsRelResult = containsRelDAO.insertContainsRelation(containsRel);
				insertContainsRelationResults.add(containsRelResult);
			}
			// Adding the PDF files to the database.
			FilePDFDAO pdfDAO = new FilePDFDAO();
			FilePDF file1 = (FilePDF) request.getSession().getAttribute("filePDF1");
			FilePDF file2 = (FilePDF) request.getSession().getAttribute("filePDF2");
			file1.setRequestRCID(reqRCID);
			file2.setRequestRCID(reqRCID);
			int insertFile1Result = pdfDAO.insertFilePDF(file1);
			int insertFile2Result = pdfDAO.insertFilePDF(file2);
			// Checking if request was successfully added to the database
			if (insertRequestRCResult > 0 && insertFile1Result > 0 && insertFile2Result > 0) {
				for (int result : insertExamResults) {
					if (result == -2 || result == -4) {
						System.out.println("Si sono verificati errori sull'inserimento degli esami all'interno del database.");
						goBackWithError(reqRCID, "Si &#232; verificato un errore durante il salvataggio della richiesta. Si prega di riprovare.", request, response);
						return;
					}
				}
				for (int result : insertContainsRelationResults) {
					if (result == 0 || result == -2) {
						System.out.println("Si sono verificati errori sull'inserimento delle relazioni Contains all'interno del database.");
						goBackWithError(reqRCID, "Si &#232; verificato un errore durante il salvataggio della richiesta. Si prega di riprovare.", request, response);
						return;
					}
				}
			} else {
				System.out.println("Si sono verificati errori sull'inserimento della richiesta o dei file pdf all'interno del database.");
				goBackWithError(reqRCID, "Si &#232; verificato un errore durante il salvataggio della richiesta. Si prega di riprovare.", request, response);
				return;
			}
			// Redirecting to the "view request status" page and setting the attributes it will need
			request.setAttribute("rRCDate", dbRCRequest.getSubmissionDate());
			request.setAttribute("rRCState", dbRCRequest.getState());
			request.setAttribute("didInsertRequest", "true");
			dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
			dis.forward(request, response);
		} else if (flag==4) {
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
			dis.forward(request, response);
		}

	}
	
	private void goBackWithError(int requestRCID, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestRCDAO reqDAO =  new RequestRCDAO();
		reqDAO.deleteRequestRCByRequestID(requestRCID);
		request.setAttribute("errorCR1", message);
		RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


