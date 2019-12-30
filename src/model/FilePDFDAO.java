package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	 * @return		<ul><li>a positive count if the insertion succeeded
	 *					<li>0 if nothing was added to the database
	 *					<li>-1 if the insertion succeeded, but the database didn't return any information about the number of inserted rows
	 *					<li>-2 if the attributes of the passed argument aren't fully specified</ul>
	 * @see			FilePDF
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
	 * Retrieves all the <tt>FilePDF</tt> objects related to a specific <tt>RequestRC</tt>
	 * (identified through an ID).
	 * 
	 * @param	requestRCID		the <tt>RequestRC</tt> ID number that the <tt>RequestRC</tt> object must match
	 * @return					an <tt>ArrayList</tt> containing the <tt>FilePDF</tt> objects
	 * 							that match the given <tt>RequestRC</tt> ID
	 * @see		FilePDF
	 * @author 	Gianluca Rossi
	 */
	@Override
	public ArrayList<FilePDF> doRetrieveAllFilePDFByIDRequestRC(int requestRCID) {

		if (requestRCID < 0) { // Checks if parameter is a valid ID
			System.out.println("doRetrieveAllFilePDFByIDRequestRC: Please enter a valid request ID.");
			return null;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<FilePDF> requestRCPDFFiles = new ArrayList<FilePDF>();
		FilePDF file = null;

		// Selects the IDs and the link of the FilePDF records that are related to the specified RequestRC ID
		String selectSQL = "SELECT  F.ID_PDF, F.LINK_PDF " + 
				"FROM Request_RC R " + 
				"	INNER JOIN FILE_PDF F\n" + 
				"		ON F.FK_ID_REQUEST_RC = R.ID_REQUEST\n" + 
				"		WHERE R.ID_REQUEST = ?";
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(selectSQL);			
			// Setting the parameter
			preparedStatement.setInt(1, requestRCID);
			// Executing the selection
			ResultSet resSet = preparedStatement.executeQuery();

			// For every related pdf file found, add it to the ArrayList
			while (resSet.next()) {
				file = new FilePDF();
				file.setRequestRCID(requestRCID);
				file.setPDFID(resSet.getInt("ID_PDF"));
				file.setPDFLink(resSet.getString("LINK_PDF"));
				requestRCPDFFiles.add(file);
			}
		} catch(SQLException e) {
			new RuntimeException("Couldn't retrieve the RequestRC pdf files" + e);
		} finally {
			// Statement release
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return requestRCPDFFiles;

	}


}
