package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import model.FilePDF;
import model.FilePDFDAO;


class FilePDFDAOTest {
	FilePDFDAO pdfDAO = new FilePDFDAO();
	
	
	
	@Test// File inserito correttamente
	void testInsertFilePDF() {
		FilePDF filepdf = new FilePDF("path", 1);
		int result = 0;
		try {
			result = pdfDAO.insertFilePDF(filepdf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(result , 1);
	}
	
	@Test// File non inserito correttamente ID richiesta non trovato
	void testInsertFilePDFfail() {
		FilePDF filepdf = new FilePDF("path2", 124);
		int result = 0;
		try {
			result = pdfDAO.insertFilePDF(filepdf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(result , 0);
	}
}
