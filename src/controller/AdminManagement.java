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

import com.itextpdf.text.log.SysoCounter;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			// Getting the RCRequest and FilePDF
			RequestRCDAO reqDAO = new RequestRCDAO();
			FilePDFDAO pdfDAO = new FilePDFDAO();
			ArrayList<FilePDF> filesPDF = new ArrayList<FilePDF>();
			int requestRCID = Integer.parseInt(request.getParameter("idRequestRC"));
			RequestRC req = reqDAO.doRetrieveRequestRCByRequestID(requestRCID);
			if (req != null) {
				filesPDF = pdfDAO.doRetrieveAllFilePDFByIDRequestRC(req.getRequestRCID());

				if (filesPDF != null) {
					for (int i = 0; i < filesPDF.size(); i++) {
						int indexWC = filesPDF.get(i).getPDFLink().indexOf("/DocumentsRequestRC/");
						if (indexWC > -1) {
							String relativePath = filesPDF.get(i).getPDFLink().
									substring(indexWC + 1, filesPDF.get(i).getPDFLink().length());
							if (relativePath.indexOf("CP.pdf") > 1) {
								request.getSession().setAttribute("pathCP", relativePath);
							}
						} else {
							goBackWithError("Path del PDF non valido, impossibile mostrare il PDF.", request, response);
							return;
						}
					}

					// Setting the RequestRC ID
					request.setAttribute("idRequestRC", requestRCID);
					// Setting the student name attribute
					String studentMail = req.getStudentID();
					StudentDAO sDAO = new StudentDAO();
					Student s = sDAO.doRetrieveStudentByEmail(studentMail);
					if (s != null) {
						String studentName = s.getName() + " " + s.getSurname();
						request.setAttribute("studentName", studentName);	
						// Setting the submission date
						String date = Utils.getFormattedDate(req.getSubmissionDate());
						request.setAttribute("submissionDate", date);
						// Setting the exams
						ExamDAO examDAO = new ExamDAO();
						ArrayList<Exam> examList = examDAO.doRetrieveAllExamsByRequestRCID(requestRCID);
						if (examList != null) {
							request.setAttribute("examList", examList);
							request.getSession().setAttribute("examList", examList);
							// Setting the university
							String universityName = req.getUniversityID();
							request.setAttribute("universityName", universityName);
							// Setting the suggestions
							SuggestionDAO suggDAO = new SuggestionDAO();
							ArrayList<Suggestion> suggList = new ArrayList<Suggestion>();
							for (Exam e : examList) {
								suggList.add(suggDAO.doRetrieveSuggestionByName(universityName, e.getName()));
							}
							request.setAttribute("suggList", suggList);
							// Creating the RCRequests documents folder if it doesn't exist
							File dir = new File(projectPath + "/WebContent" + pdfSaveFolder);
							if (!dir.mkdir()) {
								dir.mkdir();	
							}
							// Creating the student folder if it doesn't exist
							dir = new File(projectPath + "/WebContent" + pdfSaveFolder + "/" + s.getEmail());
							if (!dir.mkdir()) {
								dir.mkdir();	
							}
							// Instantiating the file used to track the mails already sent
							File fileM = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + s.getEmail() + "/" + "mailRequest.txt");
							// Constucts a new ArrayList with the exam whose professor were already contacted
							ArrayList<String> mailsSent = new ArrayList<String>();
							if (fileM.exists()) {
								for (Exam e : examList) {
									Scanner scanner = new Scanner(fileM);
									while (scanner.hasNextLine()) { 
										String lineFromFile = scanner.nextLine(); 
										if (lineFromFile.equals(e.getName())) { 
											mailsSent.add(lineFromFile);
											break;
										} else if (!scanner.hasNextLine()) {
											mailsSent.add(null);
											break;
										}
									}
									scanner.close();
								}
							} else {
								for (int i = 0; i < examList.size(); i++) {
									mailsSent.add(null);
								}
							}
							request.setAttribute("mailsSent", mailsSent);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIAdminRC/viewRCRequestAdmin.jsp");
							requestDispatcher.forward(request, response);
							return;
						} else {
							goBackWithError("Impossibile caricare la pagina, esami non trovati, si prega di riprovare.", request, response);
						}
					} else {
						goBackWithError("Impossibile caricare la pagina, dati dello studente non trovati, si prega di riprovare.", request, response);
					}
				} else {
					goBackWithError("Impossibile caricare la pagina, PDF del certificato non trovato, si prega di riprovare.", request, response);
				}
			} else {
				goBackWithError("Impossibile caricare la pagina, errore nel recupero della richiesta selezionata, si prega di riprovare.", request, response);
			}
		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void goBackWithError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(message);
		request.setAttribute("errorVRA1", message);
		RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/AdminHome");
		dis.forward(request, response);
	}

}
