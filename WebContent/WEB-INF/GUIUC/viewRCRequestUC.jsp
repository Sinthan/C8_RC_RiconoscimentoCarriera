<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String pageName = "viewRCRequestUC.jsp";
	String pageFolder = "GUIUC";
%>

<!DOCTYPE html>
<html>
<head>
<%
			HttpSession sess = request.getSession();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<%= request.getContextPath() %>/css/RC/viewRCRequestUC.css"
	rel="stylesheet">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">

<script type='text/javascript'> 
		
		window.onload = function(){
			controlServlet();
			$('[data-toggle="tooltip"]').tooltip(); // Tooltip setup
		};
		
		// If the servlet sent an error, show it
		function controlServlet() {
			var err = '<%=request.getAttribute("errorUC")%>';
			if (err != "null") {
				showAlert(1, err);
			}
		}
		
		//Check the body of the mail
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
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="page-wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader"></div> -->

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
<!-- sezione dedicata alle informazioni personali dello studente-->
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel">
									<br>
										<h1 class="text-left">Richiesta di ${userRC.name} ${userRC.surname} - ${submissionDate}</h1>
									</div>
									<div class="studentName">
											<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 indentedDiv">
											<h4 class="text-left"><b>Nome studente</b></h4>
											<h3>${userRC.name}</h3>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 indentedDiv">
											<h4 class="text-left"><b>Cognome studente</b></h4>
											<h3>${userRC.surname}</h3>
											</div>
										</div>
<!-- sezione dedicata alla lista degli esami della richiesta-->
<div class="indentedDiv">
										<h4 class="text-left description"><em>Esami inseriti dallo studente</em></h4>
									<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 orange-frame">
									
									<div id="examsListHeader" class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5"
													id="examNameColumn1">
													<h4 class="text-left field-title">
														<b>Nome esame</b>
													</h4>
												</div>
												<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
													<h4 class="text-center field-title">
														<b>CFU</b>
													</h4>
												</div>
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5"
													id="buttons">
													<h4 class="text-center field-title">
														<b>Riferimento al programma di esame</b>
													</h4>
												</div>
											</div>
											<c:forEach items="${exams}" var="exam">
												<div class="row">
	<!-- Exam name -->
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
														<h4 class="list-element">${exam.name}</h4>
													</div>
	<!-- Exam name end-->
	<!-- Exam CFU -->
													<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="CFU">
														<h4 class="text-center list-element-centered">${exam.CFU}</h4>
													</div>
	<!-- Exam CFU end -->
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
													<a onclick="window.open('${exam.programLink}', '_blank')"
															title="Vai al piano di studi">
													<h5 class="list-element">${exam.programLink}</h5>
														</a>
														</div>
														</div>
														</c:forEach>
									</div>
									</div>

<!-- sezione dedicata al download dei file della richiesta-->
						<div class="downloadButton col-12" >
						<div class="col-12 indentedDiv" >
						<form class="buttonForm" action="./DownloaderRC" method="post">
							<input type=hidden name=pdfvalue value="id" /> 
							<input type=hidden name=pathpdf value="${filePath}" />
								<button class="btn btn-primary btn-square" type="submit">
									<img id="imgDownload" src="css/svg/download.svg" class="btn-icon">
								</button>
						</form>
						<h4 id="drc1">Download documento d'identit&agrave;</h4>
						</div>
						
						
						<div class="col-12 indentedDiv" >
						<form class="buttonForm" action="./DownloaderRC" method="post">
							<input type=hidden name=pdfvalue value="cp" />
							<input type=hidden name=pathpdf value="${filePath}"/>
							<button class="btn btn-primary btn-square">
								<img id="imgDownload" src="css/svg/download.svg" class="btn-icon">
							</button>
							<h4 id="drc2">Download documento di riconoscimento carriera</h4>
						</form>
						</div>
						</div>

<!-- Decisional buttons -->
						<div class="submitButton col-12" >
							<button id="reject" value="accepted" type="submit" class="reject"
								data-toggle="modal" data-target="#popupModal">
								<span class="circle"> <span class="icon arrow"></span>
								</span> <span class="button-text">Rifiuta</span>
							</button>
							<form id="acceptForm" action="./RequestRCManagement">
								<input type=hidden name=RequestRCstate value="true"/>

								<button id="accept" value="accepted" type="submit"
									class="finish">
									<span class="circle"> <span class="icon arrow"></span>
									</span> <span class="button-text">Accetta</span>
								</button>
							</form>
							</div>
<!-- Decisional buttons end-->

<!-- Modal -->
							<form action="./RequestRCManagement" method="post">
								<div class="modal fade" id="popupModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
									    	<div class="modal-header">
									        	<h3 class="modal-title" id="exampleModalLabel">Motivo del rifiuto</h3>
									        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          		<span aria-hidden="true">&times;</span>
									        	</button>
									      	</div>
									      	<div class="modal-body">
									      		<div class="form-group">
													<label for="message-text" class="col-form-label">Messaggio:</label>
														<textarea rows="5" cols="100" class="form-control"
														id="message-text" placeholder="La richiesta è stata rifiutata perchè..." onChange="validateMailBody()"
														name="popupText" required>
														</textarea>
												</div>
										    	<input type = hidden name = RequestRCstate value="false"/>
									      	</div>
									      	<div class="modal-footer">
									        	<button type="reset" class="btn btn-secondary" data-dismiss="modal" >Annulla</button>
									         	<button type="submit" class="btn btn-primary" >Invia</button>
											</div>
									    </div>
									 </div>
								</div>
						 	</form>
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
