package model;

/**
 * <tt>FilePDF</tt> contains informations about a PDF file related to a <tt>RequestRC</tt>.
 * <p>
 * This state information includes:
 * <ul>
 * <li>The file ID
 * <li>The URL of the file
 * <li>The request ID
 * </ul>
 *
 * @author Gianluca Rossi
 * @see RequestRC
 */

public class FilePDF {

  /**
   * The ID number of the file.
   */
  private int PDFID;

  /**
   * The URL of the file.
   */
  private String PDFLink;

  /**
   * The ID of the career recognition request to which this file is attached.
   */
  private int requestRCID;

  /**
   * Constructs a <tt>FilePDF</tt> object.
   *
   * @param PDFLink the URL of the file
   * @param requestRCID the ID of the career recognition request to which this file is attached
   */
  public FilePDF(String PDFLink, int requestRCID) {
    this.PDFLink = PDFLink;
    this.requestRCID = requestRCID;
  }

  /**
   * Constructs an empty <tt>FilePDF</tt> object.
   * Sets the attributes to an invalid state.
   */
  public FilePDF() {
    PDFID = -1;
    PDFLink = null;
    requestRCID = -1;
  }

  /**
   * Returns a string representation of the object, containing the ID of the file and of the related
   * <tt>RequestRC</tt> and the URL of the file.
   */
  @Override
  public String toString() {
    return "FilePDF [PDFID=" + PDFID + ", PDFLink=" + PDFLink + ", requestRCID=" + requestRCID
        + "]";
  }

  /* Getter methods */
  /**
   * @return the ID number of the file.
   */
  public int getPDFID() {
    return PDFID;
  }

  /**
   * @return the URL of the file.
   */
  public String getPDFLink() {
    return PDFLink;
  }

  /**
   * @return the ID of the career recognition request to which this file is attached.
   */
  public int getRequestRCID() {
    return requestRCID;
  }

  /* Setter methods */
  /**
   * Sets the ID number of the file.
   */
  public void setPDFID(int ID) {
    PDFID = ID;
  }

  /**
   * Sets the URL of the file.
   */
  public void setPDFLink(String link) {
    PDFLink = link;
  }

  /**
   * Sets the ID of the career recognition request to which this file is attached.
   */
  public void setRequestRCID(int ID) {
    requestRCID = ID;
  }
}
