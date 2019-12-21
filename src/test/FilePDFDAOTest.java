package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.FilePDF;
import model.FilePDFDAO;
import model.UC;
import model.UCDAO;


class FilePDFDAOTest {

	FilePDFDAO filePDFDAO;
	FilePDF filePDF;
	
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
	
	
	
}	