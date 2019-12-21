package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DbConnection;

public class ContainsRelationDAO implements ContainsRelationDAOInterface {

	@Override
	public int insertContainsRelation(ContainsRelation conRel) {
		// TODO Auto-generated method stub

		Connection connection = null;
		try {
			System.out.println(conRel);
			connection = DbConnection.getInstance().getConn();
			PreparedStatement ps2 = connection.prepareStatement("INSERT INTO CONTAINS "
	            			+ " (FK_REQUEST_RC, FK_EXAM) "
	            			+ " VALUES " + " (?, ?) ");
	        ps2.setInt(1, conRel.getRequestRCID());
	        ps2.setInt(2, conRel.getExamID());
	        int result = ps2.executeUpdate();	
			connection.commit();
	        return 1;
	  	} catch (SQLException e) {
	  		throw new RuntimeException("Impossibile inserire la relazione Contains"+ e);
	  	}
	}
}
