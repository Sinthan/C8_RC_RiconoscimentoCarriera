package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RequestRC;
import model.RequestRCDAO;
import model.State;

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
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		State state = new State(0,"needsUCValidation");
		RequestRCDAO reqDao = new RequestRCDAO();
		ArrayList<RequestRC> reqList  =  reqDao.doRetrieveAllRequestRCBystate(state);
		request.setAttribute("reqList", reqList );
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIUC/homeRCUC.jsp");		
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
