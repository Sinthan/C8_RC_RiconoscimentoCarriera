<%-- 
  - Author(s): Gianluca Rossi, Lorenzo Maturo
  - Date: 30/12/2019
  - Description: This page allows the admin to view the details of a specific request of previous career recognition.
  --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String pageName = "viewRCRequest.jsp";
	String pageFolder = "GUIAdminRC";
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/RC/viewRCRequestAdmin.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Controlla Richiesta</title>
</head>
<body>
	<div class="page-wrapper">
		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>

		<%
			HttpSession sess = request.getSession();
			sess.setAttribute("flag", 2);
		%>

		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel"display:inline;">
										<h1 class="text-left">
											<b>Richiesta di</b>
										</h1>
									</div>

									<div id="requestSummary">
										<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
											<h4 class="text-left">
												<em>Informazioni sulla carriera pregressa fornite dallo
													studente</em>
											</h4>
											<div class="orange-frame">
												<h4 class="text-left">
													<b>Università di provenienza</b>
												</h4>
												<h3 class="text-left"
													style="margin-left: 16px; margin-top: 10px; margin-bottom: 16px;">Università
													del NEPAL</h3>
												<div id="examsList">
													<div class="col-md-3 mb-3" id="examName"
														style="margin: 0; padding: 0;">
														<h4 class="text-left">
															<b>Nome esame</b>
														</h4>
													</div>
													<div class="col-md-1 mb-3" id="CFU"
														style="margin: 0; padding: 0;">
														<h4 class="text-left">
															<b>CFU</b>
														</h4>
													</div>
													<div class="col-md-2 mb-3" id="buttons"
														style="margin: 0; padding: 0;"></div>
												</div>
											</div>
										</div>
									</div>

									<div id="certificatePreview"></div>

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