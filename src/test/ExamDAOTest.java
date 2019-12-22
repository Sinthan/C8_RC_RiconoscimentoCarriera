package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Exam;
import model.ExamDAO;

class ExamDAOTest {
	
	ExamDAO exD;
	Exam ex ;

	@BeforeEach
	void setUp() throws Exception {
		exD = new ExamDAO();
		ex = new Exam();
	}

	/*@Test
	void testinsertExamOK() throws SQLException {
		ex.setName("ingegneria del test");
		ex.setCFU(9);
		ex.setProgramLink("//link di riferimento//");
		int i = exD.insertExam(ex);
		assertEquals(-1, i);
	}
*/	
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
}
