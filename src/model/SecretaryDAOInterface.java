package model;

public interface SecretaryDAOInterface {

	/**
	 * 
	 * @param email
	 * @param password
	 * @return object Secretary
	 */
	public Secretary doRetrieveSecretary(String email, String password);
	
}
