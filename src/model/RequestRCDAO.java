package model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import controller.DbConnection;
import controller.Utils;

public class RequestRCDAO implements RequestRCDAOInterface {

  Statement stmt = null;
  String sql = "";
  String error;


  /**
   * Saves the request into the database.
   * 
   * @param request the <tt>RequestRC</tt> object that will be saved.
   * @return
   *         <ul>
   *         <li>a positive value if the insertion succeeded
   *         <li>0 if nothing was added to the database
   *         <li>-1 if the insertion succeeded, but the database didn't return any information about
   *         the number of inserted rows
   *         <li>-2 if the attributes of the passed argument aren't fully specified
   *         </ul>
   * @author Gianluca Rossi
   */
  @Override
  public int insertRequestRC(RequestRC request) {
    if (request.getUniversityID().equals("") || request.getStudentID().equals("")) { 
      // Checks if attributes are set                                                        
      System.out.println(
          "Please set the University ID and the Student ID before trying to"
          + " add the RequestRC to the database.");
      return -2;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int result = 0;

    /*
     * Adds the 5 given parametric values in the REQUEST_RC table. The Request ID is automatically
     * generated from the database, as it is defined as an auto increment value, so it's not
     * required to create a new one here.
     */
    String insertSQL =
        "INSERT INTO REQUEST_RC " + " (DATE_REQUEST, STATE, FK_UNIVERSITY, FK_USER, FK_EMAIL_UC) "
            + " VALUES (?, ?, ?, ?, ?)";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(insertSQL);
      // Getting today's date
      Calendar calendar = Calendar.getInstance();
      java.util.Date currentDate = calendar.getTime();
      java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
      // Setting parameters
      preparedStatement.setDate(1, sqlDate);
      preparedStatement.setInt(2, RCState.needsUCValidation.ordinal());
      preparedStatement.setString(3, request.getUniversityID());
      preparedStatement.setString(4, request.getStudentID());
      preparedStatement.setString(5, "EMAILUC@gmail.it");
      // Executing the insertion
      result = preparedStatement.executeUpdate();
      connection.commit();
      System.out.println("insertRequestRC(result=" + result + ": " + request.toString());
      // Logging the operation                                                                      
    } catch (SQLException e) {
      System.out.println("insertRequestRC: error while executing the query\n" + e);
      new RuntimeException("Couldn't insert the RequestRC " + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return result;
  }

  /*
   * @Override public int updateRequestRC(RequestRC request) { if(request==null) {
   * System.out.println("updateRequestRC: invalid argument passed"); return -1;
   * 
   * } else if(doRetrieveRequestRCByStudentID(request.getStudentID())==null){
   * System.out.println("updateRequestRC: request doesn't exists in DB"); return -2; }else {
   * 
   * if(updateReportID(request.getReportID(),request) == 0 &&
   * updateState(request.getState(),request.getRequestRCID()) == 0) return 0; else {
   * System.out.println("updateRequestRC: error in update methods "); return -3; } }
   * 
   * }
   */


  /*
   * @Override public int updateReportID(int reportID, RequestRC request) { if(reportID<0) {
   * System.out.println("updateReport: invalid argument passed"); return -1;
   * 
   * } else if(doRetrieveRequestRCByStudentID(request.getStudentID())==null){
   * System.out.println("updateReport: request doesn't exists in DB"); return -2; }else {
   * 
   * Connection connection = null; PreparedStatement preparedStatement = null;
   * 
   * // Selects the exams that match the 2 given parametric values String updateSQL =
   * "UPDATE REQUEST_RC SET FK_REPORT = ?" + " WHERE (FK_USER = ?); "; try {
   * 
   * connection = DbConnection.getInstance().getConn(); preparedStatement =
   * connection.prepareStatement(updateSQL);
   * 
   * // Setting parameters preparedStatement.setInt(1,request.getReportID());
   * preparedStatement.setString(2,request.getStudentID()); preparedStatement.executeQuery();
   * connection.commit(); connection.close(); return 0;
   * 
   * 
   * } catch(SQLException e) {
   * System.out.println("updateReportID: error while executing the query\n" + e); new
   * RuntimeException("Couldn't find the request in the database " + e); } finally { // Statement
   * release if(preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e)
   * { e.printStackTrace(); } } return 0; }
   * 
   * }
   */


  /**
   * Saves the report in request into the database.
   * 
   * @param requestID is the id of the request
   * @param reportID is te id of te report
   * @return
   *         <ul>
   *         <li>a positive value if the insertion succeeded
   *         <li>0 if nothing was added to the database
   *         </ul>
   * @author Gerardo Damiano
   */
  @Override
  public int insertReportID(int reportID, int requestID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    System.out.println("id report : " + reportID + "\n" + " id richiesta :" + requestID);
    String updateSQL = "UPDATE REQUEST_RC SET FK_REPORT = ?" + " WHERE (ID_REQUEST = ?); ";
    try {

      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(updateSQL);

      // Setting parameters
      preparedStatement.setInt(1, reportID);
      preparedStatement.setInt(2, requestID);
      preparedStatement.executeUpdate();
      connection.commit();
      return 1;
    } catch (SQLException e) {
      new RuntimeException("Couldn't find the request in the database " + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return 0;
  }


  /**
   * Retrieves the <tt>status</tt> of the update given ID and current state.
   * 
   * @param requestRCID the <tt>RequestRC</tt> ID number that the <tt>RequestRC</tt> object must
   *        match
   * @param RCstate the <tt>RCState</tt> number that the <tt>RCState</tt> of request have
   * @return the <tt>status</tt> 1 if the update was successful, 0 else
   * @author Gerardo Damiano
   */
  @Override
  public int updateState(RCState state, int requestRCID) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int ris = 0;
    String selectSQL = "UPDATE request_rc SET state = ? WHERE id_request = ?";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(selectSQL);
      // Setting the parameter
      if (state.equals(state.valueOf("needsUCValidation")))
        preparedStatement.setInt(1, 0);
      else if (state.equals(state.valueOf("isBeingDiscussed")))
        preparedStatement.setInt(1, 1);
      else if (state.equals(state.valueOf("approved")))
        preparedStatement.setInt(1, 2);
      else if (state.equals(state.valueOf("refused")))
        preparedStatement.setInt(1, 3);
      preparedStatement.setInt(2, requestRCID);
      // Executing the selection
      ris = preparedStatement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      System.out.println("updateState: error while executing the query\n" + e);
      new RuntimeException("Couldn't update the RequestRC " + requestRCID + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return 0;
  }


  /**
   * Retrieves the <tt>RequestRC</tt> object that matches the given ID.
   * 
   * @param requestRCID the <tt>RequestRC</tt> ID number that the <tt>RequestRC</tt> object must
   *        match
   * @return the <tt>RequestRC</tt> object if found, null otherwise
   * @author Gianluca Rossi
   */
  @Override
  public RequestRC doRetrieveRequestRCByRequestID(int requestRCID) {
    if (requestRCID < 0) { // Checks if parameter is a valid ID
      System.out.println("doRetrieveRequestRCByRequestID: Please enter a valid RequestRC ID.");
      return null;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    RequestRC requestRC = null;

    // Selects the RCRequest tuples that match the specified ID
    String selectSQL = "SELECT * FROM REQUEST_RC " + " WHERE ID_REQUEST = ?";
    try {
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(selectSQL);
      // Setting the parameter
      preparedStatement.setInt(1, requestRCID);
      // Executing the selection
      ResultSet resSet = preparedStatement.executeQuery();

      // If a RCRequest is found construct it
      if (resSet.next()) {
        requestRC = new RequestRC();
        requestRC.setRequestRCID(requestRCID);
        requestRC.setSubmissionDate(resSet.getDate("DATE_REQUEST"));
        RCState state = RCState.fromInteger(resSet.getInt("STATE"));
        requestRC.setState(state);
        requestRC.setUniversityID(resSet.getString("FK_UNIVERSITY"));
        requestRC.setStudentID(resSet.getString("FK_USER"));
        requestRC.setReportID(resSet.getInt("FK_REPORT"));
      }
    } catch (SQLException e) {
      System.out.println("doRetrieveRequestRCByRequestID: error while executing the query\n" + e);
      new RuntimeException("Couldn't retrieve the RequestRC " + requestRCID + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return requestRC;
  }

  @Override
  public RequestRC doRetrieveRequestRCByStudentID(String studentID) {
    RequestRC r = null;
    try {
      Connection connection = DbConnection.getInstance().getConn();
      PreparedStatement ps =
          connection.prepareStatement(" SELECT  * FROM request_rc " + "WHERE fk_user = ?");
      ps.setString(1, studentID);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        r = new RequestRC();
        r.setRequestRCID(rs.getInt(1));
        r.setSubmissionDate(rs.getDate(2));
        r.setState(RCState.fromInteger(rs.getInt(3)));
        r.setUniversityID(rs.getString(4));
        r.setStudentID(rs.getString(5));
        r.setReportID(rs.getInt(6));
        System.out.println(r);
        return r;
      }
    } catch (SQLException e) {
      System.out.println("doRetrieveRequestRCByStudentID: error while executing the query\n" + e);
      throw new RuntimeException(e);
    }
    return r;
  }


  /**
   * Deletes the request that matches the specified ID, and the related records of the
   * <tt>ContainsRelation</tt>, <tt>FilePDF</tt>, <tt>Exam</tt> (if not used in another request),
   * <tt>Report</tt>, <tt>ValidatedExams</tt> objects from the database.
   * 
   * @param requestRCID the ID of the <tt>RequestRC</tt> object that will be deleted.
   * @return
   *         <ul>
   *         <li>a positive value if the deletion succeeded
   *         <li>0 if the request wasn't deleted
   *         <li>-1 if the deletion succeeded, but the database didn't return any information about
   *         the number of deleted rows
   *         <li>-2 if the passed parameter is not a valid <tt>RequestRC</tt> ID
   *         </ul>
   * @author Gianluca Rossi
   */
  @Override
  public int deleteRequestRCByRequestID(int requestRCID) {
    if (requestRCID < 0) { // Checks if parameter is a valid ID
      System.out.println("deleteRequestRCByRequestID: Please enter a valid request ID.");
      return -2;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int result = 0;

    /*
     * Deletes the RequestRC in the REQUEST_RC table whose ID matches the given parametric value.
     * Deletes the relative ContainsRelation, Report and ValidatedExam records as well.
     */
    String deleteSQL = "DELETE REQUEST_RC FROM REQUEST_RC WHERE ID_REQUEST = ?";
    try {
      // Deletes all the related exams
      ExamDAO eDAO = new ExamDAO();
      eDAO.deleteAllRCRequestExamsByRequestID(requestRCID);
      // Deletes the report, if it was created
      RequestRC req = doRetrieveRequestRCByRequestID(requestRCID);
      if (req != null) {
        int reportID = req.getReportID();
        if (reportID < 0) {
          ReportDAO rDAO = new ReportDAO();
          rDAO.deleteReport(req.getReportID());
        }
      } else {
        System.out.println("RequestRC not found, can't delete the report");
      }

      // Deletes the pdf files and the student folder
      FilePDFDAO pdfDAO = new FilePDFDAO();
      FilePDF file = pdfDAO.doRetrieveAllFilePDFByIDRequestRC(requestRCID).get(0); 
      // Gets the first FilePDF
      System.out.println(file);
      String studentFilesFolderPath =
          Utils.getProjectPath() + Utils.getParentDirectoryFromFilePath(file.getPDFLink()); 
   // Gets
      // the path to the folder that contains all the student files
      File studentDirectory = new File(studentFilesFolderPath);
      if (FileUtils.deleteQuietly(studentDirectory)) { // Deletes the directory and all its files
        System.out.println("Deleted the folder " + studentDirectory);
      } else {
        System.out.println("Couldn't delete the folder " + studentDirectory);
      }
      // Preparing the RCRequest deletion
      connection = DbConnection.getInstance().getConn();
      preparedStatement = connection.prepareStatement(deleteSQL);
      // Setting parameter
      preparedStatement.setInt(1, requestRCID);
      // Executing the deletion
      result = preparedStatement.executeUpdate();
      connection.commit();
      System.out.println("deleteRequestRC(result=" + result + ")"); // Logging the operation
    } catch (SQLException e) {
      System.out.println("deleteRequestRCByRequestID: error while executing the query\n" + e);
      new RuntimeException("Couldn't delete the RequestRC " + e);
    } finally {
      // Statement release
      if (preparedStatement != null)
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return result;
  }

  /**
   * Deletes the request that matches the specified student ID, and the related records of the
   * <tt>ContainsRelation</tt>, <tt>FilePDF</tt>, <tt>Exam</tt> (if not used in another request),
   * <tt>Report</tt>, <tt>ValidatedExams</tt> objects from the database.
   * 
   * @param studentMail the ID of the <tt>Student</tt> object that the <tt>RequestRC</tt> must
   *        match.
   * @return
   *         <ul>
   *         <li>a positive value if the deletion succeeded
   *         <li>0 if the request wasn't deleted
   *         <li>-1 if the deletion succeeded, but the database didn't return any information about
   *         the number of deleted rows
   *         <li>-2 if the passed parameter is not a valid <tt>Student</tt> ID
   *         </ul>
   * @author Gianluca Rossi
   */
  @Override
  public int deleteRequestRCByStudentID(String studentMail) {
    if (studentMail.equals("")) { // Checks if parameter is a valid ID
      System.out.println("deleteRequestRCByStudentID: Please enter a valid student ID.");
      return -2;
    }

    int result = 0;

    RequestRC reqRC = doRetrieveRequestRCByStudentID(studentMail);
    if (reqRC != null) {
      int requestRCID = reqRC.getRequestRCID();
      result = deleteRequestRCByRequestID(requestRCID);
    }
    return result;
  }

  @Override
  public ArrayList<RequestRC> doRetrieveAllRequestRCBystate(RCState state) {

    ArrayList<RequestRC> requests = new ArrayList<RequestRC>();
    try {
      Connection connection = DbConnection.getInstance().getConn();
      PreparedStatement ps =
          connection.prepareStatement(" SELECT  * FROM request_rc " + "WHERE state  = ?");
      if (state.equals(state.valueOf("needsUCValidation")))
        ps.setInt(1, 0);
      else if (state.equals(state.valueOf("isBeingDiscussed")))
        ps.setInt(1, 1);
      else if (state.equals(state.valueOf("approved")))
        ps.setInt(1, 2);
      else if (state.equals(state.valueOf("refused")))
        ps.setInt(1, 3);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        RequestRC r = new RequestRC();
        r.setRequestRCID(rs.getInt(1));
        r.setSubmissionDate(rs.getDate(2));
        r.setState(RCState.fromInteger(rs.getInt(3)));
        r.setUniversityID(rs.getString(4));
        r.setStudentID(rs.getString(5));
        r.setReportID(rs.getInt(6));
        requests.add(r);
      }

    } catch (SQLException e) {
      System.out.println("doRetrieveAllRequestRCBystate: error while executing the query\n" + e);
      throw new RuntimeException(e);
    }

    return requests;
  }

}
