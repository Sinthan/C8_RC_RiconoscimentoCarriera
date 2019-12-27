package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DbConnection;

/**
 * <tt>ContainsRelationDAO</tt>
 * Implementation of the ContainsRelationDAOInterface, manages and retrieves data related to
 * <tt>ContainsRelation</tt> objects from the MySQL database.
 * 
 * @author	Gianluca Rossi
 * @see		ContainsRelation
 */

public class ContainsRelationDAO implements ContainsRelationDAOInterface {

	/**
	 * Saves the <tt>ContainsRelation</tt> object into the database.
	 * 
	 * @param conRel	the <tt>ContainsRelation</tt> object that will be saved
	 * @return			<ul><li>a positive count of the number of rows affected
	 *					<li>0 if no rows were affected
	 *					<li>-1 if the statement succeeded, but there is no update count information available</ul>
	 *					<li>-2 if the attributes of the passed argument aren't fully specified
	 * @author 			Gianluca Rossi
	 */
	@Override
	public int insertContainsRelation(ContainsRelation conRel) {
		if (conRel.getExamID() == -1 || conRel.getRequestRCID() == -1) { // Checks if attributes are set
			System.out.println("Please set the Exam ID and the RequestRC ID before trying to add the ContainsRelation to the database.");
			return -2;
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		// Adds the 2 given parametric values in the CONTAINS table.
		String insertSQL = "INSERT INTO CONTAINS " +
				" (FK_REQUEST_RC, FK_EXAM) " +
				" VALUES (?, ?)";

		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement(insertSQL);
			// Setting parameters
			preparedStatement.setInt(1, conRel.getRequestRCID());
			preparedStatement.setInt(2, conRel.getExamID());
			// Executing the insertion
			result = preparedStatement.executeUpdate();	
			connection.commit();
			System.out.println("insertContainsRelation(result=" + result + ": " + conRel.toString());		// Logging the operation
		} catch(SQLException e) {
			new RuntimeException("Couldn't insert the ContainsRelation in the database" + e);
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
	 * Return a set of <tt>ContainsRelation</tt>.
	 * 
	 * @param conRel	the <tt>ContainsRelation</tt> object that will be saved
	 * @return			a set of Contains for an Exam
	 */
	@Override
	public ArrayList<ContainsRelation> doRetrieveAllContainsRelationByIDExam(int idExam) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.getInstance().getConn();
			preparedStatement = connection.prepareStatement("SELECT * FROM CONTAINS WHERE FK_EXAM = ?");
			preparedStatement.setInt(1, idExam);
			ArrayList<ContainsRelation> containsRelations = new ArrayList<>();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ContainsRelation cr = new ContainsRelation();
				cr.setRequestRCID(rs.getInt(1));
				cr.setExamID(rs.getInt(2));
				containsRelations.add(cr);
			}
			return containsRelations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
