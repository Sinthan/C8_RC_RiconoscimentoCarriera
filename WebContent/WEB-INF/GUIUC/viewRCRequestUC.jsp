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
		
		
		// If the servlet sent an error, show it
		function controlServlet() {
			var err = '<%=request.getAttribute("errorVR")%>';
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

		<div class="sidebar-page-container basePage createRequestRCPage"
			style="">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
<!-- sezione dedicata alle informazioni personali dello studente-->
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 ">
									<div class="panel">
										<h3 class="text-center">Richiesta di ${userRC.name}
											${userRC.surname} - ${reqRC.submissionDate}</h3>
									</div>
									<div class="studentName">
										<div>
											<h4 class="text-left">Nome studente</h4>
										</div>
										<div>
											<h4 class="studentData">&nbsp;&nbsp;&nbsp;${userRC.name}</h4>
										</div>
										<div>
											<h4 class="text-left">Cognome studente</h4>
										</div>
										<h4 class="studentData">&nbsp;&nbsp;&nbsp;${userRC.surname}</h4>
										<br>
									</div>
								</div>
<!-- sezione dedicata alla lista degli esami della richiesta-->
								<div class="news-block-seven">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

										<h4 class="text-left">Esami inseriti dallo studente</h4>
									</div>
									<div class="ExamsDiv">
										<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th scope="col">Nome esame</th>
													<th scope="col">CFU</th>
													<th scope="col">Riferimento al programma di esame</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${exams}" var="exam">
													<tr>
														<td>${exam.name}</td>
														<td>${exam.CFU}</td>
														<td>${exam.programLink}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
									</div>
								</div>
							</div>

<!-- sezione dedicata al download dei file della richiesta-->
						</div>
						<div class="downloadButton col-12" >
						<form class="buttonForm" action="./DownloaderRC" method="post">
							<input type=hidden name=pdfvalue value="id" /> 
							<input type=hidden name=pathpdf value="${filePath}" />
								<button class="btn btn-primary btn-square" type="submit">
									<img id="imgDownload" src="css/svg/download.svg" class="btn-icon">
								</button>
						</form>
						<p id="drc1">Download documento d'identit&agrave;</p>
						<br>
						<br>
						<form class="buttonForm" action="./DownloaderRC" method="post">
							<input type=hidden name=pdfvalue value="cp" />
							<input type=hidden name=pathpdf value="${filePath}"/>
							<button class="btn btn-primary btn-square">
								<img id="imgDownload" src="css/svg/download.svg" class="btn-icon">
							</button>
							<p id="drc2">Download documento di riconoscimento carriera</p>
						</form>
						</div>

<!-- Decisional buttons -->
						<div class="submitButton col-12" >
							<button id="reject" value="accepted" type="submit" class="reject"
								data-toggle="modal" data-target="#popupModal">
								<span class="circle"> <span class="icon arrow"></span>
								</span> <span class="button-text">Rifiuta</span>
							</button>
							<form id="acceptForm" action="./RequestRCManagement">
								<input type=hidden name=RequestRCstate value="true" />

								<button id="accept" value="accepted" type="submit"
									class="finish">
									<span class="circle"> <span class="icon arrow"></span>
									</span> <span class="button-text">Accetta</span>
								</button>
							</form>
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
														id="message-text" placeholder="La richiesta � stata rifiutata perch�..." onChange="validateMailBody()"
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

	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

</body>

</html>
