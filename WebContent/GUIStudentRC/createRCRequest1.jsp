<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%
	String pageName = "createRCRequest.jsp";
	String pageFolder = "";
%>  
    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href = "css/createRCRequest.css">
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
		
		<div class="sidebar-page-container basePage createRequestRCPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 login-container">
									<div class="panel" style="float:left;" >
										<h1 class="text-center">Compila Richiesta</h1>
									</div>
									
									<form id="createRequestRC1" method="post" action="">
										
										
										<div class="form-group" style="position:static; float: left;">
											<p  style="float: left;" >
												Universit&agrave; di provenienza
											</p>
											<input name="nameU" class="formInput" id="nameU" placeholder="Nome Università" minlength="6" required>
										</div>
										
										
										
										<div class="form-group" style="position:static;  ">
											<div class="form-group" style="position:static; padding:15px;">
												<p  style="float: left; margin: 0; font-weight:bold;" >
													&nbsp;&nbsp;&nbsp;&nbsp;Upload documento
												</p>
											</div>
											<div class="form-group" style="position:static; padding:10px; ">
												<p  style="float: left">
													<button name="uploadD" class="btn btn-primary btn-submit">+</button> 
												</p>
												<p  style="float: left;">
													&nbsp;&nbsp;&nbsp;&nbsp;Seleziona un file 
												</p>
											</div>
										</div>
											
											
											
										<div class="form-group" style="position:static; margin-top:100px">
											<div class="form-group" style="position:static; padding:15px; ">
												<p  style="float: left; margin: 0 ; font-weight:bold" >
													&nbsp;&nbsp;&nbsp;&nbsp;Upload certificato di carriera pregressa
												</p>
											</div>
											<div class="form-group" style="position:static; padding:10px;">
												
												<div class="form-group">
													<p class="text-center" style="float: left;">
														<button type="submit" class="btn btn-primary btn-submit">+</button> 
													</p>
													<p class="text-center" style="float: left;">
														&nbsp;&nbsp;&nbsp;&nbsp;Nome Allegato 
													</p>
												</div>
											</div>
										</div>
										
										<div class="form-group" style="position:static; padding:18px;">
											<p class="text-center" style="float: right;">
												Carica file e vai all'inserimento degli esami&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
												<button type="submit" class="btn btn-primary btn-submit" style="border-radius:20px;">></button> 
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