package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.RequestRC;
import model.RequestRCDAO;
import model.State;

class RequestRCDAOTest {
	
	RequestRCDAO requestRCDAO;
	RequestRC r;
	int result;
	
	//@BeforeEach
	void setUp() throws Exception {
		requestRCDAO = new RequestRCDAO();
		r = new RequestRC();
	}
	
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
	
	@Test
	void testdoRetrieveAllRequestRCBystateOK() {
		State st = new State(1,"Stato presente");
		ArrayList<RequestRC> list = requestrcDAO.doRetrieveAllRequestRCBystate(st);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}
	
	@Test
	void testdoRetrieveAllRequestRCBystatefail() {
		State st = new State(5,"Stato non presente");
		ArrayList<RequestRC> list = requestrcDAO.doRetrieveAllRequestRCBystate(st);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(0, result);
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
