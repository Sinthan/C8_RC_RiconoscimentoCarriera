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
import model.Report;
import model.ReportDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.Suggestion;
import model.SuggestionDAO;
import model.ValidatedExam;

/**
 * Servlet implementation class ReportManagement
 */
@WebServlet("/ReportManagement")
public class ReportManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see set as attribute the validatedExam arraylist 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int requestRCID = Integer.parseInt(request.getParameter("idRequestRC"));
		RequestRCDAO rDao = new RequestRCDAO();
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(requestRCID);
		ReportDAO repoDao = new ReportDAO();
		ArrayList<ValidatedExam> validatedExamList = new ArrayList<ValidatedExam>();
		Report repo = new Report();
		if(req.getReportID()<=0) {			
			repoDao.insertReport(repo);
			req.setReportID(repoDao.doRetrieveLastReportID());
			repo.setNote("");
			repo.setValidatedExamsList(validatedExamList);
			
		} else {			
			repo = repoDao.doRetrieveReportByReportID(req.getReportID());
			validatedExamList = repoDao.doRetrieveValidatedExamsByReportID(req.getReportID());
		}
		
		
		// Setting the exams
		ExamDAO examDAO = new ExamDAO();
		ArrayList<Exam> examList = examDAO.doRetrieveAllExamsByRequestRCID(requestRCID);
		if (!examList.isEmpty()) {
			request.setAttribute("examList", examList);
			// Setting the university
			String universityName = req.getUniversityID();;
			// Setting the suggestions
			SuggestionDAO suggDAO = new SuggestionDAO();
			ArrayList<Suggestion> suggList = new ArrayList<Suggestion>();
			for (Exam e : examList) {
				suggList.add(suggDAO.doRetrieveSuggestionByName(universityName, e.getName()));
			}
			request.setAttribute("suggList", suggList);
		}
		
		request.setAttribute("note", repo.getNote());
		request.setAttribute("validatedExamList", repo.getValidatedExamsList());
		request.getSession().setAttribute("validatedExamList", repo.getValidatedExamsList());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIAdminRC/createReport.jsp");
		requestDispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
