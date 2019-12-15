/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * FilePDFDAO.
 * interacts with the database in order 
 * to manage files related to a previous career 
 * recognition request.
 */
public class FilePDFDAO implements FilePDFDAOInterface {

	/**
	* Insert the pdf file into the database.
	* @param FilePDF.
	* @return returns the int value of the state generated by the query execution.
	*/
	@Override
	public int insertFilePDF(FilePDF file) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* Get a set of PDF files given the request id.
	* @param requestRCID is the id of the Request.
	* @return returns an set of FilePDF related to the Request.
	*/
	@Override
	public ArrayList<FilePDF> doRetrieveAllFilePDFByIDRequestRC(int requestRCID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Get a PDF file given the request and file id.
	* @param requestRCID is the id of the Request.
	* @param PDFID is the id of PDF.
	* @return returns the file that corresponds to the request id and the file id.
	*/
	@Override
	public FilePDF doRetrieveFilePDF(int requestRCID, int PDFID) {
		// TODO Auto-generated method stub
		return null;
	}

}
