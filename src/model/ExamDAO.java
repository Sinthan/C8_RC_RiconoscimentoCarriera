package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controller.DbConnection;

/**
 * ExamDAO is used for communication between Exam and DB
 */

public class ExamDAO implements ExamDAOInterface {

  Statement stmt = null;
  String sql = "";
  String error;

  private final ContainsRelationDAO crDAO = new ContainsRelationDAO();

  /**
   * Saves the exam into the database.
   * 
   * @param exam the <tt>Exam</tt> object that will be saved.
   * @return
   *         <ul>
   *         <li>a positive value if the exam that wants to be saved is already present, the value
   *         corresponds to the examID of the already present exam record
   *         <li>-1 if the insertion succeeded
   *         <li>-2 if nothing was added to the database
   *         <li>-3 if the insertion succeeded, but the database didn't return any information about
   *         the number of inserted rows
   *         <li>-4 if the attributes of the passed argument aren't fully specified
   *         </ul>
   * @author Gianluca Rossi
   * @author Lorenzo Maturo
   */
  @Override
  public int insertExam(Exam exam) {
    // Checks if attributes are set
    if (exam.getName().equals("") || exam.getCFU() == -1 || exam.getProgramLink().equals("")) { 
      System.out.println(
          "Please set the Exam's name, cfu and program link before trying "
          + "to add it to the database.");
      return -4;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int result = -2; // No rows affected by default

    /*
     * Adds the 3 given parametric values in the EXAM table. The exam ID is automatically generated
     * from the database, as it is defined as an auto increment value, so it's not required to
     * create a new one here.
     */
    String insertSQL = "INSERT INTO EXAM " + " (NAME, CFU, LINK_PROGRAM) " + " VALUES (?, ?, ?)";

    // Selects the exams that match the 3 given parametric values
    String selectSQL = "SELECT * FROM EXAM " + " WHERE NAME = ? AND CFU = ? AND LINK_PROGRAM = ?";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(selectSQL);
      // Setting parameters
      preparedStatement.setString(1, exam.getName());
      preparedStatement.setInt(2, exam.getCFU());
      preparedStatement.setString(3, exam.getProgramLink());
      ResultSet resSet = preparedStatement.executeQuery();

      if (resSet.first()) { // Exam found
        int examID = resSet.getInt(1);
        System.out.println("Exam \"" + exam.getName() + "\" already present.");
        return examID;
      } else { // Exam not already present in the database, proceeding with the insertion
        System.out
            .println("Exam \"" + exam.getName() + "\" not present, adding it to the database.");
        preparedStatement.close();
        preparedStatement = connection.prepareStatement(insertSQL);
        // Setting parameters
        preparedStatement.setString(1, exam.getName());
        preparedStatement.setInt(2, exam.getCFU());
        preparedStatement.setString(3, exam.getProgramLink());
        // Executing the insertion
        result = preparedStatement.executeUpdate();
        connection.commit();
      }
    } catch (SQLException e) {
      System.out.println("insertExam: error while executing the query\n" + e);
      new RuntimeException(
          "Couldn't insert the exam \"" + exam.getName() + "\" in the database " + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    if (result > 0) { // SQL Insert succeeded
      return -1;
    } else if (result == 0) { // SQL Insert failed
      return -2;
    } else if (result == -1) { // SQL Insert succeeded without log
      return -3;
    }
    // Logging the operation
    System.out.println("insertExam(result=" + result + ": " + exam.toString()); 
    return result;
  }

  /**
   * Retrieves all the <tt>Exam</tt> objects related to a specific <tt>RequestRC</tt> (identified
   * through an ID).
   * 
   * @param requestRCID the <tt>RequestRC</tt> ID number that the <tt>RequestRC</tt> object must
   *        match
   * @return an <tt>ArrayList</tt> containing the <tt>Exam</tt> objects that match the given
   *         <tt>RequestRC</tt> ID
   * @see Exam
   * @author Gianluca Rossi
   */
  @Override
  public ArrayList<Exam> doRetrieveAllExamsByRequestRCID(int requestRCID) {
    if (requestRCID < 0) { // Checks if parameter is a valid ID
      System.out.println("doRetrieveAllExamsByRequestRCID: Please enter a valid request ID.");
      return null;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Exam> requestRCExams = new ArrayList<Exam>();
    Exam exam = null;

    // Selects the IDs of the exams that are related to the specified RequestRC ID
    String selectSQL = "SELECT E.ID_EXAM, E.NAME, E.CFU, E.LINK_PROGRAM " + "FROM CONTAINS C "
        + "	INNER JOIN EXAM E " + "		ON E.ID_EXAM = C.FK_EXAM "
        + "		WHERE C.FK_REQUEST_RC = ?";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(selectSQL);
      // Setting the parameter
      preparedStatement.setInt(1, requestRCID);
      // Executing the selection
      ResultSet resSet = preparedStatement.executeQuery();

      // For every related exam found, construct and add it to the ArrayList
      while (resSet.next()) {
        exam = new Exam();
        exam.setExamID(resSet.getInt("ID_EXAM"));
        exam.setName(resSet.getString("NAME"));
        exam.setCFU(resSet.getInt("CFU"));
        exam.setProgramLink(resSet.getString("LINK_PROGRAM"));
        requestRCExams.add(exam);
      }
    } catch (SQLException e) {
      System.out.println("doRetrieveAllExamsByRequestRCID: error while executing the query\n" + e);
      new RuntimeException("Couldn't retrieve the RequestRC exams" + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return requestRCExams;
  }

  /**
   * Retrieves the <tt>Exam</tt> object that matches the given ID
   * 
   * @param examID the <tt>Exam</tt> ID number that the <tt>Exam</tt> object must match
   * @return the <tt>Exam</tt> object if found, null otherwise
   * @author Gianluca Rossi
   */
  @Override
  public Exam doRetrieveExamByID(int examID) {
    if (examID < 0) { // Checks if parameter is a valid ID
      System.out.println("doRetrieveExamByID: Please enter a valid Exam ID.");
      return null;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Exam exam = null;

    // Selects the exams that match the specified Exam ID
    String selectSQL = "SELECT * FROM EXAM " + " WHERE ID_EXAM = ?";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(selectSQL);
      // Setting the parameter
      preparedStatement.setInt(1, examID);
      // Executing the selection
      ResultSet resSet = preparedStatement.executeQuery();

      // If an exam is found construct the Exam object
      if (resSet.next()) {
        exam = new Exam();
        exam.setExamID(examID);
        exam.setName(resSet.getString("NAME"));
        exam.setCFU(resSet.getInt("CFU"));
        exam.setProgramLink(resSet.getString("LINK_PROGRAM"));
      }
    } catch (SQLException e) {
      System.out.println("doRetrieveExamByID: error while executing the query\n" + e);
      new RuntimeException("Couldn't retrieve the exam " + examID + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return exam;
  }

  /**
   * retrieve exam.
   * 
   * @return -1 if insert failed, 0 if ok
   */
  /*
   * public int doRetrieveExam(int requestRCID, int ExamID) { int flag = 0; return flag; }
   */

  /*
   * @return do return an exam with max ID.
   */
  public int doRetrieveMaxExamID() {
    try {
      Connection connection = DbConnection.getInstance().getConn();
      PreparedStatement ps = connection.prepareStatement(" SELECT MAX(ID_EXAM) FROM EXAM");
      ResultSet r = ps.executeQuery();
      if (r.next()) {
        int examID = r.getInt(1);
        return examID;
      }
    } catch (SQLException e) {
      System.out.println("doRetrieveMaxExamID: error while executing the query\n" + e);
      new RuntimeException(e);
    }
    return -1;
  }


  /**
   * delete all Exam by Request RC id.
   * 
   * @param idRequest is the id of the Request RC
   * @return 0 if delete failed, !=0 if ok
   */
  @Override
  public int deleteAllRCRequestExamsByRequestID(int idRequest) {
    // TODO Auto-generated method stub
    int biggestExamId = -1;
    ArrayList<Exam> exams = doRetrieveAllExamsByRequestRCID(idRequest);
    ExamDAO eDao = new ExamDAO();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    // recupero l'id pi� grande della lista esami
    for (Exam x : exams) {
      if (x.getExamID() > biggestExamId) {
        biggestExamId = x.getExamID();
      }
    }
    // recupero il max id corrente
    int currentId = eDao.doRetrieveMaxExamID();

    int result = 0;
    for (int i = 0; i < exams.size(); i++) {
      ArrayList<ContainsRelation> containsRelations =
          crDAO.doRetrieveAllContainsRelationByIDExam(exams.get(i).getExamID());
      if (containsRelations.size() == 1) {
        try {
          connection = DbConnection.getInstance().getConn();
          // elimino l'esame
          preparedStatement =
              connection.prepareStatement("DELETE EXAM FROM EXAM WHERE ID_EXAM = ?");
          preparedStatement.setInt(1, exams.get(i).getExamID());
          // Executing the deletion
          result = preparedStatement.executeUpdate();
          connection.commit();
        } catch (SQLException e) {
          System.out
              .println("deleteAllRCRequestExamsByRequestID: error while executing the query\n" + e);
          throw new RuntimeException(e);
        }
      }
    }
    // lo confronto con il max exam id
    // se � uguale resetto l'autoincrement
    if (biggestExamId == currentId) {
      connection = DbConnection.getInstance().getConn();
      try {
        preparedStatement = connection.prepareStatement("ALTER TABLE EXAM AUTO_INCREMENT = ?");
        preparedStatement.setInt(1, currentId);
        result = preparedStatement.executeUpdate();
        connection.commit();
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }


    return result;
  }



  /*
   * public void removeAddExamForTest() throws SQLException { String sql =
   * "Delete FROM englishvalidation.exam  where name = 'ingegneria del test' 
   * and cfu = 9 and link_program= '//link di riferimento//'"
   * ; Connection conn = (Connection) new DbConnection().getInstance().getConn();
   * 
   * try { PreparedStatement ps = conn.prepareStatement(sql); ps.execute(); }catch (SQLException e)
   * { System.out.println("removeAddExamForTest: error while executing the query\n" + e);
   * e.getMessage(); } }
   */

  /*
   * @Override public int deleteExamsByRequestID(int id) { // TODO Auto-generated method stub return
   * 0; }
   */

}
