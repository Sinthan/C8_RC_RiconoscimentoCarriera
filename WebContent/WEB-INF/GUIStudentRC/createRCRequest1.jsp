<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String pageName = "createRCRequest1.jsp";
	String pageFolder = "GUIStudentRC";
%>

<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type='text/javascript'>
		window.onload = function(){
			controlServlet();
		};
	
		function controlServlet(){
			var err = '<%= request.getAttribute("errorCR1") %>';
			if( err.toString() != "null"){
				showAlert(1, err.toString());
			}
		}
	
		function validationFile1(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("universita").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if(!allowedExtension.exec(filePath1)){
				filePath1 = "";
				btnform.disabled = true;
				document.getElementById("parFile1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
				showAlert(1, "Formato file non accettato, inserire file in formato PDF.");
			}else{
				if( document.getElementById("file1").files[0].size > (1024*1024*10)-10 ){
					filePath1 = "";
					btnform.disabled = true;
					document.getElementById("parFile1").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
					showAlert(1, "Il file inserito supera la dimensione massima consentita, massimo 10MB");
				}else{
					var printF = filePath1.substr(filePath1.lastIndexOf("\\")+1);
					document.getElementById("parFile1").innerHTML = printF;
					showAlert(0, "File caricato correttamente.");
					if ( document.getElementById("file2").files[0].size < (1024*1024*10)-10 
							&& document.getElementById("file2").files[0].size < (1024*1024*10)-10 
							&& allowedExtension.exec(filePath1) 
							&& allowedExtension.exec(filePath2) 
							&& uniSel!="defaultUni" 
						){
						btnform.disabled = false;
					}
				}
			}
		}
		
		function validationFile2(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("universita").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if(!allowedExtension.exec(filePath2)){
				filePath2 = "";
				btnform.disabled = true;
				document.getElementById("parFile2").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
				showAlert(1, "Formato file non accettato, inserire file in formato PDF.");
			}else{
				if( document.getElementById("file2").files[0].size > (1024*1024*10)-10 ){
					filePath2 = "";
					btnform.disabled = true;
					document.getElementById("parFile2").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;" + "Seleziona un file";
					showAlert(1, "Il file inserito supera la dimensione massima consentita, massimo 10MB.");
				}else{
					var printF = filePath2.substr(filePath2.lastIndexOf("\\")+1);
					document.getElementById("parFile2").innerHTML = printF;
					showAlert(0, "File caricato correttamente.");
					if ( document.getElementById("file1").files[0].size < (1024*1024*10)-10 
							&& document.getElementById("file2").files[0].size < (1024*1024*10)-10 
							&& allowedExtension.exec(filePath1) 
							&& allowedExtension.exec(filePath2) 
							&& uniSel!="defaultUni"
						){
						btnform.disabled = false;
					}
				}
			}
		}
		
		function validationUni(){
			var filePath1 = document.getElementById("file1").value;
			var filePath2 = document.getElementById("file2").value;
			var uniSel = document.getElementById("universita").value;
			var btnform = document.getElementById("buttonCreateRequestRC1");
			var allowedExtension = /(\.pdf)$/i;
			if( uniSel=="defaultUni" ){
				btnform.disabled = true;
			}else if( document.getElementById("file1").files[0].size < (1024*1024*10)-10 
					&& document.getElementById("file2").files[0].size < (1024*1024*10)-10 
					&& allowedExtension.exec(filePath1) 
					&& allowedExtension.exec(filePath2) 
					&& uniSel!="defaultUni"
			){
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

		<div class="sidebar-page-container basePage createRequestRCPage"
			style="">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
								<br>
									<h1 class="text-left">
										Compila Richiesta
									</h1>
								</div>

									<form id="createRequestRC1" method="post"
										action="StudentManagement" enctype="multipart/form-data">

										<div class="divUni inputDescription">
											<h4><b>Universit&agrave; di provenienza</b></h4>
											<div>
												<!-- <select id="universitÓ" onChange="validationUni()" style="margin-left: 10px;">
													<option style="" value="defaultUni" selected>Seleziona una Universit&agrave;</option>
													<option style="" value="unisa">Universit&agrave; degli studi di Salerno</option>
													<option style="" value="unina">UniversitÓ di Napoli</option>
													<option style="" value="unipi">UniversitÓ di Pisa</option>
												</select> -->



												<select class="form-control uniSelect" id="universita"
													onChange="validationUni()" name="universita">
													<option value="defaultUni" selected>Seleziona una Universit&agrave;</option>
													<c:forEach items="${universities}" var="university">
														<option value="${university.name}">
															${university.name}</option>
													</c:forEach>
												</select>

											</div>
										</div>

										<div class="divFile1 inputDescription">
											<div>
												<h4><b>Upload documento</b></h4>
											</div>
											<div class="inputDiv">
												<span class="pTFile">
													<input type="file" id="file1" name="file1"
														onChange="validationFile1()" accept="application/pdf"></input>
													<label for="file1">+</label>
												</span>
												<h4 class= "fileNameLabel" id="parFile1">
													Seleziona un file</h4>
											</div>
										</div>

										<div class="divFile2 inputDescription">
											<div >
												<h4><b>Upload certificato di carriera pregressa</b></h4>
											</div>
											
											<div class="inputDiv">


											<span class="pTFile">
												<input type="file" id="file2" name="file2"
														onChange="validationFile2()" accept="application/pdf"></input>
														<label for="file2">+</label>

												</span>
												<h4 class= "fileNameLabel" id="parFile2">Seleziona un file</h4>
											</div>
										</div>

										<div class="form-group"
											style="position: fixed; padding: 32px; bottom: 0; right: 0;">
												<button id="buttonCreateRequestRC1" class="continue"
													type="submit" disabled="disabled">
													<span class="circle"> <span class="icon arrow"></span>
													</span> <span class="button-text">Carica file e vai
														all'inserimento degli esami</span>
												</button>
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