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
import model.FilePDF;
import model.FilePDFDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.StudentDAO;

/**
 * Servlet implementation class UCRCRequestRedirector
 */
@WebServlet("/UCRCRequestRedirector")
public class UCRCRequestRedirector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UCRCRequestRedirector() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		int reqID = request.getParameter();
		StudentDAO sDAO = new StudentDAO();
		RequestRCDAO reqDAO = new RequestRCDAO();
		ExamDAO examDAO = new ExamDAO();
		RequestRC reqRC = reqDAO.doRetrieveRequestRCByRequestID(reqID);
		request.getSession().setAttribute("reqRC", reqRC);
		
		//Carica la lista dei file pdf caricati dall'utente
		FilePDFDAO fDAO = new FilePDFDAO();
		ArrayList<FilePDF> listPdf = fDAO.doRetrieveAllFilePDFByIDRequestRC(reqRC.getRequestRCID());
		FilePDF file1 =  listPdf.get(0);
		String pdfID = null;
		String pdfCP = null;
		
		if(file1.getPDFLink().substring(file1.getPDFLink().indexOf("\\")).equalsIgnoreCase("ID.pdf")) {
			pdfID = file1.getPDFLink();
			pdfCP = listPdf.get(1).getPDFLink();
		}else {
			pdfCP = file1.getPDFLink();
			pdfID = listPdf.get(1).getPDFLink();
		}	
		listPdf.get(0).getPDFLink();
		request.getSession().setAttribute("filepdfID", pdfID);
		request.getSession().setAttribute("filepdfCP", pdfCP);

		//Carica la lista degli esami inseriti manualmente dallo studente
		ArrayList<Exam> eList = examDAO.doRetrieveAllExamsByRequestRCID(reqRC.getRequestRCID());
		request.getSession().setAttribute("exams", eList);
		
		//Ricavo i dati dello studente partendo dalla richiesta
		Student userRC = sDAO.doRetrieveStudentByEmail(reqRC.getStudentID());
		request.getSession().setAttribute("userRC",userRC);
		*/
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp");		
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
