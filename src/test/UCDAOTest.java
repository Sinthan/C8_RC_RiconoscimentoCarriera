package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.UC;
import model.UCDAO;

class UCDAOTest extends Mockito {

  UCDAO ucDAO;

  @BeforeEach
  public void setUp() {
    ucDAO = new UCDAO();

  }

  @Test // Utente estratto correttamente dal database
  void testDoRetriveUCOK() throws ServletException, IOException {
    UC uc = ucDAO.doRetrieveUc("UC@unisa.it", "4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b");
    assertNotEquals(null, uc);
  }

  @Test // Utente non estratto correttamente dal database (email errata)
  void testDoRetriveUCFailEmail() throws ServletException, IOException {
    UC uc = ucDAO.doRetrieveUc("UC@usa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
    assertEquals(null, uc);
  }

  @Test // Utente non estratto correttamente dal database (password errata)
  void testDoRetriveUCFailPws() throws ServletException, IOException {
    UC uc = ucDAO.doRetrieveUc("UC@unisa.it", "9611edf7f7144441973906aa7f5c0c580");
    assertEquals(null, uc);
  }

  @Test // Utente non estratto correttamente dal database (password ed email errata)
  void testDoRetriveUCFailEmailPws() throws ServletException, IOException {
    UC uc = ucDAO.doRetrieveUc("UC@uisa.it", "9611edf76b00c4441973906aa7f5c0c580");
    assertEquals(null, uc);
  }

}
