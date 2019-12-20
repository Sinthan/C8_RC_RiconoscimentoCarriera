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
</head>

<body onLoad="showData()">
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
																					
											<!-- Controllo per lo stato della richiesta -->
											<% 
										
												if(request.getAttribute("rRCState").toString().equals("needsUCValidation")){													
													out.print("<td class='text-center' align='center'><img src='imagesRC/sent.png' alt='greyPoit'>Richiesta in revisione</td>");
												}else if(request.getAttribute("rRCState").toString().equals("isBeingDiscussed")){
													out.print("<td class='text-center' align='center'><img src='imagesRC/pending.png' alt='orangePoit'>Richiesta in valutazione</td>");
												}else if(request.getAttribute("rRCState").toString().equals("approved")){
													out.print("<td class='text-center' align='center'><img src='imagesRC/approved.png' alt='greenPoit'>Richiesta approvata</td>");
												}else{
													out.print("<td>refused</td>");
												}
											%>
											
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

