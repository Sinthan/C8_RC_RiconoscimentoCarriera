package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ReportDAO;

class ReportDAOTest {
	
	ReportDAO repDAO;
	int reportID;

	@BeforeEach
	void setUp() throws Exception {
		repDAO = new ReportDAO();
		
	}

	@Test
	void updateNoteTest() {
		repDAO.updateNote(4, "test");
	}
	
	@Test
	void createReportTest() {
		reportID= repDAO.createReport();
	}
	
	@Test
	void deleteReportTest() {
		repDAO.deleteReport(reportID);
	}
	
	@Test
	void returnReportIDTest() {
		repDAO.doRetrieveReportByReportID(4);
	}
	
	@Test 
	void lastReturn() {
		repDAO.doRetrieveLastReportID();
	}

}
