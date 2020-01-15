package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import controller.UCManagement;

class UCManagementTest extends Mockito {

  HttpServletResponse response;
  HttpServletRequest request;
  HttpSession sessione;
  RequestDispatcher requestDisp;
  UCManagement ucM;



  @BeforeEach
  void setUp() throws Exception {
    response = mock(HttpServletResponse.class);
    request = mock(HttpServletRequest.class);
    sessione = mock(HttpSession.class);
    requestDisp = mock(RequestDispatcher.class);
    ucM = new UCManagement();
  }

  @Test
  void soloReturnTest() throws ServletException, IOException {
    when(request.getRequestDispatcher("/WEB-INF/GUIUC/homeRCUC.jsp")).thenReturn(requestDisp);
    ucM.doGet(request, response);
    verify(requestDisp).forward(request, response);
  }

}
