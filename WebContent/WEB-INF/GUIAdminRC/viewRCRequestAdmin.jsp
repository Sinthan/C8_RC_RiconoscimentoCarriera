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
				+ '&body=' + 'UniversitÓ di Provenienza : ' + nameU + '%0D%0A'
				+ 'Nome Esame : ' + nameE + '%0D%0A' + 'Cfu Convalidati : '
				+ cfu + '%0D%0A');
	}
	
	/* function sendMail(){
		document.getElementById("btnMail");
		var recipient = button.data('whatever');
		var modal = $(this);
		consol.log(recipient)
		consol.log("ciao")
		modal.find('.modal-title').text('New message to ' + recipient)
		modal.find('.modal-body input').val(recipient)
	}
	 */
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
												<b>UniversitÓ di provenienza</b>
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
														<%-- <button
															onClick="send('<%=request.getAttribute("universityName")%>', '${exam.name}', '${exam.CFU}')"
															class="button" data-toggle="tooltip" data-html="true"
															data-placement="top"
															title="<b><em>Invia una mail al docente</em></b>">Invia
															mail
														</button> --%>
														<button id="btnMail" type="button" onClick="sendMail()" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">Open modal for @getbootstrap</button>
														<div class="modal fade" id="exampleModal" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalLabel"
															aria-hidden="true">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLabel">New
																			message</h5>
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body">
																		<form>
																			<div class="form-group">
																				<label for="recipient-name" class="col-form-label">Send Mail to:</label>
																				<input type="text" class="form-control"
																					id="recipient-name">
																			</div>
																			<div class="form-group">
																				<label for="message-text" class="col-form-label">Message:</label>
																				<textarea rows="5" cols="100" class="form-control" id="message-text" value="${exam.name}">[DINF-UNISA] Richiesta di Riconoscimento Carriera Pregressa &#10;&#10;UniversitÓ di Provenienza : ${universityName} &#10;Nome Esame : ${exam.name} &#10;Cfu Convalidati : ${exam.name} &#10;
																				</textarea>
																			</div>
																		</form>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal">Close</button>
																		<button type="button" class="btn btn-primary">Send
																			message</button>
																	</div>
																</div>
															</div>
														</div>

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
