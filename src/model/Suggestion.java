package model;
import java.util.Date;


public class Suggestion {
	
	private String universityName;
	private String examName;
	private int externalStudentCFU;
	private int validatedCFU;
	private String validationMode;
	private Date validationDate;
	
	/**
	   * Contructor.
	   * 
	   * @param universityName is the name of the university to which the exam belongs 
	   * @param examName  is the name of the exam for which a suggestion is needed
	   * @param externalStudentCFU represents CFU validated in the suggestion
	   * @param validationMode represents the suggested validation mode  
	   * @param validationDate refers to the date of the first validation of the exam
	   */
	public Suggestion(String universityName,String examName, int externalStudentCFU, int validatedCFU, String validationMode, Date validationDate) {
		
		this.universityName = universityName;
		this.examName = examName;
		this.externalStudentCFU= externalStudentCFU;
		this.validatedCFU = validatedCFU;
		this.validationDate = validationDate;
		this.validationMode = validationMode;
		
		
	}
	/**
	 * Empty Constructor.
	 */
	public Suggestion() {
		
	}
	
	/**
	 * 
	 * getters and setters
	 * 
	 */

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String univeristyName) {
		this.universityName = univeristyName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getExternalStudentCFU() {
		return externalStudentCFU;
	}

	public void setExternalStudentCFU(int externalStudentCFU) {
		this.externalStudentCFU = externalStudentCFU;
	}

	public String getValidationMode() {
		return validationMode;
	}

	public void setValidationMode(String validationMode) {
		this.validationMode = validationMode;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationaDate) {
		this.validationDate = validationaDate;
	}

	@Override
	public String toString() {
		return "Suggestion [univeristyName=" + universityName + ", examName=" + examName + ", ExternalStudentCFU="
				+ externalStudentCFU + ", validationMode=" + validationMode + ", validationDate=" + validationDate
				+ "]";
	}
	public int getValidatedCFU() {
		return validatedCFU;
	}
	public void setValidatedCFU(int validatedCFU) {
		this.validatedCFU = validatedCFU;
	}
	
	

}
