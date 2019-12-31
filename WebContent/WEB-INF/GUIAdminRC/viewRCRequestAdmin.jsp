<%-- 
  - Author(s): Gianluca Rossi, Lorenzo Maturo
  - Date: 30/12/2019
  - Description: This page allows the admin to view the details of a specific request of previous career recognition.
  --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*, model.Exam"%>

<%
	String pageName = "viewRCRequestAdmin.jsp";
	String pageFolder = "GUIAdminRC";
%>


<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/RC/viewRCRequestAdmin.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Controlla Richiesta</title>
<%
	HttpSession sess = request.getSession();
	sess.setAttribute("flag", 2);
	sess.setAttribute("requestRCID", 4);
%>
<script type='text/javascript'>

	// Get the container divs for the exam details
	var examNameDiv = document.getElementById("examName");
	var CFUDiv = document.getElementById("CFU");
	var buttonsDiv = document.getElementById("buttons");

	window.onload = function(){
		controlServlet();
		$('[data-toggle="tooltip"]').tooltip();
	};
	
	// If the servlet sent an error, show it
	function controlServlet() {
		var err = '<%=request.getAttribute("errorVR")%>';
		if (err != "null") {
			showAlert(1, err);
		}
	}
	
	function send(nameU, nameE, cfu) {
	    window.open('mailto: ?subject=' + 
	    		'[DINF-UNISA] Richiesta di Riconoscimento Carriera Pregressa' 
	    		+ '&body=' 
	    		+ 'Università di Provenienza : ' + nameU + '%0D%0A'
	    		+ 'Nome Esame : ' + nameE + '%0D%0A'
	    		+ 'Cfu Convalidati : ' + cfu + '%0D%0A'
	    );
	}
	
</script>
</head>
<body>
	<div class="page-wrapper">
		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>
		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
									<h1 class="text-left">
										Richiesta di <%=request.getAttribute("studentName")%> -
											<%=request.getAttribute("submissionDate")%>
									</h1>
								</div>

								<div id="requestSummary">
									<div class="col-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<h4 class="text-left description">
											<em>Informazioni sulla carriera pregressa fornite dallo
												studente</em>
										</h4>
										<div class="orange-frame">
											<h4 class="text-left field-title">
												<b>Università di provenienza</b>
											</h4>
											<h3 class="text-left"><%=request.getAttribute("universityName")%></h3>
											<div id="examsList" class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" id="examName"">
													<h4 class="text-left field-title">
														<b>Nome esame</b>
													</h4>
													<c:forEach items="${examList}" var="exam">
														<h3>${exam.name}</h3>
													</c:forEach>
												</div>
												<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
													<h4 class="text-center field-title">
														<b>CFU</b>
													</h4>
													<c:forEach items="${examList}" var="exam">
														<h3 class="text-center">${exam.CFU}</h3>
													</c:forEach>
												</div>
												<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" id="buttons">
													<h4 class="text-left field-title">
														<b>Tasti</b>
													</h4>
													<c:forEach items="${examList}" var="exam">
														<button onClick="send('<%=request.getAttribute("universityName")%>', '${exam.name}', '${exam.CFU}')" 
														class="button" data-toggle="tooltip" data-html="true"
											data-placement="top"
											title="<b><em>Invia una mail al docente</em></b>">Invia mail</button>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div id="certificatePreview">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<h4 class="text-left description">
											<em>Certificato di carriera pregressa dello studente</em>
										</h4>
										<div class="orange-frame">
											<embed src=<%= sess.getAttribute("pathCP") %>
												type="application/pdf" width="100%" height="600px" style="border-radius: 10px;"></embed>
										</div>
									</div>
								</div>

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