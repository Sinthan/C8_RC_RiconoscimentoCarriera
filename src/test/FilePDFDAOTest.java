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
	
	@BeforeEach
	public void setUp() {
		filePDFDAO = new FilePDFDAO();
	}
	
	
}	