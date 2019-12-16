package model;
import model.Exam; 

import java.util.ArrayList;

/**
 * ExamDAO is used for communication between Exam and DB
 */ 

public class ExamDAO implements ExamDAOInterface{

	
	/**
	 * insert new exam 
	 * @return -1 if insert failed, 0 if ok 
	 */
	public int insertExam(Exam exam) {
		int flag = 0;
		return flag;
	}
	
	/**
	 * retrieve all exams 
	 * @return arraylist of exams
	 */
	public ArrayList<Exam> doRetrieveAllExamsByIDRequestRC(int requestRCID){
		ArrayList<Exam> list = null;
		return list;
	}
	
	/**
	 * retrieve exam 
	 * @return -1 if insert failed, 0 if ok 
	 */
	public int doRetrieveExam(int requestRCID, int ExamID) {
		int flag = 0;
		return flag; 
	}
	
	/**
	 * delete exam 
	 * @return -1 if insert failed, 0 if ok 
	 */
	public int deleteExamsByRequestID(int id) {
		int flag = 0;
		return flag;
	}
	
}
