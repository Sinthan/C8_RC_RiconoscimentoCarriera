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
import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.StudentDAO;

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
			// Getting the RCRequest
			RequestRCDAO reqDAO = new RequestRCDAO();
			int requestRCID = (int) request.getSession().getAttribute("requestRCID");
			RequestRC req = reqDAO.doRetrieveRequestRCByRequestID(requestRCID);
			
			// Setting the student name attribute
			String studentMail = req.getStudentID();
			StudentDAO sDAO = new StudentDAO();
			Student s = sDAO.doRetrieveStudentByEmail(studentMail);
			String studentName = s.getName() + s.getSurname();
			request.setAttribute("studentName", studentName);
			
			// Setting the submission date
			request.setAttribute("submissionDate", req.getSubmissionDate());
			
			// Getting the exams
			ExamDAO examDAO = new ExamDAO();
			ArrayList<Exam> examList= examDAO.doRetrieveAllExamsByRequestRCID(requestRCID);
			int exams = examList.size();
			String[] examNamesArray = new String[exams];
			int[] CFUArray = new int[exams];
			String[] linkArray = new String[exams];
			int i = 0;
			for (Exam e : examList) {
				examNamesArray[i] = e.getName();
				CFUArray[i] = e.getCFU();
				linkArray[i] = e.getProgramLink();
				i++;
			}
			request.setAttribute("examNamesArray", examNamesArray);
			request.setAttribute("CFUArray", CFUArray);
			request.setAttribute("linkArray", linkArray);
			
			// Getting the university
			String universityName = req.getUniversityID();
			request.setAttribute("universityName", universityName);
			
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
