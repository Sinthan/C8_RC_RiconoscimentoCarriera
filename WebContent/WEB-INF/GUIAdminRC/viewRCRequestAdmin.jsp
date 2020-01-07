<%-- 
  - Author(s): Gianluca Rossi, Lorenzo Maturo
  - Date: 30/12/2019
  - Description: This page allows the admin to view the details of a specific request of previous career recognition.
  --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Exam, model.Suggestion, controller.Utils"%>

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
	String pageName = "viewRCRequestAdmin.jsp";
	String pageFolder = "GUIAdminRC";
	HttpSession sess = request.getSession();
	ArrayList<Suggestion> suggList = (ArrayList<Suggestion>) request.getAttribute("suggList");
	ArrayList<String> mailsSended = (ArrayList<String>) request.getAttribute("mailsSended");
	int examRow = 1;
%>
<script type="text/javascript">
	var examSelected = null
	var requestRCID = null
	var index = null
	
	window.onload = function(){
		controlServlet();
		$('[data-toggle="tooltip"]').tooltip(); // Tooltip setup
	};
	
	// If the servlet sent an error, show it
	function controlServlet() {
		var err = '<%=request.getAttribute("errorVR")%>';
		if (err != "null") {
			showAlert(1, err);
		}
	}

	function autoFillModal(examName, examCFU, examLink, requestID, examRow) {
		// Get the email element and resets it
		emailField = document.getElementById("recipient-name");
		emailField.value = "";
		// Get the textarea element
		document.getElementById("recipient-name").value = "";
		txtArea = document.getElementById("message-text");
		txtArea.value = "[DINF-UNISA] Richiesta di riconoscimento carriera pregressa\n" +
				"\nUniversità di provenienza dello studente: " + "${universityName}" +
				"\nEsame che si intende validare: " + examName +
				"\nCFU: " + examCFU +
				"\nLink al piano di studi: " + examLink +
				"\nNome dello studente: " + "${studentName}";
		examSelected = examName; 
		requestRCID = requestID;
		index = examRow;
	}

	function validateMailAddress() {
		txtArea = document.getElementById("message-text").value;
		btnSend = document.getElementById("btnSend");
		btnSend.disabled = true;
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document
				.getElementById("recipient-name").value)) {
			if (txtArea != null && txtArea.length > 0) {
				btnSend.disabled = false;
			}
		}
	}

	function validateMailBody() {
		btnSend = document.getElementById("btnSend");
		btnSend.disabled = true;
		if (txtArea != null && txtArea.length > 0) {
			if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document
					.getElementById("recipient-name").value)) {
				btnSend.disabled = false;
			}
		}
	}

	function sendMail() {
		mailD = document.getElementById("recipient-name").value;
		txtArea = document.getElementById("message-text").value;
		$.ajax({
			type : 'POST',
			url : 'RequestRCManagement',
			data : 'recipient-name=' + mailD + '&message-text=' + txtArea + '&exam-selected=' + examSelected + '&requestRCID=' + requestRCID,
			error : function(response) {
				// Gets called when an error occurs with error details in variable response
				showAlert(1, "Errore nell'invio dell'email.");
			},
			success : function(response) {
				// Gets called when the action is successful with server response in variable response
				//document.getElementById("btnMail" + index).style["background-color"] = 'red';
				document.getElementById('btnMail' + index).className = "btn btn-danger btn-square";
				
				showAlert(0, "Email inviata correttamente.");
			}
		});
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
										Richiesta di
										<%=request.getAttribute("studentName")%>
										-
										<%=request.getAttribute("submissionDate")%>
									</h1>
								</div>
<!-- Email modal -->
								<div class="modal fade" id="modal" tabindex="-1" role="dialog"
									aria-labelledby="modalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="modalLabel">Invia mail al
													docente</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<div class="form-group">
													<label for="recipient-name" class="col-form-label">Indirizzo
														email del docente:</label> <input type="email"
														class="form-control" id="recipient-name"
														oninput="validateMailAddress()" name="recipient-name"
														placeholder="emaildocente@esempio.com">
												</div>
												<div class="form-group">
													<label for="message-text" class="col-form-label">Messaggio:</label>
													<textarea rows="5" cols="100" class="form-control"
														id="message-text" onChange="validateMailBody()"
														name="message-text"></textarea>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Annulla</button>
												<button id="btnSend" onclick="sendMail()" type="button"
													class="btn btn-primary" data-dismiss="modal"
													disabled="disabled">Invia</button>
											</div>
										</div>
									</div>
								</div>
<!-- Modal end -->
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
											<div id="examsListHeader" class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"
													id="examNameColumn1"">
													<h4 class="text-left field-title">
														<b>Nome esame</b>
													</h4>
												</div>
												<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
													<h4 class="text-center field-title">
														<b>CFU</b>
													</h4>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center"
													id="buttons">
													<br>
												</div>
											</div>
											<c:forEach items="${examList}" var="exam">
												<div id="examsListRow<%=examRow%>" class="row">
<!-- Exam name -->
													<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"
														id="examNameColumn<%=examRow%>">
														<h4 class="list-element">${exam.name}</h4>
													</div>
<!-- Exam name end-->
<!-- Exam CFU -->
													<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
														<h4 class="text-center list-element-centered">${exam.CFU}</h4>
													</div>
<!-- Exam CFU end -->
<!-- Exam buttons -->
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5"
														id="buttons" style="padding-left: 74px;">
														
														
														<span data-toggle="modal" data-target="#modal">
														
															
															<%
																if (mailsSended.get(examRow - 1) == null) {
															%>
															<button id="btnMail<%=examRow-1%>" type="button"
																onClick="autoFillModal('${exam.name}', '${exam.CFU}', '${exam.programLink}', '${idRequestRC}', <%=examRow-1%>)"
																class="btn btn-primary btn-square" data-toggle="tooltip"
																data-html="true" data-placement="bottom"
																title="<b><em>Contatta il docente</em></b>">
																<img id="imgMail" src="css/svg/mail.svg" class="btn-icon">
															</button>
															<%
																} else {
															%>
															<button id="btnMailSended<%=examRow-1%>"  id="btnMail" type="button"
																onClick="autoFillModal('${exam.name}', '${exam.CFU}', '${exam.programLink}', '${idRequestRC}', '<%=examRow-1%>')"
																class="btn btn-danger btn-square" data-toggle="tooltip"
																data-html="true" data-placement="bottom"
																title="<b><em>Docente gi&#224; contattato</em></b>">
																<img id="imgMail" src="css/svg/mail.svg" class="btn-icon">
															</button>
															<%
																}
															%>
														</span> 
														
														<a onclick="window.open('${exam.programLink}', '_blank')"
															class="btn btn-primary btn-square" data-toggle="tooltip"
															data-html="true" data-placement="bottom"
															title="<b><em>Vai al piano di studi</em></b>"> <img
															src="css/svg/external-link.svg" class="btn-icon">
														</a>
<!-- Exam suggestion -->
														<%
															if (suggList.get(examRow - 1) != null) {
														%>
														<span data-toggle="collapse"
															data-target="#suggestion<%=examRow%>"
															aria-expanded="false"
															aria-controls="suggestion<%=examRow%>">
															<button class="btn btn-primary btn-square" type="button"
																data-toggle="tooltip" data-html="true"
																data-placement="bottom"
																title="<b><em>Visualizza suggerimento</em></b>">
																<img src="css/svg/help-circle.svg" class="btn-icon">
															</button>
														</span>

													</div>
												</div>

												<div
													class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse"
													id="suggestion<%=examRow%>">
													<div class="card card-body suggestion">
														<h4 id="suggestion<%=examRow%>Title"
															class="suggestion-body"><b><em>Validato il <%=Utils.getFormattedDate(suggList.get(examRow - 1).getValidationDate())%></em></b>
														</h4>
														<h4 id="suggestion<%=examRow%>Body" class="suggestion-body"><%=suggList.get(examRow - 1).getValidationMode()%></h4>
													</div>
												</div>

												<%
													} else {
												%>
												<span data-toggle="tooltip" data-html="true"
													data-placement="bottom"
													title="<b><em>Suggerimento non disponibile</em></b>"
													style = "padding-bottom: 13px;">
													<button class="btn btn-primary btn-square" type="button"
														disabled>
														<img src="css/svg/help-circle.svg" class="btn-icon">
													</button>
												</span>
										</div>
									</div>
									<%
										}
											examRow++;
									%>
<!-- Exam suggestion end -->
<!-- Exam buttons end -->
									</c:forEach>
<!-- Adding an extra div after the last suggestion in order to make the orange container resize as the last suggestion gets expanded -->
									<div>&nbsp;</div>
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
	<jsp:include page="/partials/includes.jsp" />
</body>
</html>
