<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String pageName = "viewRCRequestUC.jsp";
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
									<div class="panel" style="float:left; text-align: left" >
										<h2 class="text-center">Richiesta di NOME - DATA</h2>
									</div>
									<div class="StudentName"  style="  width : 1000px; position: relative; float:left" >
									<div>
									<p style="font-weight: bold" >Nome studente </p>
									</div>
									<div>
									<p class="pFile" id="parFile1">&nbsp;&nbsp;&nbsp;&nbsp;NOME</p>
									</div>
									<div>
									<p style="font-weight: bold">Cognome studente </p>
									</div>
												<p class="pFile">&nbsp;&nbsp;&nbsp;&nbsp;COGNOME</p>		
									</div>		
									</div>			
											
										
								<div class="news-block-seven">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="panel" style="display: inline">
											<p class="text-left" style="font-weight: bold">Esami inseriti dallo studente</p>
											</div>
										<div id="ExamsDiv">
				
										<input type="hidden" name="rowCount" id="rowCount" value="1" />
										
										<div class="form-row" id=examInsertionRows>
											<div class="form-group col-md-4 mb-3">
												<label for="examName1">Nome esame</label> 
												<p>Programmazione 1</p>
											</div>
											
											<div class="form-group col-md-1 mb-3">
												<label for="CFU1">CFU</label>
												<p>9</p>	
											</div>

											<div class="form-group col-md-5 mb-3">
												<label for="programLink1">Link al programma d'esame</label>
												<p>www.link.com</p>
												
											</div>
											
											<br>
										</div>
										</div>
										</div>
										</div>
									
										<div class="downloadButton" style="  width : 1400px; position: relative; float:left">
											<div class="divDownload1" style="display: inline">
												<button class="btn" style="float:left"><i class="fa fa-download"></i></button>
												<p>&nbsp;Download documento d'identit&agrave;</p>
											</div>
											<div class="divDownload2" style="width: 800px; margin-bottom: 50px;  flaot:left; margin: 15 px ;">
												<button class="btn" style="float:left"><i class="fa fa-download"></i></button>
												<p>&nbsp;Download documento di riconoscimento carriera</p>
											</div>
										</div>
					
										<div class="submitButton" style=" margin-right: 60px; width: 200px; position: relative; float:right">
											
												<button class="btn btn-primary" type="submit" style=" margin-right: 20px; flaot: left; position: relative">Rifiuta</button>
										
												<button class="btn btn-primary" type="submit" style="flaot: right; position: relative">Accetta</button>
											
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