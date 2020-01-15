package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RequestRC;
import model.RequestRCDAO;
import model.SortByName;
import model.Student;
import model.University;
import model.UniversityDAO;

/**
 * Servlet implementation class StudentRCRequestRedirector.
 */
@WebServlet("/StudentRCRequestRedirector")
public class StudentRCRequestRedirector extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet().
   */
  public StudentRCRequestRedirector() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int flag = Integer.parseInt(request.getParameter("flag"));
    String delete = request.getParameter("delete");
    System.out.println(delete);

    if (flag == 1 && delete != null) {
      HttpSession sessione = request.getSession();
      Student s = (Student) sessione.getAttribute("user");
      request.getSession().setAttribute("flag", 1);
      RequestRCDAO rcDao = new RequestRCDAO();
      RequestRC reqRc = rcDao.doRetrieveRequestRCByStudentID(s.getEmail());
      rcDao.deleteRequestRCByRequestID(reqRc.getRequestRCID());
      RequestDispatcher dis = request.getRequestDispatcher("/StudentManagement");
      dis.forward(request, response);
    } else if (flag == 1 && delete == null) {
      HttpSession sessione = request.getSession();
      Student s = (Student) sessione.getAttribute("user");
      String email = s.getEmail();
      RequestRCDAO rDao = new RequestRCDAO();
      RequestRC rRC = rDao.doRetrieveRequestRCByStudentID(email);
      if (rRC == null) {
        UniversityDAO uniDAO = new UniversityDAO();
        List<University> universities = uniDAO.doRetrieveAllUniversity();
        Collections.sort(universities, new SortByName());
        request.getSession().setAttribute("universities", universities);
        RequestDispatcher requestDispatcher =
            request.getRequestDispatcher("/WEB-INF/GUIStudentRC/createRCRequest1.jsp");
        requestDispatcher.forward(request, response);
      } else {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(rRC.getSubmissionDate());
        request.setAttribute("rRCDate", date);
        request.setAttribute("rRCState", rRC.getState().toString());
        RequestDispatcher requestDispatcher =
            request.getRequestDispatcher("/WEB-INF/GUIStudentRC/viewRCRequestStatus.jsp");
        requestDispatcher.forward(request, response);
      }

    } else if (flag == 2) {
      RequestDispatcher dis = request.getRequestDispatcher("/_areaStudent/viewRequest.jsp");
      dis.forward(request, response);
    }
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
