<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String pageName = "homeRCUC.jsp";
	String pageFolder = "GUIUC";
%>  
    
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

	<link href="<%= request.getContextPath() %>/css/RC/viewRCRequestUC.css"
	rel="stylesheet">
	<jsp:include page="/partials/head.jsp" />
	<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="page-wrapper">

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>
		
<div class="sidebar-page-container basePage viewRequestStudent">
			<br/><h2>Richieste da controllare</h2><br/>
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
							<table id="studentTableRC" class="display data-results table table-striped table-hover table-bordered">
									<thead>
									
										<tr align="center">
											<th class="text-center tableRequestRCtd" align="center">Nome</th>
											<th class="text-center tableRequestRCtd" align="center">Data invio</th>
											<th class="text-center tableRequestRCtd" align="center"></th>
										</tr>
										<tr>
										
										<td class="text-center" align="center">${email}</td>	
											<td class="text-center" align="center">${stName}</td>	
											<td class="text-center" align="center">${rRCDate}</td>
											
											<td class="submitButton-centre" style=" margin-right: 60px; width: 200px; position: relative; float:centre">												
												
											<a href="/EnglishValidation/UCRCRequestRedirector">ta da</a></td>
											
											
											</tr>
										
											</thead>
										<tbody id="bodyStudentTable">
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
