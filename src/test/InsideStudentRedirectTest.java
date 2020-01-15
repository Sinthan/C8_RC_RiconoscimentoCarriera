package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import controller.InsideStudentRedirect;

class InsideStudentRedirectTest extends Mockito {

  HttpServletRequest request;
  HttpServletResponse response;
  RequestDispatcher requestDisp;
  InsideStudentRedirect isr;

  @BeforeEach
  void setUp() throws Exception {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    requestDisp = mock(RequestDispatcher.class);
    isr = new InsideStudentRedirect();
  }

  @Test
  void RedirectTest() throws ServletException, IOException {
    when(request.getRequestDispatcher("/WEB-INF/GUIStudentRC/homePageRCStudent.jsp"))
        .thenReturn(requestDisp);
    isr.doGet(request, response);
    verify(requestDisp).forward(request, response);
  }

}
