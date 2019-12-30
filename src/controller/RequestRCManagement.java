package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.RequestRC;
import model.RequestRCDAO;
import model.SenderMail;
import model.State;
import model.Student;
import model.UC;

/**
 * Servlet implementation class RequestRCManagement
 */

@WebServlet("/RequestRCManagement")
public class RequestRCManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestRCManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		State stateAcceptByUC = new State(1,"isBeingDiscussed");
		State stateRejectByUC = new State(3,"refused");
		Object user = request.getSession().getAttribute("user");
		int result;
		RequestDispatcher disp;
		//Se la richiesta deve essere trattata dall' UC
		if(user instanceof UC) {
			String requestRCstate = request.getParameter("RequestRCstate");
			RequestRCDAO reqDAO = new RequestRCDAO();
			RequestRC requestRC = (RequestRC) request.getSession().getAttribute("reqRC");
			//Se la richiesta è stata accettata dall'UC
			if(requestRCstate.equalsIgnoreCase("true")) {
				result = reqDAO.updateState(stateAcceptByUC, requestRC.getRequestRCID());
				if(result == 1) {
					disp = request.getServletContext().getRequestDispatcher("/UCManagement");
					disp.forward(request, response);
				}else {
					request.setAttribute("errorCR1","Impossibile eseguire l'update.");
					disp = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp");
					disp.forward(request, response);
				}
				}//Se la richiesta è stata rifiutata dall'UC
			else if(requestRCstate.equalsIgnoreCase("false")) {
				//Ottengo la motivazione del rifiuto della richiesta
				String messageBody = request.getParameter("popupText");
				result = reqDAO.updateState(stateRejectByUC, requestRC.getRequestRCID());
				if(result == 1) {
					Student student = (Student) request.getSession().getAttribute("userRC");
					SenderMail email = new SenderMail();
					String message = "Gentile "+student.getName()+" " + student.getSurname() +", la sua richiesta di convalida della carriera pregressa"
									+ " è stata rifiutata per le seguenti ragioni: \n"+ messageBody +"\n"+ "Cordiali saluti.";
					email.sendMail("carrierapregressaunisa@gmail.com", student.getEmail() , "Carriera pregressa", message, null);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/UCManagement");
					requestDispatcher.forward(request, response);
				}else {
					request.setAttribute("errorCR1","Impossibile inviare l'email.");
					disp = request.getServletContext().getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp");
					disp.forward(request, response);
				}
			}
			
		}//Se la richiesta deve essere trattata dal PCD
		else if(user instanceof Admin) {
			
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
