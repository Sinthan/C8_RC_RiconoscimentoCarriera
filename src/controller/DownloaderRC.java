package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.test.web.servlet.ResultMatcher;
import model.Student;

/**
 * Servlet implementation class DownloaderRC.
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
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String value = request.getParameter("pdfvalue");
    String pathpdf = request.getParameter("pathpdf");
    Student student = (Student) request.getSession().getAttribute("userRC");
    String file = null;
    // verifico se il file da scaricare è l'ID o CP
    if (value.equalsIgnoreCase("id")) {
      file = "ID.pdf";
    } else {
      file = "CP.pdf";
    }
    String path = pathpdf + file;
    File fileF = new File(path);

    if (fileF.length() != 0) {
      response.setHeader("Content-Disposition",
          "attachment; filename=\"" + student.getEmail() + file + "\"");
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
    } else if ((fileF.length() == 0)) {
      goBackWithError("Impossibile scaricare il PDF, file non trovato.", request, response);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

  private void goBackWithError(String message, HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    System.out.println(message);
    request.setAttribute("errorUC", message);
    RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/GUIUC/viewRCRequestUC.jsp");
    dis.forward(request, response);
  }

}
