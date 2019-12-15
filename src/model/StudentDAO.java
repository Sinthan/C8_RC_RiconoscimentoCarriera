package model;
import model.Student;

/**
 * StudentDAO is used for communication between Student and DB
 *
 */
public class StudentDAO implements StudentDAOInterface {

	/**
	 * Insert new student  
	 * 
	 * @return -1 if insert failed, 0 if insert ok
	 */
	public int insertStudent(Student s) {
		
		int flag = 0;
		return flag;
	}
	
	/**
	 * Retrieve the student through his email        
	 * @return student object
	 */
	public Student doRetrieveStudentByEmail(String email) {
		
		Student s = null;
		return s;
	}
	
	/**
	 * Delete student having his email        
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteStudentByEmail(String email) {
		
		int flag = 0;
		return flag;
		
	}
	
	/**
	 * Delete external student when his registration date is too old        
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteOldExternalStudent() {
		
		int flag = 0;
		return flag;
		
	}
	
}
