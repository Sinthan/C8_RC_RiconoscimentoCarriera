package model;

/*
 * PCD
 * Class that uses the PCD's 
 * (Presidente del Consiglio Didattico) informations
 */
public class PCD {
	private String email;
	private String name;
	private String surname;
	private char sex;
	private String password;
	private int userType;

	
	
	/*
	 * Constructor
	 * @param email is the address used by user to log in
	 * @param name is the name of the user
	 * @param surname is the surname of the user
	 * @param sex specifies sex of the user with letters (M,F)
	 * @param password is the password used by user to log in
	 * @param userType specifies the role of the user (0,1,2)
	 */
	public PCD (String email, String name, String surname, char sex, String password, int userType){
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.password = password;
		this.userType = userType;
	
	
	}
	
	/*
	 * Void Constructor
	 */
	public PCD() {
		
	}
	
	
	
	
	/*
	 * toString method
	 */
	public String toString(){
		return email;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Getter and setter
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}



}