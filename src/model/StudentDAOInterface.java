package model;

import java.sql.Date;
import model.Student;

/**
 * interface for StudentDAO.
 *
 */
public interface StudentDAOInterface {

  public int insertStudent(Student s);

  public Student doRetrieveStudentByEmail(String email);

  public int deleteStudentByEmail(String email);
  // public int deleteOldExternalStudent();

  /**
   * the method takes care of validation during the login phase.
   * 
   * @param email is the id of the student
   * @param password is the password of the student
   * @return object Student
   */
  public Student doRetrieveStudent(String email, String password);

  /**
   * this method takes care of registering a new student.
   * 
   * @param email is the id of the student
   * @param name is the name of the student
   * @param surname of student
   * @param userType 
   * @param sex
   * @param password
   * @param date
   * @return param int
   */
  public int insertStudent(String email, String name, String surname, int userType, char sex,
      String password, Date date);

}
