package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ValidatedExam;

class ValidateExamTest {
	
	ValidatedExam exam1;
	ValidatedExam exam2;

	@BeforeEach
	void setUp() throws Exception {
		exam1 = new ValidatedExam();
		exam1.setReportID(0);
		exam1.setExamName("test");
		exam1.setValidatedCFU(0);
		exam1.setValidationProcedure("test");
		exam1.setVExamID(0);
		exam1.getVExamID();
		
	}

	@Test
	void test() {
		exam2= new ValidatedExam(exam1.getReportID(),exam1.getExamName(),exam1.getValidatedCFU(),exam1.getValidationProcedure());
		exam2.toString();
	}

}
