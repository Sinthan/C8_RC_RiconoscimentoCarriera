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

	<script type='text/javascript'> 
	
		function validationFile1(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("università").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if(!allowedExtension.exec(filePath1)){
				filePath1 = "";
				btnform.disabled = true;
				document.getElementById("parFile1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
				showAlert(1, "Formato file non accettato, inserire file in formato PDF.");
			}else{
				var printF = filePath1.substr(filePath1.lastIndexOf("\\")+1);
				document.getElementById("parFile1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + printF;
				showAlert(0, "File caricato correttamente.");
				if (allowedExtension.exec(filePath1) && allowedExtension.exec(filePath2) && uniSel!="defaultUni" ){
					btnform.disabled = false;
				}
			}
			 
		}
		
		function validationFile2(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("università").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if(!allowedExtension.exec(filePath2)){
				filePath2 = "";
				btnform.disabled = true;
				document.getElementById("parFile1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
				showAlert(1, "Formato file non accettato, inserire file in formato PDF.");
			}else{
				var printF = filePath2.substr(filePath2.lastIndexOf("\\")+1);
				document.getElementById("parFile2").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + printF;
				showAlert(0, "File caricato correttamente.");
				if (allowedExtension.exec(filePath1) && allowedExtension.exec(filePath2) && uniSel!="defaultUni"){
					btnform.disabled = false;
				}
			}
			 
		}
		
		function validationUni(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("università").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if( uniSel=="defaultUni" ){
				btnform.disabled = true;
			}else if(allowedExtension.exec(filePath1) && allowedExtension.exec(filePath2) && uniSel!="defaultUni"){
				btnform.disabled = false;
			}else{
				btnform.disabled = true;
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
		
		<%
			HttpSession sess = request.getSession();
			sess.setAttribute("flag",2);
		%>
		
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
									
									<form id="createRequestRC1" method="post" action="StudentManagement">
										
										<div class="divUni">
											<p class="pBold">
												Universit&agrave; di provenienza :
											</p>
											<div>
												<!-- <select id="università" onChange="validationUni()" style="margin-left: 10px;">
													<option style="" value="defaultUni" selected>Seleziona una Universit&agrave;</option>
													<option style="" value="unisa">Universit&agrave; degli studi di Salerno</option>
													<option style="" value="unina">Università di Napoli</option>
													<option style="" value="unipi">Università di Pisa</option>
												</select> -->
												
												
												
												<select id="università" onChange="validationUni()" name="università">
													<option style="" value="defaultUni" selected>Seleziona una Universit&agrave;</option>
												  	<c:forEach items="${universities}" var="university">
												    	<option value="${university.name}">
												    		${university.name}
												    	</option>
												  	</c:forEach>
												</select>
												
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
													<input class="fileS" type="file" id="file1" onChange="validationFile1()" accept="application/pdf" ></input> 
													<label for="file1" class="btn-2">+</label>
												</p>
												<p class="pFile" id="parFile1">
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
												<p class="pTFile"> 
													<input class="fileS" type="file" id="file2" onChange="validationFile2()" accept="application/pdf" ></input> 
													<label for="file2" class="btn-2">+</label>
												</p>
												<p class="pFile" id="parFile2">
													&nbsp;&nbsp;&nbsp;&nbsp;Seleziona un file
												</p>
											</div>
										</div>
										
										<div class="divNext">
											<p class="pNext" style="float: right;">
												Carica file e vai all'inserimento degli esami&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
												<button id="buttonCreateRequestRC1" class="bNext" type="submit" disabled="disabled">></button> 
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