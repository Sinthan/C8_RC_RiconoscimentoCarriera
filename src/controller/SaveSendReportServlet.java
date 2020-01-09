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

import model.ReportDAO;
import model.ValidatedExam;
import model.ValidatedExamDAO;

/**
 * Servlet implementation class SaveReportServlet
 */
@WebServlet("/SaveSendReportServlet")
public class SaveSendReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveSendReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession();
		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		String note = request.getParameter("note");
		boolean flag =(boolean) sess.getAttribute("flag");
		int rows =(int) sess.getAttribute("rows");
		int repoID =(int) sess.getAttribute("repoID");
		int CFU;
		String examName;
		String mode;		
		ValidatedExam  exam = null;
		ValidatedExamDAO vExamDao = new ValidatedExamDAO();
		ReportDAO repoDao = new ReportDAO();
		for(int i = 1; i < rows; i++) {
			exam = new ValidatedExam();
			examName = request.getParameter("examName" + i);
			CFU = Integer.parseInt(request.getParameter("CFU"+i));
			mode = (String) request.getParameter("mode" + i);
			exam.setExamName(examName);
			exam.setValidatedCFU(CFU);
			exam.setValidationProcedure(mode);
			exam.setReportID(repoID);
			System.out.println("servlet prima"+exam);
			vExamDao.updateValidatedExam(exam);		
			examList.add(exam);
			
		}
		
			repoDao.updateNote(repoID,note);
		
	
//		if (flag==true) {//if sendbutton setted flag
//			
//			
//		}
		request.setAttribute("note",note);
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
