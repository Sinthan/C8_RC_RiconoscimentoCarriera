<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ page
	import="java.util.ArrayList, model.ValidatedExam,model.ValidatedExamDAO"%>
<%
	String pageName = "createReport.jsp";
	String pageFolder = "GUIAdminRC";
%>
<!DOCTYPE html>
<html>
<script type='text/javascript'>
	var rowCount = 1;
	var examNameRegex = /^(\w+\s?\-?)*(\-?\s*\w*)*$/;
	var CFURegex = /[0-9]{1,2}/;
	
	window.onload = function(){
		controlServlet();
		$('[data-toggle="tooltip"]').tooltip()
	};
	
	// If the servlet sent an error, show it
	function controlServlet() {
		var err = '<%=request.getAttribute("errorCR2")%>';
		if(err != "null") {
			showAlert(1, err);
		}
	}
	
	function allowNumbersOnly(e) {
	    var code = (e.which) ? e.which : e.keyCode;
	    if (code > 31 && (code < 48 || code > 57)) {
	        e.preventDefault();
	        showAlert(1, "Sono consentiti solo caratteri numerici.");
	    }
	}
	
	function maxLengthCheck(object) {
	    if (object.value.length > object.max.length)
	      object.value = object.value.slice(0, object.max.length)
	  }
	
	function validateCFU(object) {
		if(this.value <= 30) {
			return true;
		} else if(object.value > 30) {
			object.value = '30';
			showAlert(1, "Il valore massimo dei CFU &#232; 30");
			this.focus();
			element.className += " is-invalid";
			return false;
		}
	}

	function allowAlphaNumericOnly(e) {
		var keyCode = (e.which) ? e.which : e.keyCode;
		if (!(keyCode == 95) && // _
				!(keyCode == 45) && // -
				!(keyCode == 32) && // Space
			    !(keyCode > 47 && keyCode < 58) && // Numeric (0-9)
			    !(keyCode > 64 && keyCode < 91) && // Upper alpha (A-Z)
			    !(keyCode > 96 && keyCode < 123)) { // Lower alpha (a-z)
			e.preventDefault();
			showAlert(1, "Sono consentiti solo caratteri alfanumerici pi&#249; i caratteri \"-\" e \"_\".");
		}
	}

</script>
<head>
<link rel="stylesheet" href="css/RC/createReport.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Repost</title>
</head>
<body>
	<div class="page-wrapper">
		<%
			HttpSession sess = request.getSession();
		%>

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>

		<div class="sidebar-page-container basePage Report">
			<br />
			<h2 align=center>
				<b>Richieste da controllare</b>
			</h2>
			<br />
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<form action="SaveSendReportServlet" method="get">
									<table id="Report"
										class="display data-results table table-striped table-hover table-bordered">
										<thead>
											<tr align="center">
												<th class="text-center tableRequestRCtd" align="center"
													width="30%">Nome esame</th>
												<th class="text-center tableRequestRCtd" align="center"
													width="30%">CFU</th>
												<th class="text-center tableRequestRCtd" align="center"
													width="30%">Modalità di convalida</th>
											</tr>

											<%
												ValidatedExamDAO vExamDao = new ValidatedExamDAO();
												ArrayList<ValidatedExam> examList = (ArrayList<ValidatedExam>) request.getAttribute("examList");
												int rows = 1;
												sess.setAttribute("flag", false);


												for (ValidatedExam temp : examList) {
													
													out.print("<tr>");
													out.print("<td class=text-center align=center>" + "<textarea readonly name=examName" + rows +" id=examName" + rows
															+ " required >" + temp.getExamName()
															+ "</textarea></td>");													
													out.print("<td class=text-center align=center><input type=number name=CFU" + rows +" max=30 min=0"
															+ " minlength=1 maxlength=2 required id=CFU" + rows + " onkeypress=allowNumbersOnly(event)"
															+ " onblur=validateCFU(this) oninput=maxLengthCheck(this) value=" + temp.getValidatedCFU()
															+ " style=\"border:1px solid; width:10%\"></td>");
													out.print("<td class=text-center align=center>" + "<textarea name=mode"+ rows+ " id=mode" + rows
															+ " style=\"border:1px solid\" required >" + temp.getValidationProcedure()
															+ "</textarea></td>");
													out.print("</tr>");
													rows++;
													
												}
												
											%>
											
										</thead>
										<tbody id="bodyExamTable">

										</tbody>
									</table>
									<%
									sess.setAttribute("rows", rows);
									sess.setAttribute("repoID",examList.get(1).getReportID());
									%>
									<br> <br> <label>NOTE</label>
									<div class="note">
										<%
											out.print(
													"<textarea placeholder=\"Inserire note\" rows=\"10\" cols=\"100\" style=\"border:1px solid;\" required name=\"note\" id=\"note\" >"
															+ request.getAttribute("note") + " </textarea>");
										%>
									</div>
									<div class="panel">
										<br>
										<button type="submit" class="btn btn-primary" id="btnSave" >
											Salva bozza</button>

										<button type="button" class="btn btn-danger" id="btnSend"
											onclick=<%request.setAttribute("flag", true);%>>
											Invia Report</button>
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