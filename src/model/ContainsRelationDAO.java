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
		try {
			PreparedStatement ps2 = conn.prepareStatement(" INSERT INTO CONTAINS "
	            			+ " (FK_REQUEST_RC, FK_EXAM) "
	            			+ " VALUES " + " (?, ?) ");
	        ps2.setInt(1, conRel.getRequestRCID());
	        ps2.setInt(2, conRel.getExamID());
	        ps2.executeUpdate();
	        return 1;
	  	} catch (SQLException e) {
	  		throw new RuntimeException("Impossibile effettuare la registrazione"+ e);
	  	}
	}
}
