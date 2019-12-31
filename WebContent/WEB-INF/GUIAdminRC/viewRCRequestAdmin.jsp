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
<script type="text/javascript">

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
		window.open('mailto: ?subject='
				+ '[DINF-UNISA] Richiesta di Riconoscimento Carriera Pregressa'
				+ '&body=' + 'Università di Provenienza : ' + nameU + '%0D%0A'
				+ 'Nome Esame : ' + nameE + '%0D%0A' + 'Cfu Convalidati : '
				+ cfu + '%0D%0A');
	}
	
	function autoFillModal(examName, examCFU, examLink) {
		// Get the textarea element
		txtArea = document.getElementById("message-text");
		txtArea.innerHTML = "[DINF-UNISA] Richiesta di Riconoscimento Carriera Pregressa\n" +
							"\nUniversità di provenienza dello studente: " + "${universityName}" +
							"\nEsame che si intende validare: " + examName +
							"\nCFU: " + examCFU +
							"\nLink al piano di studi: " + examLink +
							"\nNome dello studente: " + "${studentName}";
		txtArea.value = "[DINF-UNISA] Richiesta di Riconoscimento Carriera Pregressa\n" +
							"\nUniversità di provenienza dello studente: " + "${universityName}" +
							"\nEsame che si intende validare: " + examName +
							"\nCFU: " + examCFU +
							"\nNome dello studente: " + "${studentName}";
	}
	
	function validationMail() {
		 
		 txtArea = document.getElementById("message-text").value;
		 btnSend = document.getElementById("btnSend");
		 btnSend.disabled = true;
		 if ( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById("recipient-name").value) ) {
			 if( txtArea != null && txtArea.length > 0 ) {
			 	btnSend.disabled = false;
			 }
		  }
	}
	
	function validationBodyMail() {
		 txtArea = document.getElementById("message-text").value;
		 btnSend = document.getElementById("btnSend");
		 btnSend.disabled = true;
		 if ( txtArea != null && txtArea.length > 0  ) {
			 if ( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById("recipient-name").value) ) {
				 btnSend.disabled = false;
			 }
		  }
	}
	
	function sendMail(){
		var txtArea = document.getElementById("message-text");
		var modal = $(this);
		consol.log(recipient)
		consol.log("ciao")
		modal.find('.modal-title').text('New message to ' + recipient)
		modal.find('.modal-body input').val(recipient)
	}
	
	/* $('#exampleModal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var recipient = button.data('whatever') // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this)
		consol.log(recipient)
		consol.log("ciao")
		modal.find('.modal-title').text('New message to ' + recipient)
		modal.find('.modal-body input').val(recipient)
	}) */
	
</script>
</head>
<body>
	<div class="page-wrapper">
		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>
		<div class="modal fade" id="modal" tabindex="-1" role="dialog"
			aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalLabel">Invia mail al docente</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">Indirizzo email del docente:</label> <input type="email" class="form-control"
									id="recipient-name" onChange="validationMail()" name="recipient-name ">
							</div>
							<div class="form-group">
								<label for="message-text" class="col-form-label">Messaggio:</label>
								<textarea rows="5" cols="100" class="form-control"
									id="message-text" onChange="validationBodyMail()" name="message-text"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Annulla</button>
						<button id="btnSend" onClick="sendMail()" type="button" class="btn btn-primary" disabled="disabled">Invia</button>
					</div>
				</div>
			</div>
		</div>
		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
									<h1 class="text-left">
										Richiesta di
										<%=request.getAttribute("studentName")%>
										-
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
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"
													id="examName"">
													<h4 class="text-left field-title">
														<b>Nome esame</b>
													</h4>
													<c:forEach items="${examList}" var="exam">
														<h4 class="list-element">${exam.name}</h4>
													</c:forEach>
												</div>
												<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
													<h4 class="text-center field-title">
														<b>CFU</b>
													</h4>
													<c:forEach items="${examList}" var="exam">
														<h4 class="text-center list-element">${exam.CFU}</h4>
													</c:forEach>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"
													id="buttons">
													<br>
													<c:forEach items="${examList}" var="exam">
														<%-- <button
															onClick="send('<%=request.getAttribute("universityName")%>', '${exam.name}', '${exam.CFU}')"
															class="button" data-toggle="tooltip" data-html="true"
															data-placement="top"
															title="<b><em>Invia una mail al docente</em></b>">Invia
															mail
														</button> --%>
														<button id="btnMail" type="button" onClick="autoFillModal('${exam.name}', '${exam.CFU}')"
															class="btn btn-primary needsMargins" data-toggle="modal"
															data-target="#modal" data-whatever="@getbootstrap" style="display:inline;">M</button>
															<a onclick="window.open('${exam.programLink}', '_blank')" class="btn btn-primary needsMargins">L</a>
															<br>
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
											<embed src=<%=sess.getAttribute("pathCP")%>
												type="application/pdf" width="100%" height="600px"></embed>
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
