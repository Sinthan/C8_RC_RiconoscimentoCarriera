package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Student;
import model.StudentDAO;


class StudentDAOTest extends Mockito {

	StudentDAO sDAO;
	
	
	@BeforeEach
	public void setUp() {
		sDAO = new StudentDAO();
			
	}
	
	
	@Test // Utente estratto correttamente dal database
	  void testDoRetriveStudentOK() throws ServletException, IOException {
		Student s = sDAO.doRetrieveStudent("d.taffuri@studenti.unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertNotEquals(null,s);
	}
	
	@Test // Utente non estratto correttamente dal database (email errata)
	  void testDoRetriveStudentFailEmail() throws ServletException, IOException {
		Student s = sDAO.doRetrieveStudent("r.deluca@stud3enti.unisa.it", "9611edf7f716b00c8a44441973906aa7f5c0c580");
	    assertEquals(null,s);
	}
	
	@Test // Utente non estratto correttamente dal database (password errata)
	  void testDoRetriveStudentFailPws() throws ServletException, IOException {
		Student s = sDAO.doRetrieveStudent("r.deluca@studenti.unisa.it", "9611ed3906aa7f5c0c580");
	    assertEquals(null,s);
	}
	
	@Test // Utente non estratto correttamente dal database (password ed email errata)
	  void testDoRetriveStudentFailEmailPws() throws ServletException, IOException {
		Student s = sDAO.doRetrieveStudent("r.deluca@studedednti.unisa.it", "9611e444419aa7f5c0c580");
	    assertEquals(null,s);
	}
	
	@Test // Utente gi� presente nel database
	void testInserteStudenteFail() throws ServletException, IOException{
		int result = sDAO.insertStudent("dekio.95@hotmail.it", "vin", "dek", 0, 'M', "123456789", null);
		assertEquals(0, result);
	}
	
	@Test // Utente non presente ed inserito correttamenete
	void testInserteStudenteOK() throws ServletException, IOException{
		int result = sDAO.insertStudent("gerardo@gmail.it", "gerardo", "damiano", 0, 'M', "123456789",null);
		assertEquals(1, result);
	}
	
	@AfterEach
	public void deleteAddUser() {
		sDAO.deleteStudentByEmail("gerardo@gmail.it");
	}
	
}