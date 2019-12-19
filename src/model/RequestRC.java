package model;

import java.util.ArrayList;
import java.util.Date;

/*
 * RequestRC
 * Class that uses the RequestRC informations
 * Different from Request 
 * (Request for English Validation, Request RC for Riconoscimento Carriera) 
 */
public class RequestRC {
	
	
	public enum RCState {
		needsUCValidation, 
		isBeingDiscussed,
		approved,
		refused;

		public static RCState fromInteger(int x) {
			switch(x) {
			case 0:
				return needsUCValidation;
			case 1:
				return isBeingDiscussed;
			case 2: 
				return approved;
			case 3: 
				return refused;
				
			}
			System.err.println("Integer");
			return null;
		}
	}
	
	
	private int requestRCID;
	private Date submissionDate;
	private RCState state;
	private String universityID;
	private int reportID;
	private String studentID;
	private ArrayList<Exam> examsList;
	
	


	/*
	 * Constructor
	 * @param submissionDate is the submission date of the request
	 * @param universityID is the unique ID of request's referred university 
	 * @param studentID refers to unique ID of the request's referred student
	 * @param examsList refers to exams listed by the student in the request
	 */
	public RequestRC(Date submissionDate, String universityID, String studentID, ArrayList<Exam> examsList) {
		this.submissionDate = submissionDate;
		this.universityID = universityID;
		this.studentID = studentID;
		this.examsList = examsList;	
	}
	
	/*
	 * Void Constructor
	 */
	public RequestRC() {
	}
	
	
	/*
	 * toString method
	 * 
	 */
	public String toString(){
		return universityID;
		
	}
	
	/*
	 * Getter and setter
	 */
	public int getRequestRCID() {
		return requestRCID;
	}
	public void setRequestRCID(int requestRCID) {
		this.requestRCID = requestRCID;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public RCState getState() {
		return state;
	}
	public void setState(RCState state) {
		this.state = state;
	}
	public String getUniversityID() {
		return universityID;
	}
	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public ArrayList<Exam> getExamsList() {
		return examsList;
	}
	public void setExamsList(ArrayList<Exam> examsList) {
		this.examsList = examsList;
	}
	









}