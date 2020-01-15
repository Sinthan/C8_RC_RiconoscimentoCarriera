package model;

import java.util.ArrayList;

/**
 * Report. Maintains Report data for a previous career recognition RequestRC.
 */

public class Report {

  /**
   * Variables.
   * 
   * @param reportID is the id of the Report.
   * @param note notes contains the annotations that the PCD has inserted regarding the Report.
   * @param validatedExamsList is an arrayList that contains validated Report exams.
   */
  private int reportID;
  private String note;
  private ArrayList<ValidatedExam> validatedExamsList = new ArrayList<ValidatedExam>();

  /**
   * Empty Constructor.
   * 
   */
  public Report() {
    reportID = -1;
    note = "";
  }

  /**
   * Constructor.
   * 
   * @param reportID
   * @param not
   * @param validatedExamsList
   */
  public Report(int reportID, String note, ArrayList<ValidatedExam> validatedExamsList) {
    super();
    this.reportID = reportID;
    this.note = note;
    this.validatedExamsList = validatedExamsList;
  }

  /**
   * Get the id of the Report.
   * 
   * @return the report id
   */
  public int getReportID() {
    return reportID;
  }

  /**
   * Get the note of the Report.
   * 
   * @return note notes contains the annotations that the PCD has inserted regarding the Report.
   */
  public String getNote() {
    return note;
  }

  /**
   * Get the validatedExamsList of the Report.
   * 
   * @return validatedExamsList is an arrayList that contains validated Report exams.
   */
  public ArrayList<ValidatedExam> getValidatedExamsList() {
    return validatedExamsList;
  }

  /**
   * Set the id of the Report.
   * 
   * @param reportID is the id of the Report.
   * @return void.
   */
  public void setReportID(int reportID) {
    this.reportID = reportID;
  }

  /**
   * Set the note of the Report.
   * 
   * @param note notes contains the annotations that the PCD has inserted regarding the Report.
   * @return void.
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Set the validatedExamsList of the Report.
   * 
   * @param validatedExamsList is an arrayList that contains validated Report exams.
   * @return void.
   */
  public void setValidatedExamsList(ArrayList<ValidatedExam> validatedExamsList) {
    this.validatedExamsList = validatedExamsList;
  }

  /**
   * Get the information contained within the report.
   * 
   * @return a String with information contained within the report.
   */
  @Override
  public String toString() {
    return "Report: reportID = " + reportID + ", note = " + note + ", validatedExamsList = "
        + validatedExamsList + ".";
  }
}
