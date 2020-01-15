package model;

/**
 * Object Exam Represents an Exam
 *
 */

public class Exam {


  private int examID;
  private String name;
  private int CFU;
  private String programLink;


  /**
   * Empty Costructor.
   */
  public Exam() {
    
  }

  /**
   * Contructor.
   * 
   * @param examID represents the exam ID
   * @param name represents the name of the exam
   * @param CFU represents the number of credits of the exam
   * @param programLink represents the link to the exam program
   */

  public Exam(int examID, String name, int CFU, String programLink) {

    this.examID = examID;
    this.name = name;
    this.CFU = CFU;
    this.programLink = programLink;
  }

  /**
   * getter and setter.
   */

  public int getExamID() {
    return examID;
  }

  public String getName() {
    return name;
  }

  public int getCFU() {
    return CFU;
  }

  public String getProgramLink() {
    return programLink;
  }

  public void setExamID(int examID) {
    this.examID = examID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCFU(int cFU) {
    CFU = cFU;
  }

  public void setProgramLink(String programLink) {
    this.programLink = programLink;
  }

  @Override
  public String toString() {
    return "Exam [examID=" + examID + ", name=" + name + ", CFU=" + CFU + ", programLink="
        + programLink + "]";
  }

}
