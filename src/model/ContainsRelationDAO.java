package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DbConnection;

public class ContainsRelationDAO implements ContainsRelationDAOInterface {

	Statement stmt = null;
	String sql = "";
	String error;
	@SuppressWarnings("static-access")
	
	Connection conn = (Connection) new DbConnection().getInstance().getConn();
	
	@Override
	public int insertContainsRelation(ContainsRelation conRel) {
		// TODO Auto-generated method stub

		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConn();
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO CONTAINS "
	            			+ " (FK_REQUEST_RC, FK_EXAM) "
	            			+ " VALUES " + " (?, ?) ");
	        ps2.setInt(1, conRel.getRequestRCID());
	        ps2.setInt(2, conRel.getExamID());
	        ps2.executeUpdate();
			connection.commit();
	        return 1;
	  	} catch (SQLException e) {
	  		throw new RuntimeException("Impossibile inserire la relazione Contains"+ e);
	  	}
	}
}
