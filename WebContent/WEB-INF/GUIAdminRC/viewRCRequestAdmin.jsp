<%-- 
  - Author(s): Gianluca Rossi, Lorenzo Maturo
  - Date: 30/12/2019
  - Description: This page allows the admin to view the details of a specific request of previous career recognition.
  --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*, model.RequestRC, model.RequestRCDAO"%>

<%
	String pageName = "viewRCRequestAdmin.jsp";
	String pageFolder = "GUIAdminRC";
%>


<!DOCTYPE html>
<html>
<head>
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
		$('[data-toggle="tooltip"]').tooltip()
		addExamList();
	};
	
	// If the servlet sent an error, show it
	function controlServlet() {
		var err = '<%=request.getAttribute("errorVR")%>';
		if (err != "null") {
			showAlert(1, err);
		}
	}

	function addExamList() {
		var examNameArray = [<%=request.getAttribute("examNameArray")%>];
		examNameArray.forEach(addExamName);

		var CFUArray = [<%=request.getAttribute("CFUArray")%>];
		CFUArray.forEach(addCFU);
		
		var linkArray = [<%=request.getAttribute("linkArray")%>];
		linkArray.forEach(addLinkBtn);
		
	}
	
	function addCFU(value) {
		// Creates the <p> element for the CFU
		var CFU = document.createElement("p");
		CFU.innerHTML = value;
		CFU.className = "text-center";
		CFUDiv.appendChild(CFU);
	}

	function addExamName(name) {
		// Creates the <p> element for the exam name
		var examName = document.createElement("p");
		examName.innerHTML = name;
		examNameDiv.appendChild(examName);
	}
	
	function addLinkBtn(link) {
		// Creates the <button> element for the program link of the exam
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
										<b>Richiesta di <%=request.getAttribute("studentName")%> - <%=request.getAttribute("submissionDate")%></b>
									</h1>
								</div>

								<div id="requestSummary">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<h4 class="text-left">
											<em>Informazioni sulla carriera pregressa fornite dallo
												studente</em>
										</h4>
										<div class="orange-frame">
											<h4 class="text-left">
												<b>Università di provenienza</b>
											</h4>
											<h3 class="text-left"
												style="margin-left: 16px; margin-top: 10px; margin-bottom: 16px;"><%=request.getAttribute("universityName")%></h3>
											<div id="examsList">
												<div class="col-md-3 mb-3" id="examName"
													style="padding-left: 0;">
													<h4 class="text-left">
														<b>Nome esame</b>
													</h4>
												</div>
												<div class="col-md-1 mb-3" id="CFU" style="padding-left: 0;">
													<h4 class="text-center">
														<b>CFU</b>
													</h4>
												</div>
												<div class="col-md-2 mb-3" id="buttons"></div>
											</div>
										</div>
									</div>
								</div>

								<div id="certificatePreview"></div>

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