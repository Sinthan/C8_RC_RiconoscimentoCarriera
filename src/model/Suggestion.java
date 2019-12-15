package model;
import java.util.Date;


public class Suggestion {
	
	private String univeristyName;
	private String examName;
	private String externalStudentCFU;
	private String validationMode;
	private Date validationaDate;
	
	/**
	   * Contructor.
	   * 
	   * @param universityName is the name of the university to which the exam belongs 
	   * @param examName  is the name of the exam for which a suggestion is needed
	   * @param externalStudentCFU represents CFU validated in the suggestion
	   * @param validationMode represents the suggested validation mode  
	   * @param validationDate refers to the date of the first validation of the exam
	   */
	public Suggestion(String univeristyName,String examName, String externalStudentCFU, String validationMode, Date validationaDate) {
		
		
		
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

	public String getUniveristyName() {
		return univeristyName;
	}

	public void setUniveristyName(String univeristyName) {
		this.univeristyName = univeristyName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExternalStudentCFU() {
		return externalStudentCFU;
	}

	public void setExternalStudentCFU(String externalStudentCFU) {
		this.externalStudentCFU = externalStudentCFU;
	}

	public String getValidationMode() {
		return validationMode;
	}

	public void setValidationMode(String validationMode) {
		this.validationMode = validationMode;
	}

	public Date getValidationaDate() {
		return validationaDate;
	}

	public void setValidationaDate(Date validationaDate) {
		this.validationaDate = validationaDate;
	}

	@Override
	public String toString() {
		return "Suggestion [univeristyName=" + univeristyName + ", examName=" + examName + ", ExternalStudentCFU="
				+ externalStudentCFU + ", validationMode=" + validationMode + ", validationaDate=" + validationaDate
				+ "]";
	}
	
	

}
