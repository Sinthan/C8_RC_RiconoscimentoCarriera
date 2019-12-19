package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RequestRCDAO;
import model.Student;
import model.RequestRC;

/**
 * Servlet implementation class StudentManagement
 */
@WebServlet("/StudentManagement")
public class StudentManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

    	public void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {

    		HttpSession sessione = request.getSession(true);

    		


    		Student s = (Student) sessione.getAttribute("user"); 		
    		String email = s.getEmail();
    		System.out.println(email);
    		
    		try {
    			RequestRCDAO rDAO =  new RequestRCDAO();
    			RequestRC rRC = rDAO.doRetrieveRequestRCByStudentID(email);
    			  
    				request.setAttribute("rRCDate", rRC.getSubmissionDate().toString());
    				request.setAttribute("rRCState", rRC.getState().toString());
    				
    				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
    				requestDispatcher.forward(request, response);    	
    				return;
    				
    		}catch (Exception e) {
				e.getMessage();
			}
    				

    	}

    	/**
    	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doGet(request, response);
    		
    	}
    		
    }


