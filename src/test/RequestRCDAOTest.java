package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.RequestRC;
import model.RequestRCDAO;
import model.Student;
import model.Exam;
import model.RCState;

class RequestRCDAOTest {
	
	RequestRCDAO requestRCDAO;
	HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	RequestRC r;
	int result;
	RCState state;
	Student s ;
	
	//@BeforeEach
	void setUp() throws Exception {
		requestrcDAO = new RequestRCDAO();
		r = new RequestRC();
		s= new Student();
	}
	
	RequestRCDAO requestrcDAO = new RequestRCDAO();
	

	@Test// Richiesta estratta correttamente
	void testdoRetrieveRequestRCByStudentID() {
		RequestRC r = requestrcDAO.doRetrieveRequestRCByStudentID("g.rossi31@studenti.unisa.it");
		assertNotNull(r);
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
		 state = RCState.refused;
		ArrayList<RequestRC> list = requestrcDAO.doRetrieveAllRequestRCBystate(state);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}
	
	@Test
	public void updateStateTest() {
		state = RCState.approved;
		r = requestrcDAO.doRetrieveRequestRCByStudentID("g.rossi31@studenti.unisa.it");
		int idRequest = r.getRequestRCID();
		int result =requestrcDAO.updateState(state,idRequest);
		assertEquals(0, result);
	}
	
	@Test
	public void doRetrieveRequestRCByRequestIDTest() {
		r = requestrcDAO.doRetrieveRequestRCByStudentID("g.rossi31@studenti.unisa.it");
		RequestRC testRequest = requestrcDAO.doRetrieveRequestRCByRequestID(r.getRequestRCID());
		assertNotNull(testRequest);
	}
	

	@AfterEach
	public void resetValueStateAfterTest() {
		state = RCState.refused;
		r = requestrcDAO.doRetrieveRequestRCByStudentID("g.rossi31@studenti.unisa.it");
		int idRequest = r.getRequestRCID();
		requestrcDAO.updateState(state,idRequest);
	}
	
	

}
