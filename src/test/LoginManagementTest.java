package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.LoginManagement;


class LoginManagementTest extends Mockito {

  private LoginManagement servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  /**
   * Before.
   */
  @BeforeEach
  public void setUp() {
    servlet = new LoginManagement();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  // Test Login
  // Admin
  @Test // Caso Login Admin: Login effettuato con successo
  void testLoginAdminOK() throws ServletException, IOException {
    request.addParameter("email", "fferrucci@unisa.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }

  @Test // Caso Login Admin: Utente non presente nel Database (email errata)
  void testLoginAdminFailEmail() throws ServletException, IOException {
    request.addParameter("email", "studentenullo@gmail.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Admin: Utente non presente nel Database (password errata)
  void testLoginAdminFailPwd() throws ServletException, IOException {
    request.addParameter("email", "fferrucci@unisa.it");
    request.addParameter("password", "pasrd");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Admin: Utente non presente nel Database (password errata ed email errata)
  void testLoginAdminFailEmailPwd() throws ServletException, IOException {
    request.addParameter("email", "fferrucciiii@unisa.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }


  // Test Login
  // Segreteria

  @Test // Caso Login Secretary: Login effettuato con successo
  void testLoginSecretaryOK() throws ServletException, IOException {
    request.addParameter("email", "segreteria@unisa.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }

  @Test // Caso Login Secretary: Utente non presente nel Database(email errata)
  void testLoginSecretaryFail() throws ServletException, IOException {
    request.addParameter("email", "segreteriaa@unisa.it");
    request.addParameter("password", "123456789");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Secretary: Utente non presente nel Database(password errata)
  void testLoginSecretaryPwd() throws ServletException, IOException {
    request.addParameter("email", "segreteria@unisa.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Secretary: Utente non presente nel Database(password ed email errati)
  void testLoginSecretaryEmailPwd() throws ServletException, IOException {
    request.addParameter("email", "segreteriaa@unisa.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  // Test Login
  // Studente
  @Test // Caso Login Student: Login effettuato con successo
  void testLoginStudentOK() throws ServletException, IOException {
    request.addParameter("email", "g.rossi31@studenti.unisa.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }

  @Test // Caso Login Student: Utente non presente nel Database(email errata)
  void testLoginStudentFail() throws ServletException, IOException {
    request.addParameter("email", "prova@studk.unisa.it");
    request.addParameter("password", "123456789");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Student: Utente non presente nel Database(password errata)
  void testLoginStudentPwd() throws ServletException, IOException {
    request.addParameter("email", "prova@studenti.unisa.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login Student: Utente non presente nel Database(password ed email errati)
  void testLoginStudentEmailPwd() throws ServletException, IOException {
    request.addParameter("email", "prova@stui.unisa.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  // Test Login
  // UC________________________________________________________________________________________
  @Test // Caso Login UC: Login effettuato con successo
  void testLoginUCOK() throws ServletException, IOException {
    request.addParameter("email", "UC@unisa.it");
    request.addParameter("password", "password");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }

  @Test // Caso Login UC: Utente non presente nel Database(email errata)
  void testLoginUCFail() throws ServletException, IOException {
    request.addParameter("email", "UCnisa.it");
    request.addParameter("password", "123456789");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login UC: Utente non presente nel Database(password errata)
  void testLoginUCPwd() throws ServletException, IOException {
    request.addParameter("email", "UC@unisa.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }

  @Test // Caso Login UC: Utente non presente nel Database(password ed email errati)
  void testLoginUCEmailPwd() throws ServletException, IOException {
    request.addParameter("email", "UCa@stuMFIEW.it");
    request.addParameter("password", "12345678910");
    servlet.doPost(request, response);
    assertEquals(null, response.getContentType());
  }
}
