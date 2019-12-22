package model;
import java.util.ArrayList;

/**
 * Interface for ExamDAO
 */ 

public interface ExamDAOInterface {

	public int insertExam(Exam exam);
	public ArrayList<Exam> doRetrieveAllExamsByIDRequestRC(int requestRCID);
	public int doRetrieveExam(int requestRCID, int ExamID);
	public int deleteExamsByRequestID(int id);
}