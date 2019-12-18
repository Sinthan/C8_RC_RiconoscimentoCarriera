<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String pageName = "";
	String pageFolder = "";
%>  
    
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script type='text/javascript' src="<%= request.getContextPath() %>/jsRC/validateCreateRequest.js"></script>
	<script type='text/javascript'> 
		function formValidation(){
			var fileInput1 = document.getElementById("file1");
			var fileInput2 = document.getElementById("file2");
			var filePath1 = document.getElementById("file1");
			var filePath2 = document.getElementById("file2");
			var allowedExtension = /(\.pdf)$/i;
			if(!allowedExtension.exec(filePath1) || !allowedExtension.exec(filePath2) ){
				fileInput1 = "";
				fileInput2 = "";
				showAlert(1, "Formato file non accettato");
				
			}
		}
	</script>
	<link href="<%= request.getContextPath() %>/css/RC/createRCRequest.css"
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

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 ">
									<div class="panel" style="float:left; margin-top:-70px" >
										<h1 class="text-center">Compila Richiesta</h1>
									</div>
									
									<form id="createRequestRC1" method="post" action="">
										
										<div class="divUni">
											<p class="pBold">
												Universit&agrave; di provenienza :
											</p>
											<div>
												<select name="università" style="margin-left: 10px;">
													<option style="" value="unisa" selected="selected">Seleziona universit&agrave;</option>
													<option style="" value="unisa" selected="selected">Universit&agrave; dei sapientini</option>
													<option style="" value="unisa" selected="selected">Universit&agrave; dei picassi</option>
													<option style="" value="unisa" selected="selected">Universit&agrave; dei serpenti</option>
												</select>
												
												<c:forEach  items="${universities}"  var="università">
						          					<a class="dropdown-item">
													<c:out value="${università.nome}"/></a>
						          					<div class="dropdown-divider"></div>
												</c:forEach>
											</div>	
										</div>
										
										<div class="divFile1">
											<div>
												<p class="pBold">
													&nbsp;&nbsp;&nbsp;&nbsp;Upload documento
												</p>
											</div>
											<div class="" >
												<p class="pTFile">
													<input class="fileS" type="file" id="file1" onChange="formValidation()" accept="application/pdf" ></input> 
													<label for="file1" class="btn-2">+</label>
												</p>
												<p class="pFile">
													&nbsp;&nbsp;&nbsp;&nbsp;Seleziona un file 
												</p>
											</div>
										</div>
										
										<div class="divFile2">
											<div>
												<p class="pBold">
													&nbsp;&nbsp;&nbsp;&nbsp;Upload certificato di carriera pregressa
												</p>
											</div>
											<div class="" >
												<div class="">
													<p class="pTFile"> 
														<input class="fileS" type="file" accept="application/pdf" id="file2"></input> 
														<label for="file2" class="btn-2">+</label>
													</p>
													<p class="pFile">
														&nbsp;&nbsp;&nbsp;&nbsp;Nome Allegato 
													</p>
												</div>
											</div>
										</div>
										
										<div class="divNext">
											<p class="pNext" style="float: right;">
												Carica file e vai all'inserimento degli esami&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
												<button class="bNext" type="submit">></button> 
											</p>
										</div>
									</form>
									
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