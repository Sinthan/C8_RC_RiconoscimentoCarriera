package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Suggestion;
import model.SuggestionDAO;

class SuggestionDAOTest extends Mockito{
	
	Suggestion suggestion;
	SuggestionDAO suggDAO;
	

	@BeforeEach
	void setUp() throws Exception {
		suggestion = mock(Suggestion.class);
		suggDAO = new SuggestionDAO();
	}

	@Test
	void insertSuggestiontest() {
		suggDAO.insertSuggestion(suggestion);
	}
	
	@Test
	void doRetrieveSuggestionByNameTest() {
		suggDAO.doRetrieveSuggestionByName("Università degli Studi di NAPOLI Federico II","Analisi Matematica I");
	}
	
	@Test
	void doRetrieveSuggestionByNameCFUTest() {
		suggDAO.doRetrieveSuggestionByName("Università degli Studi di NAPOLI Federico II", "Analisi Matematica I",9);
		
	}

}
