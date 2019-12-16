package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import interfacce.UserInterface;
import model.Admin;
import model.AdminDAO;
import model.RequestRC;
import model.RequestRCDAO;
import model.Secretary;
import model.SecretaryDAO;
import model.Student;
import model.StudentDAO;
import model.UC;
import model.UCDAO;
/**
 * Servlet implementation class LogigLogoutManagement
 */
@WebServlet("/LoginManagement")
public class LoginManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Connection conn = new DbConnection().getInstance().getConn();
		Integer result = 0;
	    String error = "";
	    String content = "";
	    String redirect = "";
	  
	    String email = request.getParameter("email");
	    String password = new Utils().generatePwd(request.getParameter("password"));
	    
	    if(conn== null) {
	    	error = "Nessuna connessione al DB ";
	    }else{
	    	error = "Email o password errati ";
	    }
	        
	        try {
	        		
	        	/*
	        	 *  vengono lanciati tutti i metodi dao, 
	        	 *  il ritorno sarà unico quindi ci sarà un solo oggetto diverso da null.
	        	 */
	        	
	        			StudentDAO sDao = new StudentDAO();
	        			Student student = sDao.doRetrieveStudent(email, password);	        			
	
	        			SecretaryDAO secretaryDao = new SecretaryDAO();
		        		Secretary secretary = secretaryDao.doRetrieveSecretary(email, password);
		        		
		        		AdminDAO adminDao = new AdminDAO();
		        		Admin admin = adminDao.doRetrieveAdmin(email, password);
		        		
		        		UCDAO ucDao = new UCDAO();
		        		UC uc = ucDao.doRetrieveUc(email, password);
		        		
		        		RequestRCDAO rDao = new RequestRCDAO();
		        		
	        			
		        		/*
		        		 * il seguente controllo discrimina in base al ritorno dei singoli metodi delle classi DAO 
		        		 * se l'oggetto è diverso da null si procede al reindirizzamento
		        		 */
	        	
	        			if (student != null) { // Profilo Student
	        				/*
	        				 * il seguente if controlla il tipo di studente tramite l'estenzione della mail 
	        				 * così da discriminare tra studenti interni ed esterni e reindirizzare alla pagina dedicata
	        				 */
	        				if((student.getEmail().substring(student.getEmail().indexOf("@"))).equalsIgnoreCase("@studenti.unisa.it") ) {
	        					redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";	        		
	        				}else {
	        					RequestRC rRC = rDao.doRetrieveRequestRCByStudentID(student.getEmail());
	        					if( rRC == null ) {
	        						redirect = request.getContextPath() + "/GUIStudentRC/createRCRequest1.jsp";
	        					}else {
	        						redirect = request.getContextPath() + "/GUIStudentRC/viewRCRequestStatus.jsp";
	        					}
	        				}
	        				request.getSession().setAttribute("user", student);
	        			}else if(secretary!=null){// Profilo Secretary
	        				redirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
	        				request.getSession().setAttribute("user", secretary);
	        			}else if(admin != null){// Profilo Admin
	        				redirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
	        				request.getSession().setAttribute("user", admin);
	        			} else if(uc != null){// Profilo Admin
	        				redirect = request.getContextPath() + "/_areaUC/viewRequest.jsp";
	        				request.getSession().setAttribute("user", uc);
	        			}else {
	        				throw new NumberFormatException("utente non valido");
	        			}
	        				
	        		
	        				result = 1;
	        	
	        } catch (Exception e) {
	        	System.out.println(error);
	        }
	       
	        JSONObject res = new JSONObject();
	        res.put("result", result);
	        res.put("error", error);
	        res.put("content", content);
	        res.put("redirect", redirect);
	        PrintWriter out = response.getWriter();
	        out.println(res);
	        response.setContentType("json");
	    }
	        
	}
	    