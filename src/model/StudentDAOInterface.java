package model;
import model.Student;

/**
 * 
 * interface for StudentDAO
 *
 */
public interface StudentDAOInterface {
	
	public int insertStudent(Student s);	
	public Student doRetrieveStudentByEmail(String email);
	public int deleteStudentByEmail(String email);
	public int deleteOldExternalStudent();
	

}
