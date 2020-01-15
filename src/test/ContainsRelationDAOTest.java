package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.ContainsRelation;
import model.ContainsRelationDAO;

class ContainsRelationDAOTest extends Mockito {
	
	ContainsRelationDAO crDAO;
	ContainsRelation conR;

	@BeforeEach
	void setUp() throws Exception {
		crDAO = new ContainsRelationDAO();
		conR = mock(ContainsRelation.class);
	}

	@Test
	void insertContainsRelationtest() {
		crDAO.insertContainsRelation(conR);
	}

	@Test
	void doRetrieveAllContainsRelationByIDExamTest() {
		crDAO.doRetrieveAllContainsRelationByIDExam(0);
	}
	
}
