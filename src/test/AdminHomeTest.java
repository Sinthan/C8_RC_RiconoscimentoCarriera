package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import controller.AdminHome;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class AdminHomeTest extends Mockito {

  AdminHome ah;
  HttpServletRequest request;
  HttpServletResponse response;
  RequestDispatcher requestDisp;

  @BeforeEach
  void setUp() throws Exception {
    ah = new AdminHome();
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    requestDisp = mock(RequestDispatcher.class);
  }

  @Test
  void redirectAdminHomeTest() throws ServletException, IOException {
    when(request.getRequestDispatcher("/WEB-INF/GUIAdminRC/homeRCPCD.jsp")).thenReturn(requestDisp);
    ah.doGet(request, response);
    verify(requestDisp).forward(request, response);

  }

}
