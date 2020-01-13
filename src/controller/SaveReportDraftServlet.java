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
import model.Student;
import model.StudentDAO;
import model.ValidatedExam;
import model.ValidatedExamDAO;

/**
 * Servlet implementation class SaveReportServlet
 */
@WebServlet("/SaveReportDraftServlet")
public class SaveReportDraftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveReportDraftServlet() {
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
		ValidatedExamDAO vExamDao = new ValidatedExamDAO();
		ArrayList<ValidatedExam> examList = new ArrayList<ValidatedExam>();
		String note = request.getParameter("additionalNotes");
		RequestRC req = rDao.doRetrieveRequestRCByRequestID(requestRCID);
		int rows = Integer.parseInt(request.getParameter("rowCount"));
		int repoID = req.getReportID();
		int CFU;
		String examName;
		String mode;
		
		if (req != null) {
			for(int i = 1; i < rows; i++) {
				ValidatedExam vExam = new ValidatedExam();
				examName =(String) request.getParameter("validatedExamName" + i);
				String CFUParam = request.getParameter("validatedExamCFU" + i);
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
			
			request.setAttribute("idRequestRC",requestRCID);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewReportAdminServlet");
			requestDispatcher.forward(request, response);
		} else {
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
