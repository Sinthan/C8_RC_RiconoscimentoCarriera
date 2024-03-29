<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@ page import = "model.RequestRC,controller.Utils, model.RequestRCDAO, model.State, model.Student, model.StudentDAO, java.util.ArrayList, java.text.SimpleDateFormat"
    %>
<%
	String pageName = "homeRCUC.jsp";
	String pageFolder = "GUIUC";
%>  
    
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

	<link href="<%= request.getContextPath() %>/css/RC/homeRCUC.css"
	rel="stylesheet">
	<jsp:include page="/partials/head.jsp" />
	<meta charset="ISO-8859-1">
<title>Home UC</title>
</head>
<body>
	<div class="page-wrapper">

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>
		
<div class="sidebar-page-container basePage homeRCUC">
			<br/><h2 align = center>Richieste da controllare</h2><br/>
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
							<table id="UCTableRC" class="display data-results table table-striped table-hover table-bordered">
									<thead> 									
										<tr align="center">
											<th class="text-center tableRequestRCtd" align="center" width="30%">Email</th>
											<th class="text-center tableRequestRCtd" align="center" width="30%">Nome</th>
											<th class="text-center tableRequestRCtd" align="center" width="30%">Data</th>
											<th class="text-center tableRequestRCtd" align="center" width="10%"></th>
										</tr>
											
										<%
										ArrayList<RequestRC> reqList =(ArrayList<RequestRC>) request.getAttribute("reqList");
										RequestRC temp = null;
										StudentDAO rDao = new StudentDAO();	
										
 										for (int i = 0; i < reqList.size(); i++){ 
											temp = reqList.get(i);
											Utils util = new Utils();
											String date =  util.getFormattedDate(temp.getSubmissionDate());
											Student s = rDao.doRetrieveStudentByEmail(temp.getStudentID());
											out.print("<tr>");
											out.print("<td class=text-center align=center>" + temp.getStudentID() + "</td>");
											out.print("<td class=text-center align=center>"+ s.getName() + " " + s.getSurname() + "</td>");
											out.print("<td class=text-center align=center>"+ date +"</td>");
											out.print("<td class=submitButton-centre align=center style= margin-right: 60px; width: 200px; position: relative; float:centre>"+
													"<form action=./UCRCRequestRedirector method=post> <input type = hidden name = idRequestRC value = "  + temp.getRequestRCID() +  "  /> " +
													"<input type= submit value =Controlla  class=\"btn btn-primary\"></input></form> </td>");
											out.print("</tr>");
										}
											
											%>
											</thead>
										<tbody id="bodySUCTable">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

</body>

</html>
