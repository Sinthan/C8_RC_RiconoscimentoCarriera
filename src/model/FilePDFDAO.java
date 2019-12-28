/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DbConnection;

/**
 * FilePDFDAO
 * interacts with the database in order 
 * to manage files related to a previous career 
 * recognition request.
 */

public class FilePDFDAO implements FilePDFDAOInterface {
	
	Connection conn = (Connection) new DbConnection().getInstance().getConn();

	/**
	 * Saves the pdf file into the database.
	 * 
	 * @param file	the <tt>FilePDF</tt> object that will be saved
	 * @return		<ul><li>a positive count of the number of rows affected
	 *				<li>0 if no rows were affected
	 *				<li>-1 if the statement succeeded, but there is no update count information available</ul>
	 *				<li>-2 if the attributes of the passed argument aren't fully specified
	 * @author 		Gianluca Rossi
	 */
	@Override
	public int insertFilePDF(FilePDF file) {
		if (file.getPDFLink().equals("") || file.getRequestRCID() == -1) { // Checks if attributes are set
			System.out.println("Please set the PDF link and the RequestRC ID before trying to add the FilePDF to the database.");
			return -2;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0; // No rows affected by default

		/* Adds the 2 given parametric values in the FILE_PDF table.
		 * The file ID is automatically generated from the
		 * database, as it is defined as an auto increment value,
		 * so it's not required to create a new one here.
		 */
		String insertSQL = "INSERT INTO FILE_PDF "
				+ " (LINK_PDF, FK_ID_REQUEST_RC) "
				+ " VALUES (?, ?)";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(insertSQL);			
			// Setting parameters
			preparedStatement.setString(1, file.getPDFLink());
			preparedStatement.setInt(2, file.getRequestRCID());
			// Executing the insertion
			result = preparedStatement.executeUpdate();	
			connection.commit();
			System.out.println("insertFilePDF(result=" + result + ": " + file.toString());		// Logging the operation
		} catch(SQLException e) {
			new RuntimeException("Couldn't insert the FilePDF " + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	/**
	 * Get a set of PDF files given the request id.
	 * @param requestRCID is the id of the Request.
	 * @return returns an set of FilePDF related to the Request.
	 */
	@Override
	public ArrayList<FilePDF> doRetrieveAllFilePDFByIDRequestRC(int requestRCID) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					" SELECT  * FROM file_pdf "
				  + "WHERE fk_id_request_rc = ? ");
			ps.setInt(1, requestRCID);
			ResultSet rs = ps.executeQuery();
			ArrayList<FilePDF> listPdf = new ArrayList<>();
			while(rs.next()){
				FilePDF f = new FilePDF();
				f.setPDFID(rs.getInt(1));
				f.setPDFLink(rs.getString(2));
				f.setRequestRCID(rs.getInt(3));
				listPdf.add(f);
			}
			if(listPdf.isEmpty()){
				return null;
			}else {
				return listPdf;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
