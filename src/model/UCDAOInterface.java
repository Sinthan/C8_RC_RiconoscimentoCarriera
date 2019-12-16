package model; 

/**
 * Interface for UCDAO
 */
import model.UC;

public interface UCDAOInterface {

	public UC doRetrieveUCByEmail (String email);
	
	
}