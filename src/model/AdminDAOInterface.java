package model;

public interface AdminDAOInterface {
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return admin object
	 */
	public Admin doRetrieveAdmin(String email, String password);
}
