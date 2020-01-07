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
import model.ReportDAO;
import model.RequestRC;
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
		HttpSession sess = request.getSession(true);
//		int reqID =(int) sess.getAttribute("IdRequestRC");
		RequestRCDAO rDao = new RequestRCDAO();
//		RequestRC req = rDao.doRetrieveRequestRCByRequestID(reqID);
		ReportDAO repoDao = new ReportDAO();
		Report repo = null;
		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		examList.add(new ValidatedExam(1, "prog1 ", 3 , "biuo"));
		examList.add(new ValidatedExam(2, "prog2 ", 4 , "lkoloo"));
//		if(req.getReportID()<=0) {
//			repo = new Report(req.getRequestRCID(),"",null);	
//			repoDao.insertReport(repo);
//		} else {
//			 examList = repoDao.doRetrieveValidatedExamsByReportID(reqID);
//		}
		request.setAttribute("examList", examList);
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
