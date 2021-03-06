package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;
import model.Exam;
import model.RCState;
import model.ReportDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.SenderMail;
import model.Student;
import model.UC;
import model.ValidatedExam;
import model.ValidatedExamDAO;

/**
 * Servlet implementation class RequestRCManagement.
 */

@WebServlet("/RequestRCManagement")
public class RequestRCManagement extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public final RequestRCDAO rDAO = new RequestRCDAO();
  String projectPath = Utils.getProjectPath();
  String pdfSaveFolder = "/DocumentsRequestRC";
  String projectName = "/C8_RC_RiconoscimentoCarriera";
  SenderMail email = new SenderMail();

  /**
   * @see HttpServlet#HttpServlet().
   */
  public RequestRCManagement() {
    super();
   
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    RCState stateAcceptByUC = RCState.isBeingDiscussed;
    RCState stateRejectByUC = RCState.refused;
    Object user = request.getSession().getAttribute("user");
    int result;
    RequestDispatcher disp;
    RequestRC reqRC = (RequestRC) request.getSession().getAttribute("reqRC");

    // Se la richiesta deve essere trattata dall' UC
    if (user instanceof UC) {
      String requestRCstate = request.getParameter("RequestRCstate");
      RequestRCDAO reqDAO = new RequestRCDAO();
      // Se la richiesta viene accettata dall'UC
      if (requestRCstate.equalsIgnoreCase("true")) {
        System.out.println("ID RICHIESTA" + reqRC.getRequestRCID());
        // Aggiorno lo stato della richiesta
        result = reqDAO.updateState(stateAcceptByUC, reqRC.getRequestRCID());
        // Siccome la richiesta e' stata accettata creo ed allego un report contenente la lista
        // degli esami da validare
        ArrayList<Exam> exams = (ArrayList<Exam>) request.getSession().getAttribute("exams");
        // ReportDAO rDao = new ReportDAO();
        // ValidatedExam e = new ValidatedExam();
        // ValidatedExamDAO eDao = new ValidatedExamDAO();
        // result = rDao.createReport();
        // int reportID = rDao.doRetrieveLastReportID();
        // result = reqDAO.insertReportID(reportID, reqRC.getRequestRCID());
        // for(int i = 0; i < exams.size(); i++){
        // e.setExamName(exams.get(i).getName());
        // e.setReportID(reportID);
        // e.setValidationProcedure(null);
        // result = eDao.insertValidatedExam(e);
        // }
        disp = request.getRequestDispatcher("/UCManagement");
        disp.forward(request, response);
        // Se la richiesta viene rifiutata dall'UC
      } else if (requestRCstate.equalsIgnoreCase("false")) {   
        // Ottengo la motivazione del rifiuto della richiesta
        String messageBody = request.getParameter("popupText");
        result = reqDAO.updateState(stateRejectByUC, reqRC.getRequestRCID());
        Student student = (Student) request.getSession().getAttribute("userRC");
        String message = "Gentile " + student.getName() + " " + student.getSurname()
            + ", la sua richiesta di convalida della carriera pregressa"
            + " e' stata rifiutata per le seguenti ragioni: \n" + messageBody + "\n"
            + "Cordiali saluti.";
        email.sendMail("carrierapregressaunisa@gmail.com", student.getEmail(), "Carriera pregressa",
            message, null);
        response.sendRedirect("/EnglishValidation/UCManagement");
      }
      // Se la richiesta deve essere trattata dal PCD
    } else if (user instanceof Admin) {

      // Get the mail of professor
      int reqRCID = Integer.parseInt(request.getParameter("requestRCID"));
      String mailD = request.getParameter("recipient-name");
      // Control email format
      if (mailD != null) {
        String regex =
            "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+"
            + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mailD);
        if (!matcher.matches()) {
          request.setAttribute("errorVR",
              "Impossibile inviare l'email al docente. Email non vaida.");
          return;
        }
      } else {
        request.setAttribute("errorVR", "Campo email vuoto.");
        return;
      }

      // Get body text
      String messageBody = request.getParameter("message-text");
      // Control body format
      if (messageBody.length() < 1) {
        request.setAttribute("errorVR",
            "Impossibile inviare l'email al docente. Messaggio non inserito.");
        return;
      }
      // Send mail to professor
      email.sendMail("carrierapregressaunisa@gmail.com", mailD, "Carriera pregressa", messageBody,
          null);

      // Initialize variables to save mail sent to Admin for an student
      RequestRC requestRC = rDAO.doRetrieveRequestRCByRequestID(reqRCID);
      String mailStudent = requestRC.getStudentID();
      String examSelected = request.getParameter("exam-selected");

      // Control if folder DocumentsRequestRC is present in the project
      File dir = new File(projectPath + "/WebContent" + pdfSaveFolder);
      if (!dir.mkdir()) {
        dir.mkdir();
      }

      // Control if folder of Students document is present in DocumentsRequestRC
      dir = new File(projectPath + "/WebContent" + pdfSaveFolder + "/" + mailStudent);
      if (!dir.mkdir()) {
        dir.mkdir();
      }
      // Control if file of sent mail is present in the folder of the student
      File fileM = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + mailStudent
          + "/" + "mailRequest.txt");

      // Control if the mail for exam was sent
      if (fileM.exists()) {
        Scanner scanner = new Scanner(fileM);
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileM, true)));
        boolean found = false;
        while (scanner.hasNextLine()) {
          String lineFromFile = scanner.nextLine();
          if (lineFromFile.equals(examSelected)) {
            found = true;
            break;
          }
        }
        if (!found) {
          writer.write("\n" + examSelected);
        }
        scanner.close();
        writer.close();
      } else {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileM, true)));
        writer.println(examSelected);
        writer.close();
      }

      fileM = new File(projectPath + "/" + "WebContent" + pdfSaveFolder + "/" + mailStudent + "/"
          + "mailRequest.txt");

      // Create a new ArrayList of exam sent to Professor
      @SuppressWarnings("unchecked")
      ArrayList<Exam> examList = (ArrayList<Exam>) request.getSession().getAttribute("examList");
      ArrayList<String> mailsSent = new ArrayList<String>();
      if (fileM.exists()) {
        for (Exam e : examList) {
          Scanner scanner = new Scanner(fileM);
          while (scanner.hasNextLine()) {
            String lineFromFile = scanner.nextLine();
            if (lineFromFile.equals(e.getName())) {
              mailsSent.add(lineFromFile);
              break;
            } else if (!scanner.hasNextLine()) {
              mailsSent.add(null);
              break;
            }
          }
          scanner.close();
        }
      }
      request.setAttribute("mailsSent", mailsSent);
      return;
    }

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
