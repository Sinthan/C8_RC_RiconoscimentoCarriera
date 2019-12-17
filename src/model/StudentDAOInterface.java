package model;
import model.Student;
import java.sql.Date;
/**
 * 
 * interface for StudentDAO
 *
 */
public interface StudentDAOInterface {
	
		
	public Student doRetrieveStudentByEmail(String email);
	public int deleteStudentByEmail(String email);
	public int deleteOldExternalStudent();
	
	/**
	 * the method takes care of validation during the login phase 
	 * @param email
	 * @param password
	 * @return object Student 
	 */
	public Student doRetrieveStudent(String email, String password);
	
	/**
	 * this method takes care of registering a new student
	 * @param email
	 * @param name
	 * @param surname
	 * @param userType
	 * @param sex
	 * @param password
	 * @param date
	 * @return param int 
	 */
	public int insertStudent(String email, String name, String surname, int userType, char sex, String password, Date date);

}
