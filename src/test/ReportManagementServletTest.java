package test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import controller.ReportManagementServlet;

class ReportManagementServletTest extends Mockito {

  ReportManagementServlet rm;
  HttpServletRequest request;
  HttpServletResponse response;
  RequestDispatcher dsp;
  HttpSession sessione;

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    Path root = Paths.get(".").normalize().toAbsolutePath();
    System.out.println(root);
    String projectFolder = "C8_RC_RiconoscimentoCarriera";
    int extraPathIndex = root.toString().indexOf(projectFolder);
    String catalinaRoot = root.toString().substring(0, extraPathIndex - 1);
    System.setProperty("catalina.base", catalinaRoot + "/.metadata/");
    rm = new ReportManagementServlet();
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    sessione = mock(HttpSession.class);
    dsp = mock(RequestDispatcher.class);
  }

  @Test
  void validationModeTooLongtest() throws ServletException, IOException {
    int i = 1;
    when(request.getSession()).thenReturn(sessione);
    when(request.getParameter("idRequestRC")).thenReturn("4");
    when(request.getParameter("rowCount")).thenReturn("2");
    String examName = "Analisi I";
    when(request.getParameter("validatedExamName" + i)).thenReturn(examName);
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("validatedExamMode" + i)).thenReturn(
        "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\n");
    rm.doGet(request, response);
    String message = "Impossibile salvare la bozza, la modalita di convalida dell'esame " + examName
        + " supera i 5000 caratteri";
    verify(response).sendRedirect("/EnglishValidation/AdminHome?errorVRA1=" + message);
  }

  @Test
  void noteTooLongtest() throws ServletException, IOException {
    int i = 1;
    when(request.getSession()).thenReturn(sessione);
    when(request.getParameter("idRequestRC")).thenReturn("4");
    when(request.getParameter("additionalNotes")).thenReturn(
        "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\n");
    when(request.getParameter("rowCount")).thenReturn("2");
    String examName = "Analisi I";
    when(request.getParameter("validatedExamName" + i)).thenReturn(examName);
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("validatedExamMode" + i)).thenReturn("Complimenti\n");
    rm.doGet(request, response);
    String message = "Impossibile salvare la bozza, la nota supera i 5000 caratteri";
    verify(response).sendRedirect("/EnglishValidation/AdminHome?errorVRA1=" + message);
  }

  @Test
  void testSuggestionNotPresent() throws ServletException, IOException {
    int i = 1;
    when(request.getSession()).thenReturn(sessione);
    when(request.getParameter("idRequestRC")).thenReturn("4");
    when(request.getParameter("additionalNotes")).thenReturn("Complimenti");
    when(request.getParameter("rowCount")).thenReturn("2");
    when(request.getParameter("validatedExamName" + i)).thenReturn("Analisi xxxI");
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("suggOverwrite" + i)).thenReturn("yes");
    when(request.getParameter("validatedExamMode" + i)).thenReturn("Complimenti\n");
    when(request.getParameter("closeRCRequestBtn")).thenReturn("ok");
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("externalExamCFU" + i)).thenReturn("9");
    when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
    rm.doGet(request, response);
    verify(dsp).forward(request, response);
  }

  @Test
  void testSuggestionPresent() throws ServletException, IOException {
    int i = 1;
    when(request.getSession()).thenReturn(sessione);
    when(request.getParameter("idRequestRC")).thenReturn("4");
    when(request.getParameter("additionalNotes")).thenReturn("Complimenti");
    when(request.getParameter("rowCount")).thenReturn("2");
    when(request.getParameter("validatedExamName" + i)).thenReturn("Analisi I");
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("suggOverwrite" + i)).thenReturn("yes");
    when(request.getParameter("validatedExamMode" + i)).thenReturn("Complimenti\n");
    when(request.getParameter("closeRCRequestBtn")).thenReturn("ok");
    when(request.getParameter("validatedExamCFU" + i)).thenReturn("9");
    when(request.getParameter("externalExamCFU" + i)).thenReturn("9");
    when(request.getRequestDispatcher("/AdminHome")).thenReturn(dsp);
    rm.doGet(request, response);
    verify(dsp).forward(request, response);
  }
}
