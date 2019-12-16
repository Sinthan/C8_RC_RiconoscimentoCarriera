package model;

/**
 * <tt>ValidatedExam</tt>
 * contains informations related to an exam that has been input into a report.
 * <p>
 * This state information includes:
 * <ul>
 * <li>The validated exam ID
 * <li>The report ID
 * <li>The exam external name
 * <li>The validated CFU number
 * <li>The validation procedure note
 * </ul>
 *
 * @author	Gianluca Rossi
 * @see		Report
 */

public class ValidatedExam {

	private int vExamID;
	private int reportID;
	private String examName;
	private int validatedCFU;
	private	String validationProcedure;
	
	/**	
	 * Constructs a <tt>ValidatedExam</tt> object.
	 *
	 * @param reportID				the ID of the report that contains this exam
	 * @param examName				the exam name used in the external university
	 * @param validatedCFU			the value of CFU validated for this exam
	 * @param validationProcedure	the note containing the validation procedure
	 */
	public ValidatedExam(int reportID, String examName, int validatedCFU, String validationProcedure) {
		this.reportID = reportID;
		this.examName = examName;
		this.validatedCFU = validatedCFU;
		this.validationProcedure = validationProcedure;
	}
	
	/**
     * Constructs an empty <tt>ValidatedExam</tt> object.
     */
	public ValidatedExam() {
		
	}
	
	/**
	 * Returns a string representation of the object, containing the ID of the <tt>ValidatedExam</tt>
	 * and of the related <tt>Report</tt> and the exam name used in the external university.
	 */
	@Override
	public String toString() {
		return "ValidatedExam [vExamID=" + vExamID + ", reportID=" + reportID + ", examName=" + examName + "]";
	}
	
	/* Getter methods */
	/**
	 * @return	the ID of this validated exam
	 */
	public int getVExamID() {
		return vExamID;
	}
	
	public int getReportID() {
		return reportID;
	}
	
	/**
	 * @return	the exam name used in the external university
	 */
	public String getExamName() {
		return examName;
	}
	
	/**
	 * @return	the value of CFU validated for this exam
	 */
	public int getValidatedCFU() {
		return validatedCFU;
	}
	
	/**
	 * @return	the note containing the validation procedure
	 */
	public String getValidationProcedure() {
		return validationProcedure;
	}
	
	/* Setter methods */
	/**
	 * Sets the ID of this validated exam.
	 */
	public void setVExamID(int ID) {
		vExamID = ID;
	}

	public void setReportID(int ID) {
		reportID = ID;
	}
	
	/**
	 * Sets the exam name used in the external university.
	 */
	public void setExamName(String name) {
		examName = name;
	}
	
	/**
	 * Sets the value of CFU validated for this exam.
	 */
	public void setValidatedCFU(int value) {
		validatedCFU = value;
	}

	/**
	 * Sets the note containing the validation procedure.
	 */
	public void setValidationProcedure(String note) {
		validationProcedure = note;
	}
}
