package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DbConnection;

public class ContainsRelationDAO implements ContainsRelationDAOInterface {

	/**
	 * Saves the <tt>ContainsRelation</tt> object into the database.
	 * 
	 * @param conRel	the <tt>ContainsRelation</tt> object that will be saved
	 * @return			<ul><li>a positive count of the number of rows affected (from INSERT, UPDATE, or DELETE)
	 *					<li>0 if no rows were affected
	 *					<li>-1 if the statement succeeded, but there is no update count information available</ul>
	 *					<li>-2 if the attributes of the passed argument aren't fully specified
	 * @author 			Gianluca Rossi
	 */
	@Override
	public int insertContainsRelation(ContainsRelation conRel) {
		if (conRel.getExamID() == -1 || conRel.getRequestRCID() == -1) // Checks if attributes are set
			return -2;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		// Adds the 2 parametric values in the CONTAINS table.
		String insertSQL = "INSERT INTO CONTAINS " +
				" (FK_REQUEST_RC, FK_EXAM) " +
				" VALUES (?, ?)";

		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(insertSQL);
			// Setting parameters
			preparedStatement.setInt(1, conRel.getRequestRCID());
			preparedStatement.setInt(2, conRel.getExamID());
			// Logging the operation
			System.out.println("insertContainsRelation: "+ conRel.toString());

			result = preparedStatement.executeUpdate();	
			connection.commit();
		} catch(SQLException e) {
			new RuntimeException(e);
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
}
