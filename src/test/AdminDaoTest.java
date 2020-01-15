package test;

import model.Admin;
import model.AdminDAO;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;




class AdminDaoTest extends Mockito {

  AdminDAO aDAO;
  Admin a;

  @BeforeEach
  public void setUp() {
    aDAO = new AdminDAO();
    a = new Admin();
  }

  @Test // Utente estratto correttamente dal database
  void testDoRetriveAdminOK() throws ServletException, IOException {
    a = aDAO.doRetrieveAdmin("fferrucci@unisa.it", "4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b");
    assertNotNull(a);
  }

  @Test // Utente non estratto correttamente dal database (email errata)
  void testDoRetriveAdminFailEmail() throws ServletException, IOException {
    a = aDAO.doRetrieveAdmin("fferruckjhii@unisa.it", "4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b");
    assertEquals(null, a);
  }

  @Test // Utente non estratto correttamente dal database (password errata)
  void testDoRetriveAdminFailPws() throws ServletException, IOException {
    a = aDAO.doRetrieveAdmin("fferrucci@unisa.it", "9611ed3906aa7f5c0c580");
    assertEquals(null, a);
  }

  @Test // Utente non estratto correttamente dal database (password ed email errata)
  void testDoRetriveAdminFailEmailPws() throws ServletException, IOException {
    a = aDAO.doRetrieveAdmin("fferrcci@unisa.it", "9611e444419aa7f5c0c580");
    assertEquals(null, a);
  }
}
