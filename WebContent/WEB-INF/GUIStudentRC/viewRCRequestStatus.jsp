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
			<br/><h2 style= "margin-left: 15px">La tua richiesta</h2><br/>
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
												}else if(request.getAttribute("rRCState").toString().equals("refused")){
													out.print("<td class='text-center' align='center'><img src='imagesRC/refused.png' alt='redPoit'>Richiesta rifiutata, controllare mail per dettagli</td>");
												}
											%>
												</tr>
											</thead>
										<tbody id="bodyStudentTable">
									</tbody>
								</table>
								<%
								
								
								HttpSession sess = request.getSession();
								sess.setAttribute("flag", 5);
							
								if(request.getAttribute("rRCState").toString().equals("refused")){
									out.print("<td class=submitButton-centre align=center style= margin-right: 60px; width: 200px; position: relative; float:centre>"+
									    "<form action='./StudentManagement' method=get align='right'>" +
										"<input type= submit value ='Crea una nuova Richiesta' name= 'flag' class=btn btn-primary></input></form>");
									}
									
								%>
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

