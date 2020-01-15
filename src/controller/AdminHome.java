package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RCState;
import model.RequestRC;
import model.RequestRCDAO;

/**
 * Servlet implementation class AdminHome.
 */
@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet().
   */
  public AdminHome() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RCState state = RCState.isBeingDiscussed;
    RequestRCDAO reqDao = new RequestRCDAO();
    ArrayList<RequestRC> reqList = reqDao.doRetrieveAllRequestRCBystate(state);
    request.setAttribute("reqList", reqList);
    RequestDispatcher requestDispatcher =
        request.getRequestDispatcher("/WEB-INF/GUIAdminRC/homeRCPCD.jsp");
    requestDispatcher.forward(request, response);
    return;
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
