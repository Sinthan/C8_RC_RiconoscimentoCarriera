<%-- 
  - Author(s): Gianluca Rossi, Lorenzo Maturo
  - Date: 30/12/2019
  - Description: This page allows the student to compile the exams list related to his request of previous career recognition and to submit it.
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
<jsp:include page="/partials/head.jsp"/>
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

		<div class="sidebar-page-container basePage createRequestRCPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel"display:inline;">
										<h1 class="text-left">Inserisci esami</h1>
									</div>
									
									<div id="requestSummary"></div>
									
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