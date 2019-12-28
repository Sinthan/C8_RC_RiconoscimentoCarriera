package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import model.State;
import model.Student;
import model.StudentDAO;
import model.UniversityDAO;

/**
 * Servlet implementation class UCManagement
 */
@WebServlet("/UCManagement")
public class UCManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UCManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		State state = new State(0,"needsUCValidation");
		RequestRCDAO reqDao = new RequestRCDAO();
		ArrayList<RequestRC> reqList  =  reqDao.doRetrieveAllRequestRCBystate(state);
		RequestRC temp = null;
		StudentDAO rDao = new StudentDAO();
		String name;
		String surname;
		
	
		
		for (int i = 0; i < reqList.size(); i++) {
		      temp = reqList.get(i);
		      temp.getSubmissionDate();
		      System.out.println(temp.toString());
		      Student s = rDao.doRetrieveStudentByEmail(temp.getStudentID());
		      name = s.getName();
		      surname = s.getSurname();
		      request.setAttribute("rRCDate",temp.getSubmissionDate());
		      request.setAttribute("stName",name+" "+surname);	      
		}
		
		
		ExamDAO examDAO = new ExamDAO();
		List<Exam> exams =  (List<Exam>) examDAO.doRetrieveAllExamsByRequestRCID(5);
		request.getSession().setAttribute("exams", exams);
		System.out.println(exams.toString());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIUC/homeRCUC.jsp");		
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
