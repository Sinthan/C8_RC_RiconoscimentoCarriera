<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page import="java.util.*,model.RequestRC"%>
<%
	String pageName = "viewRCRequestStatus.jsp";
	String pageFolder = "_areaStudent";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());

	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<script type='text/javascript'>
	window.onload = function(){
		controlServlet();
	};
	
	// If the servlet has the status of the RCRequest insertion, show it
	function controlServlet() {
		var didInsertRequest = '<%=request.getAttribute("didInsertRequest")%>';
		if(didInsertRequest == "true") {
			showAlert(0, "Richiesta inserita con successo");
		}
	}
</script>
</head>

<body>
	<div class="page-wrapper">
 
		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>
		
		
	<div class="sidebar-page-container basePage viewRequestStudent">
			<br/><h2>La tua richiesta</h2><br/>
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
							<table id="studentTableRC" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center tableRequestRCtd" align="center">Data di invio</th>
											<th class="text-center tableRequestRCtd" align="center">Stato richiesta</th>
										</tr>
										<tr>
											<td class="text-center" align="center">${rRCDate}</td>	
											<td class="text-center" align='center'>${stName}</td>
												</tr>
											</thead>
										<tbody id="bodyStudentTable">
									</tbody>
								</table>
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

		<script
		src=" request.getContextPath() /js/pages/scripts_viewRCRequestStatus.js">	
	</script>

</body>
</html>

