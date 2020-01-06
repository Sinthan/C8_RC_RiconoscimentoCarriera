package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;

/**
 * Servlet implementation class ProofForViewRCRequestAdmin
 */
@WebServlet("/ProofForViewRCRequestAdmin")
public class ProofForViewRCRequestAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
	public ProofForViewRCRequestAdmin() {
    		super();
    		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setEmail("fferrucci@unisa.it");
		admin.setName("Filomena");
		admin.setSex('M');
		admin.setSurname("Ferrucci");
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("requestRCID", 12);
		request.setAttribute("flag", 2);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AdminManagement");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
