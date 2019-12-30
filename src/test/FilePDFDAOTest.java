package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Exam;
import model.FilePDF;
import model.FilePDFDAO;


class FilePDFDAOTest {

	FilePDFDAO filePDFDAO;
	FilePDF filePDF;
	int result;
	
	@BeforeEach
	public void setUp() throws Exception {
		filePDFDAO = new FilePDFDAO();
		filePDF = new FilePDF();
	}
	
	/*@Test
	void testInsertFilePDFOK() throws SQLException {
		filePDF.setPDFLink("FILEPDFCARR1");
		filePDF.setRequestRCID(1);
		
		int i = filePDFDAO.insertFilePDF(filePDF);
		assertEquals(-1, i);
	}
*/
	
	@Test
	void testInsertFilePDFFailPDFLink() {
		filePDF.setPDFLink("");
		filePDF.setRequestRCID(1);
		
		int i = filePDFDAO.insertFilePDF(filePDF);
		assertEquals(-2, i);
	}
	
	@Test
	void testInsertFilePDFFailRequestRCID() {
		filePDF.setPDFLink("FILEPDFCARR1");
		filePDF.setRequestRCID(-1);
		
		int i = filePDFDAO.insertFilePDF(filePDF);
		assertEquals(-2, i);
	}
	
	@Test
	void testdoRetrieveAllFilePDFByIDRequestRCOK() {
		ArrayList<FilePDF> list = new ArrayList<FilePDF>();
		list = filePDFDAO.doRetrieveAllFilePDFByIDRequestRC(1);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(1, result);
	}	
	
	@Test
	void testdoRetrieveAllFilePDFByIDRequestRCfail() {
		ArrayList<FilePDF> list = new ArrayList<FilePDF>();
		list = filePDFDAO.doRetrieveAllFilePDFByIDRequestRC(3456789);
		if(list.isEmpty()) {
			result = 0;
		}else {
			result = 1;
		}
		assertEquals(0, result);
	}	
	
}	