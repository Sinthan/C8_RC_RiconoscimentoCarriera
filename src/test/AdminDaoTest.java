package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals; 
import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.Admin;
import model.AdminDAO;



class AdminDAOTest extends Mockito {

	AdminDAO aDAO;
	
	@BeforeEach
	public void setUp() {
		aDAO = new AdminDAO();
	}
	
	@Test // Utente estratto correttamente dal database
	  void testDoRetriveAdminOK() throws ServletException, IOException {
		Admin a = aDAO.doRetrieveAdmin("fferrucci@unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertNotEquals(null, a);
	}
	
	@Test // Utente non estratto correttamente dal database (email errata)
	  void testDoRetriveAdminFailEmail() throws ServletException, IOException {
		Admin a = aDAO.doRetrieveAdmin("fferruckjhii@unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertEquals(null, a);
	}
	
	@Test // Utente non estratto correttamente dal database (password errata)
	  void testDoRetriveAdminFailPws() throws ServletException, IOException {
		Admin a = aDAO.doRetrieveAdmin("fferrucci@unisa.it", "9611ed3906aa7f5c0c580");
	    assertEquals(null, a);
	}
	
	@Test // Utente non estratto correttamente dal database (password ed email errata)
	  void testDoRetriveAdminFailEmailPws() throws ServletException, IOException {
		Admin a = aDAO.doRetrieveAdmin("fferrucci@unisa.it", "9611e444419aa7f5c0c580");
	    assertEquals(null, a);
	}
}