package controller;

import java.io.IOException;
import java.util.ArrayList;

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
		int flag = (int) request.getSession().getAttribute("flag");
		if (flag == 1) {
			
		} else if (flag == 2) {
			// Getting the RCRequest and FilePDF
			RequestRCDAO reqDAO = new RequestRCDAO();
			FilePDFDAO pdfDAO = new FilePDFDAO();
			ArrayList<FilePDF> filesPDF = new ArrayList<FilePDF>();
			int requestRCID = (int) request.getSession().getAttribute("requestRCID");
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
			
			// Getting the university
			String universityName = req.getUniversityID();
			request.setAttribute("universityName", universityName);
			
			// Getting the suggestion
			SuggestionDAO suggDAO = new SuggestionDAO();
			ArrayList<Suggestion> suggList = new ArrayList<Suggestion>();
			for (Exam e : examList) {
				suggList.add(suggDAO.doRetrieveSuggestionByName(universityName, e.getName()));
			}
			request.setAttribute("suggList", suggList);
			
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
