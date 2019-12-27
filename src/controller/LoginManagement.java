package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
	        		
	        	Secretary secretary = null;
	        	Admin admin = null;
	        	UC uc = null;
	        	
	        	
	        			StudentDAO sDao = new StudentDAO();
	        			Student student = sDao.doRetrieveStudent(email, password);	        			
	
	        			RequestRCDAO rDao = new RequestRCDAO();

	        			if(student==null) {
	        				SecretaryDAO secretaryDAO = new SecretaryDAO();
	        				secretary = secretaryDAO.doRetrieveSecretary(email, password);
	        			}
	        			
	        			if(student == null && secretary == null) {
	        				AdminDAO adminDAO = new AdminDAO();
	        				admin = adminDAO.doRetrieveAdmin(email, password);
	        			}
	        			
	        			if(student == null && secretary == null && admin == null) {
	        				UCDAO ucDAO = new UCDAO();
	        				uc = ucDAO.doRetrieveUc(email, password);
	        			}

	        			
		        		/*
		        		 * il seguente controllo discrimina in base al ritorno dei singoli metodi delle classi DAO 
		        		 * se l'oggetto � diverso da null si procede al reindirizzamento
		        		 */
	        	
	        			if (student != null) { // Profilo Student
	        				/*
	        				 * il seguente if controlla il tipo di studente tramite l'estenzione della mail 
	        				 * cos� da discriminare tra studenti interni ed esterni e reindirizzare alla pagina dedicata
	        				 */
	        				if((student.getEmail().substring(student.getEmail().indexOf("@"))).equalsIgnoreCase("@studenti.unisa.it") ) {
	        					redirect = request.getContextPath()+"/InsideStudentRedirect";	  
	        					System.out.println(redirect);
	        				}else {
	        					RequestRC rRC = rDao.doRetrieveRequestRCByStudentID(student.getEmail());
	        					if( rRC == null ) {
	        						request.getSession().setAttribute("flag",1);
	        						request.getSession().setAttribute("user", student);
	        						redirect = request.getContextPath() + "/StudentManagement";
	        					}else {
	        						request.getSession().setAttribute("flag",0);
	        						redirect = request.getContextPath() + "/StudentManagement";
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
	        				redirect = request.getContextPath() + "/UCRequestRedirector";
	        				request.getSession().setAttribute("user", uc);
	        			}else {
	        				throw new NumberFormatException("utente non valido");
	        			}
	        				
	        		
	        				result = 1;
	        				
	        			    if(student != null || uc != null || admin != null || secretary != null) {
	        		        	
	        		        	JSONObject res = new JSONObject();
	        		        	res.put("result", result);
	        		        	res.put("error", error);
	        		        	res.put("content", content);
	        		        	res.put("redirect", redirect);
	        		        	PrintWriter out = response.getWriter();
	        		        	out.println(res);
	        		        	response.setContentType("json");		        
	        		        }
	        	
	        } catch (Exception e) {
	        	System.out.println(error);
	        }      
	    
	    }
	        
	        
	}
	    
