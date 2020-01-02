package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.test.web.servlet.ResultMatcher;

import model.Student;

/**
 * Servlet implementation class DownloaderRC 
 */
@WebServlet("/DownloaderRC")
public class DownloaderRC extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DownloaderRC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("pdfvalue");
		String pathpdf = request.getParameter("pathpdf");
		Student student = (Student) request.getSession().getAttribute("userRC");
		String file = null;
		//verifico se il file da scaricare è l'ID o CP
		if(value.equalsIgnoreCase("id")) {
			file = "ID.pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "ID" + student.getEmail() +".pdf\"");
			}else {
			file = "CP.pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "CP" + student.getEmail() + ".pdf\"");
		}
		String path = pathpdf + file;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setContentType("APPLICATION/OCTET-STREAM");
		
		FileInputStream fileInputStream = new FileInputStream(path);
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
			}
		fileInputStream.close();
		out.close();
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
