package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model.Exam;
import model.ExamDAO;
import model.RequestRC;
import model.University;

class ExamDAOTest {
	
	ExamDAO exD;
	Exam ex;
	int result;

	@BeforeEach
	void setUp() throws Exception {
		exD = new ExamDAO();
		ex = new Exam();
	}

	@Test
	void testinsertExamOK() throws SQLException {
		ex.setName("testtest");
		ex.setCFU(9);
		ex.setProgramLink("//link di riferimento//");
		int i = exD.insertExam(ex);
		assertNotNull(i);
	}
	
	@Test
	void testInsertExamFailName() {
		ex.setName("");
		ex.setCFU(9);
		ex.setProgramLink("//link di riferimento//");
		int i = exD.insertExam(ex);
		assertEquals(-4, i);
	}
	
	@Test
	void testInsertExamFailCFU() {
		ex.setName("ingegneria del test");
		ex.setCFU(-1);
		ex.setProgramLink("//link di riferimento//");
		int i = exD.insertExam(ex);
		assertEquals(-4, i);
	}
	
	@Test
	void testInsertExamFailLink() {
		ex.setName("ingegneria del test");
		ex.setCFU(9);
		ex.setProgramLink("");
		int i = exD.insertExam(ex);
		assertEquals(-4, i);
	}	
	
	@Test// Esame estratto correttamente
	void testdoRetrieveExamByID() {
		Exam ex = exD.doRetrieveExamByID(1);
		assertNotEquals(ex, null);
	}
	
	@Test// Esame non estratto correttamente 
	void testdoRetrieveExamByIDfail() {
		Exam ex = exD.doRetrieveExamByID(532838936);
		assertEquals(ex, null);
	}
	
	@Test
	void testdoRetrieveAllExamsByRequestRCIDOK() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		list = exD.doRetrieveAllExamsByRequestRCID(1);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}	
	
	@Test
	void testdoRetrieveAllExamsByRequestRCIDfail() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		list = exD.doRetrieveAllExamsByRequestRCID(3456789);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(0, result);
	}	
	
	@Test
	public void doRetrieveMaxExamIDTest() {
		 int result = exD.doRetrieveMaxExamID();
		 assertNotNull(result);
	}
	
	@Test 
	public void  doRetrieveExamByIDTest() {
		Exam examReturn= exD.doRetrieveExamByID(1);
		assertNotNull(examReturn);
	}
	
}
