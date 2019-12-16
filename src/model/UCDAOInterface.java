package model; 

/**
 * Interface for UCDAO
 */
import model.UC;

public interface UCDAOInterface {
	/**
	 * 
	 * @param email
	 * @param password
	 * @return Uc object
	 */
	public UC doRetrieveUc(String email, String password);
	
	
}