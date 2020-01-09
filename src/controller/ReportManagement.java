package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Report;
import model.RequestRC;
import model.ReportDAO;
import model.RequestRCDAO;
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
		
		
//		int reqID =(int) request.getAttribute("IdRequestRC");
		RequestRCDAO rDao = new RequestRCDAO();
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(1);
		ReportDAO repoDao = new ReportDAO();
		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		Report repo = new Report();
		if(req.getReportID()<=0) {			
			repoDao.insertReport(repo);
			req.setReportID(repoDao.doRetrieveLastReportID());
			repo.setNote("");
			repo.setValidatedExamsList(examList);
			
		} else {			
			repo = repoDao.doRetrieveReportByReportID(req.getReportID());
			examList = repoDao.doRetrieveValidatedExamsByReportID(req.getReportID());
		}
		
		
		request.setAttribute("note", repo.getNote());
		request.setAttribute("examList", repo.getValidatedExamsList());
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
