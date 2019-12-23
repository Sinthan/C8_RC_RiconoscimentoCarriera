<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page import="java.util.*,model.Request"%>

<%
	String pageName = "homeRCUC.jsp";
	String pageFolder = "GUIUC";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="showData()">
		<h1>PAGINA UC</h1>
		<a href="UCManagement" class="btn btn-primary btn-lg btn-block"
										role="button" aria-pressed="true">Richiesta</a>
		
</body>
</html>
