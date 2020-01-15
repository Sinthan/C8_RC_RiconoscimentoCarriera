package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.Secretary;
import model.SecretaryDAO;

class SecretaryDAOTest extends Mockito {

  SecretaryDAO sDAO;

  @BeforeEach
  public void setUp() {
    sDAO = new SecretaryDAO();
  }

  @Test // Utente estratto correttamente dal database
  void testDoRetriveSecretaryOK() throws ServletException, IOException {
    Secretary s =
        sDAO.doRetrieveSecretary("segreteria@unisa.it", "4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b");
    assertNotEquals(null, s);
  }

  @Test // Utente non estratto correttamente dal database (email errata)
  void testDoRetriveSecretaryFailEmail() throws ServletException, IOException {
    Secretary s =
        sDAO.doRetrieveSecretary("segteria@unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
    assertEquals(null, s);
  }

  @Test // Utente non estratto correttamente dal database (password errata)
  void testDoRetriveSecretaryFailPws() throws ServletException, IOException {
    Secretary s =
        sDAO.doRetrieveSecretary("segreteria@unisa.it", "9611edf7f716b00c973906aa7f5c0c580");
    assertEquals(null, s);
  }

  @Test // Utente non estratto correttamente dal database (password ed email errata)
  void testDoRetriveSecretaryFailEmailPws() throws ServletException, IOException {
    Secretary s = sDAO.doRetrieveSecretary("ffewyibfewiufii@unisa.it", "9611e444419aac0c580");
    assertEquals(null, s);
  }
}
