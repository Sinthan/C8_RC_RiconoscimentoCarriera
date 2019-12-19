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
	  void testDoRetriveAdminOK() throws ServletException, IOException {
		Secretary s = sDAO.doRetrieveSecretary("segreteria@unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertNotEquals(null, s);
	}
	
	@Test // Utente non estratto correttamente dal database (email errata)
	  void testDoRetriveAdminFailEmail() throws ServletException, IOException {
		Secretary s = sDAO.doRetrieveSecretary("segteria@unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertEquals(null, s);
	}
	
	@Test // Utente non estratto correttamente dal database (password errata)
	  void testDoRetriveAdminFailPws() throws ServletException, IOException {
		Secretary s = sDAO.doRetrieveSecretary("segreteria@unisa.it", "9611edf7f716b00c973906aa7f5c0c580");
	    assertEquals(null, s);
	}
	
	@Test // Utente non estratto correttamente dal database (password ed email errata)
	  void testDoRetriveAdminFailEmailPws() throws ServletException, IOException {
		Secretary s = sDAO.doRetrieveSecretary("ffewyibfewiufii@unisa.it", "9611e444419aac0c580");
	    assertEquals(null, s);
	}
}