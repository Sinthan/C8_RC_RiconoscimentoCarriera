<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="org.apache.taglibs.standard.extra.spath.AbsolutePath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page import="java.util.*,model.RequestRC"%>
<%
	String pageName = "homepageRCStudent.jsp";
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

<body>
	<div class="page-wrapper">
 
		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>
		
		
	<div class="sidebar-page-container basePage indexPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 index-container">
									<div class="panel">
										<h2 class="text-center">Benvenuto</h2>
										<p></p>
										</div>	
										   		<form action="StudentRCRequestRedirector" method="get" id="formRC">
													<input type="hidden" value="1" id="flag" name="flag">
													<input  class="btn btn-primary btn-lg btn-block" type="submit" value="Riconoscimento Carriera Pregressa">
												</form>
										<p></p>
										  		 <form action="StudentRCRequestRedirector" method="get" id="formEV">
													<input type="hidden" value="2" id="flag" name="flag">
													<input  class="btn btn-primary btn-lg btn-block" type="submit" value="English Validation">
												 </form>	
									<p></p>
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