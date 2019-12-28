package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for ExamDAO
 */ 

public interface ExamDAOInterface {

	public int insertExam(Exam exam);
	public ArrayList<Exam> doRetrieveAllExamsByRequestRCID(int requestRCID);
	public Exam doRetrieveExam(int requestRCID, int ExamID);
	public int deleteExamsByRequestID(int id);
	public Exam doRetrieveExamByID(int examID);
}