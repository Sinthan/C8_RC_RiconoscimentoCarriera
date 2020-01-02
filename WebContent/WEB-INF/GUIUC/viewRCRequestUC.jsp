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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<link href="<%= request.getContextPath() %>/css/RC/viewRCRequestUC.css"
	rel="stylesheet">
	<jsp:include page="/partials/head.jsp" />
	<meta charset="ISO-8859-1">
	
	<script type='text/javascript'> 
		
		function openForm() {
		  document.getElementById("myForm").style.display = "block";
		}

		function closeForm() {
		  document.getElementById("myForm").style.display = "none";
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
		
		<div class="sidebar-page-container basePage createRequestRCPage" style="">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 ">
									<div class="panel">
										<h2 class="text-center">Richiesta di ${userRC.name} ${userRC.surname} - ${reqRC.submissionDate}</h2>
									</div>
										<div class="studentName" >
											<div>
												<p style="font-weight: bold" >Nome studente </p>
											</div>
												<div>
													<p class="pDat" id="pName">&nbsp;&nbsp;&nbsp;&nbsp;${userRC.name}</p>
												</div>
													<div>
														<p style="font-weight: bold">Cognome studente</p>
													</div>
														<p class="pDat" id="pSurname">&nbsp;&nbsp;&nbsp;&nbsp;${userRC.surname}</p>		
													</div>		
								</div>					
									  	
									<div class="news-block-seven">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											
												<p class="text-left">Esami inseriti dallo studente</p>
											</div>
											
												<div id="ExamsDiv" >	
													<div class="form-row" id=examInsertionRows>
														<div class="form-group col-md-4 mb-3">
															<label for="examName1">Nome esame</label>
															<c:forEach items="${exams}" var="exam">
															<p>${exam.name}</p>
															</c:forEach>
														</div>
															<div class="form-group col-md-1 mb-3">
																<label for="CFU1">CFU</label>
																<c:forEach items="${exams}" var="exam">
																<p>${exam.CFU}</p>
																</c:forEach>
															</div>
														<div class="form-group col-md-5 mb-3">
															<label for="programLink1">Link al programma d'esame</label>
															<c:forEach items="${exams}" var="exam">
																<p>${exam.programLink}</p>
															</c:forEach>
														</div>
														<br>
													</div>
												</div>
											
										</div>
										
									<!-- sezione dedicata al download dei file della richiesta-->	
									</div>
									<div class="downloadButton">
										<div class="divDownload1" >
											<form action="./DownloaderRC" method = "post">
											<input type = hidden name = pdfvalue value="id"/>
											<input type = hidden name = pathpdf value="${filePath}"/>
											<button class="btn" type="submit"><i class="fa fa-download"></i></button>
											<p id="drc1" class="drc">Download documento d'identit&agrave;</p>
											</form>
										</div>
										<div class="divDownload2">
											<form action="./DownloaderRC" method = "post">
											<input type = hidden name = pdfvalue value="cp"/>
											<input type = hidden name = pathpdf value="${filePath}"/>
											<button class="btn"><i class="fa fa-download"></i></button></a>
											<p id="drc2" class="drc">Download documento di riconoscimento carriera</p>
											</form>
										</div>
										
									</div>
									
									<!-- popup per il rifiuto della richiesta -->
									<div class="submitButton" >
										<button id="reject" class="btn btn-primary" type="submit" onClick ="openForm()">Rifiuta</button>
										<form id ="acceptForm" action="./RequestRCManagement">
										<input type = hidden name = RequestRCstate value="true"/>
										<button id="accept" value="accepted" class="btn btn-primary" type="submit">Accetta</button> </form>
										<div class="form-popup" id="myForm">
										  <form action="./RequestRCManagement" class="form-container" id="containerPopup" method="post">
										    <h3>Motivo del rifiuto</h3>
										    <input type="text" placeholder="Motivazione.." name="popupText" required>
										    <input type = hidden name = RequestRCstate value="false"/>
										    <button type="submit" class="btn" >Inoltra</button>
										    <button type="submit" class="btn cancel" onclick="closeForm()">Annulla</button>
										  </form>
										</div>
									</div>
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