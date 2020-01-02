package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.RequestRC;
import model.RequestRCDAO;
import model.RCState;

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
		RCState state = RCState.needsUCValidation;
		ArrayList<RequestRC> list = requestrcDAO.doRetrieveAllRequestRCBystate(state);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}
	
	@Test
	void testdoRetrieveAllRequestRCBystatefail() {
		RCState state = RCState.refused;
		ArrayList<RequestRC> list = requestrcDAO.doRetrieveAllRequestRCBystate(state);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}

}
