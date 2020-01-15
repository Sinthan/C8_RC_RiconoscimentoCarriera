package model;

/**
 * <tt>ValidatedExam</tt> contains informations related to an exam that has been input into a
 * report.
 * This state information includes:
 * <ul>
 * <li>The validated exam ID
 * <li>The report ID
 * <li>The exam external name
 * <li>The validated CFU number
 * <li>The validation procedure note
 * </ul>
 *
 * @author Gianluca Rossi
 * @see Report
 */

public class ValidatedExam {

  /**
   * The ID of this <tt>ValidatedExam</tt>.
   */
  private int vExamID;
  /**
   * The ID of the report that contains this exam.
   */
  private int reportID;
  /**
   * The exam name used in the external university.
   */
  private String name;
  /**
   * The value of CFU validated for this exam.
   */
  private int validatedCFU;
  /**
   * The note containing the validation procedure.
   */
  private String validationProcedure;

  /**
   * Constructs a <tt>ValidatedExam</tt> object.
   *
   * @param reportID the ID of the report that contains this exam
   * @param examName the exam name used in the external university
   * @param validatedCFU the value of CFU validated for this exam
   * @param validationProcedure the note containing the validation procedure
   */

  public ValidatedExam(int reportID, String examName, int validatedCFU,
      String validationProcedure) {
    vExamID = -1; // -1 is used to identify an ID that wasn't initialized by the developer.
    this.reportID = reportID;
    this.name = examName;
    this.validatedCFU = validatedCFU;
    this.validationProcedure = validationProcedure;
  }

  /**
   * Constructs an empty <tt>ValidatedExam</tt> object.
   * Sets the attributes to an invalid state.
   */
  public ValidatedExam() {
    // -1 is used to identify an attribute that wasn't initialized by the developer.
    vExamID = -1;
    reportID = -1;
    name = "";
    validatedCFU = -1;
    validationProcedure = "";
  }

  /**
   * Returns a string representation of the object, containing the ID of the <tt>ValidatedExam</tt>
   * and of the related <tt>Report</tt> and the exam name used in the external university.
   */
  @Override
  public String toString() {
    return "ValidatedExam [vExamID=" + vExamID + ", reportID=" + reportID + ", examName=" + name
        + ", CFU=" + validatedCFU + ", mode=" + validationProcedure + "]";
  }

  /* Getter methods */
  /**
   * @return the ID of this validated exam.
   */
  public int getVExamID() {
    return vExamID;
  }

  /**
   * @return the ID of the report that contains this exam.
   */
  public int getReportID() {
    return reportID;
  }

  /**
   * @return the exam name used in the external university.
   */
  public String getExamName() {
    return name;
  }

  /**
   * @return the value of CFU validated for this exam.
   */
  public int getValidatedCFU() {
    return validatedCFU;
  }

  /**
   * @return the note containing the validation procedure.
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

  /**
   * Sets the ID of the report that contains this exam.
   */
  public void setReportID(int ID) {
    reportID = ID;
  }

  /**
   * Sets the exam name used in the external university.
   */
  public void setExamName(String name) {
    this.name = name;
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
