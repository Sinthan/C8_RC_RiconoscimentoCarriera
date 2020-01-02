package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

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
		
    int reqID = Integer.parseInt(request.getParameter("idRequestRC"));
    StudentDAO sDAO = new StudentDAO();
    RequestRCDAO reqDAO = new RequestRCDAO();
    ExamDAO examDAO = new ExamDAO();
    RequestRC reqRC = reqDAO.doRetrieveRequestRCByRequestID(reqID);
    request.getSession().setAttribute("reqRC", reqRC);
		
	//Ricavo i dati dello studente partendo dalla richiesta 
    Student userRC = sDAO.doRetrieveStudentByEmail(reqRC.getStudentID());
    request.getSession().setAttribute("userRC",userRC);
    //Ricavo il path della cartella file dello studente
    String path=  Utils.getProjectPath() +  "\\WebContent\\DocumentsRequestRC\\" + userRC.getEmail() + "\\";
    path = path.replace("/", "\\");
	request.getSession().setAttribute("filePath", path);
    //Carica la lista degli esami inseriti manualmente dallo studente
    ArrayList<Exam> eList = examDAO.doRetrieveAllExamsByRequestRCID(reqRC.getRequestRCID());
    request.getSession().setAttribute("exams", eList);
    
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
