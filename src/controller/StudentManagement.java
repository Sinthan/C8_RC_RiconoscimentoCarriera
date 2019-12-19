package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Exam;
import model.RequestRC;
import model.SortByName;
import model.University;
import model.UniversityDAO;
import java.util.Collections;
import java.util.Comparator;

/**
 * Servlet implementation class StudentManagement
 */
@WebServlet("/StudentManagement")
public class StudentManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UniversityDAO uniDAO = new UniversityDAO();
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int flag = (int) request.getSession().getAttribute("flag");
		if( flag==1 ){
			//accede al primo form
			List<University> universities = uniDAO.doRetrieveAllUniversity();
			Collections.sort(universities , new SortByName());
			request.getSession().setAttribute("universities", universities);
			RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
			dis.forward(request, response);
		}
		else if( flag==2 ){
			//primo form della compilazione delle richieste
			System.out.println("flag 2");
		} else if (flag == 3) { //createRCRequest2.jsp
			// Getting exams list
			ArrayList<Exam> examsList = new ArrayList<Exam>();
			int rowCount = (int) request.getSession().getAttribute("rowCount");
			Exam exam = new Exam();
			for (int i = 0; i < rowCount - 1; i++) {
				// Gets the exam parameters from the form
				String name =(String) request.getSession().getAttribute("examName" + rowCount);
				int CFU = (int) request.getSession().getAttribute("CFU" + rowCount);
				String link = (String) request.getSession().getAttribute("programLink" + rowCount);
				
				// Sets and adds the exam to the array
				exam.setName(name);
				exam.setCFU(CFU);
				exam.setProgramLink(link);
				examsList.add(exam);
			}
			// Adding exams list to the RequestRC
			RequestRC newReq = (RequestRC) request.getSession().getAttribute("newRequestRC");
			newReq.setExamsList(examsList);
			request.setAttribute("newRequestRC", newReq);
			// Redirect to view request status page
			RequestDispatcher dis = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
			dis.forward(request, response);
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

