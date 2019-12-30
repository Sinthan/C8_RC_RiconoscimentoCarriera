package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.RequestRC;
import model.RequestRCDAO;

class RequestRCDAOTest {

	RequestRCDAO requestrcDAO = new RequestRCDAO();
	

	@Test// Richiesta estratta correttamente
	void testdoRetrieveRequestRCByStudentID() {
		RequestRC r = requestrcDAO.doRetrieveRequestRCByStudentID("provaRC1@unisa.it");
		assertNotEquals(r, null);
	}
	
	@Test// Richiesta non estratta correttamente 
	void testdoRetrieveRequestRCByStudentIDfail() {
		RequestRC r = requestrcDAO.doRetrieveRequestRCByStudentID("prkasdfgh@unisa.it");
		assertEquals(r, null);
	}
	
	@Test// Richiesta estratta correttamente
	void testdoRetrieveRequestRCByRequestID() {
		RequestRC r = requestrcDAO.doRetrieveRequestRCByRequestID(1);
		assertNotEquals(r, null);
	}
	
	@Test// Richiesta non estratta correttamente 
	void testdoRetrieveRequestRCByRequestIDfail() {
		RequestRC r = requestrcDAO.doRetrieveRequestRCByRequestID(654321);
		assertEquals(r, null);
	}
	

}
