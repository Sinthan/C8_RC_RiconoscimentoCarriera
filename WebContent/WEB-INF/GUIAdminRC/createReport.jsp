<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, model.ValidatedExam, model.ValidatedExamDAO, model.Suggestion, controller.Utils"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/RC/createReport.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Compila Report</title>
<%
	String pageName = "createReport.jsp";
	String pageFolder = "GUIAdminRC";
	HttpSession sess = request.getSession();
	ArrayList<Suggestion> suggList = (ArrayList<Suggestion>) request.getAttribute("suggList");
	int examRow = 1;
%>
<script type='text/javascript'>
	var examNameRegex = /^(\w+\s?\-?)*(\-?\s*\w*)*$/;
	var CFURegex = /[0-9]{1,2}/;
	
	window.onload = function(){
		controlServlet();
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	// If the servlet sent an error, show it
	function controlServlet() {
		var err = '<%=request.getAttribute("errorCR")%>';
		if(err != "null") {
			showAlert(1, err);
		}
	}
	
	function fillRowWithSuggestion(rowNumber, suggestedCFU, suggestedProcedure) {
		// Get the validated CFU field
		validatedCFUField = document.getElementById("validatedExamCFU" + rowNumber);
		validatedCFUField.value = suggestedCFU;
		
		// Get the validation mode field
		validationModeField = document.getElementById("validatedExamMode" + rowNumber);
		validationModeField.value  = suggestedProcedure;
		
		$("#suggestion" + rowNumber).collapse('hide');
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
</head>
<body>
	<div class="page-wrapper">
		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>
		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
								<br>
									<h1 class="text-left">
										Compila Report
									</h1>
									<h5 class="text-left description">per la richiesta di <%=request.getAttribute("studentName")%></h5>
								</div>
<!-- Editable exams list-->
									<div class="col-lg-10 col-md-10" id="editableExamsList">
											<div id="examsListHeader" class="row">
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"
													id="examNameColumn1">
													<h4 class="text-left field-title">
														<b>Nome esame esterno</b>
													</h4>
												</div>
												<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" id="CFU">
													<h4 class="text-left field-title">
														<b>CFU convalidati</b>
													</h4>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center"
													id="buttons">
													<h4 class="text-left field-title">
														<b>Modalità di convalida</b>
													</h4>
												</div>
											</div>
											<c:forEach items="${validatedExamList}" var="vExam">
												<div id="examsListRow<%=examRow%>" class="row">
	<!-- Exam external name -->
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"
														id="validatedExamName<%=examRow%>">
														<h4 class="list-element">${vExam.examName}</h4>
													</div>
	<!-- Exam external name end-->
	<!-- Exam CFU -->
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" id="CFU">
													
													<span>
														<input class="form-control" type="text" placeholder="es. 9"
														id="validatedExamCFU<%=examRow%>" min="1"
												max="30" minlength="1" maxlength="2" required
												onkeypress="allowNumbersOnly(event)"
												onblur="validateCFU(this)" oninput="maxLengthCheck(this)"
												style="display: inline; width: 30%">
															<script>
															// if a draft of the exam cfu
															// is already present, fill the field with it
																if (${vExam.validatedCFU} >= 0) {
																	document.getElementById("validatedExamCFU<%=examRow%>").defaultValue = ${vExam.validatedCFU};
																	
																}
															</script>
														<h3 class="inline">/</h>
														<h4 class="inline">X</h4>
														</span>
													</div>
	<!-- Exam CFU end -->
	<!-- Exam validation mode -->
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-center"
														id="validationMode">
														
														<textarea class="form-control" id="validatedExamMode<%=examRow%>"
														placeholder="es. L'esame é stato convalidato come PROGRAMMAZIONE 1" rows="3" required>
														<%
																if (true) {
																	// if a draft of the exam validation mode
																	// is already present, fill the field with it
															%>
																${vExam.validationProcedure}
															<%
																}
															%>
														</textarea>
														</div>
	<!-- Exam suggestion -->
														<%
															if (suggList.get(examRow - 1) != null) {
														%>
														<span id="collapsableBtn<%=examRow%>" data-toggle="collapse"
															data-target="#suggestion<%=examRow%>"
															aria-expanded="false"
															aria-controls="suggestion<%=examRow%>">
															<button class="btn btn-primary btn-square" type="button"
																data-toggle="tooltip" data-html="true"
																data-placement="right"
																title="<b><em>Visualizza suggerimento</em></b>">
																<img src="css/svg/help-circle.svg" class="btn-icon">
															</button>
														</span>

													</div>

												<div
													class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse"
													id="suggestion<%=examRow%>">
													<div class="suggestion">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 no-left-margin">
														<h4 id="suggestion<%=examRow%>Body" class="suggestion-body"><%=suggList.get(examRow - 1).getValidationMode()%></h4>
														<br>
														</div>
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 no-left-margin">
														<h5>
															VALIDATO IL
														</h5>
														<h4><b><%=Utils.getFormattedDate(suggList.get(examRow - 1).getValidationDate())%></b></h4>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 no-left-margin">
														<h5>
															CFU RICONOSCIUTI
														</h5>
														<h4><b>
															<%=suggList.get(examRow - 1).getValidatedCFU()%>/<%=suggList.get(examRow - 1).getExternalStudentCFU()%></b></h4>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 no-left-margin">
													<button onclick="fillRowWithSuggestion(<%=examRow%>, <%=suggList.get(examRow - 1).getValidatedCFU()%>, '<%=suggList.get(examRow - 1).getValidationMode()%>')"
													class="btn btn-success">Usa suggerimento</button>
													</div>
													<!-- Adding an extra div in order to make the suggestion resize correctly -->
													<div>&nbsp;</div>
													<div>&nbsp;</div>

												</div>
												</div>
												<%
													} else {
												%>
												<span data-toggle="tooltip" data-html="true"
													data-placement="right"
													title="<b><em>Suggerimento non disponibile</em></b>"
													style = "padding-bottom: 13px;">
													<button class="btn btn-primary btn-square" type="button"
														disabled>
														<img src="css/svg/help-circle.svg" class="btn-icon">
													</button>
												</span>
									</div>
									<%
										}
											examRow++;
									%>
									</c:forEach>
									</div>
<!-- Editable exams list end -->
<!-- Additional notes section -->
							<div class="col-lg-12 col-md-12" id="additionalNotesDiv">
								<h4 class="text-left">
									<b>Note aggiuntive</b>
								</h4>
								<textarea class="form-control" id="additionalNotes"
								placeholder="" rows="5"></textarea>
							</div>
<!-- Additional notes section end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="/partials/footer.jsp" />
	</div>
	<jsp:include page="/partials/includes.jsp" />
</body>
</html>