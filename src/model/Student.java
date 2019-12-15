package model;

import interfacce.UserInterface;
import java.util.Date;

/**
 * 
 * Object Student represents a real student 
 *
 */
public class Student implements UserInterface {
	
	private String email;
	private String name;
	private String surname;
	private char sex;
	private String password;
	private int userType;
	private Date registrationDate;
  
	/**
	 * Empty Constructor.
	 */
  public Student() {}


  /**
   * Contructor.
   * 
   * @param email is the address that the Student uses to Log in the site.
   * @param name is the name of the Student.
   * @param surname is the surname of the Student.
   * @param sex specifies the sex of the Student with one letter (M,F).
   * @param password is the password that the Student uses to Log in the site.
   * @param userType specifies the type of the user.
   * @param registrationDate specifies the student's registration date 
   */
  public Student(String email, String name, String surname, char sex, String password,int userType, Date registrationDate) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.sex = sex;
    this.password = password;
    this.userType = userType;
    this.registrationDate = registrationDate;
  }

  public Student(String email, String name, String surname, char sex, String password,int userType) {
	    this.email = email;
	    this.name = name;
	    this.surname = surname;
	    this.sex = sex;
	    this.password = password;
	    this.userType = userType;
  
  }
  
  /**
   * 
   * getters and setters
   * 
   */
  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getSurname() {
    return surname;
  }

  @Override
  public char getSex() {
    return sex;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public int getUserType() {
    return userType;
  }

  public Date getRegistrationDate() {
		return registrationDate;
  }
  
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public void setSex(char sex) {
    this.sex = sex;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void setUserType(int userType) {
    this.userType = userType;
  }

  public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
}

@Override
  public boolean validate() {
    return new Stub().database.containsKey(getEmail()) 
        && new Stub().database.containsValue(getPassword());    
  }
}