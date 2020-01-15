<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, model.Exam, model.ValidatedExam, model.ValidatedExamDAO, model.Suggestion, controller.Utils"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/partials/head.jsp" />
<link rel="stylesheet" href="css/RC/createReport.css">
<meta charset="ISO-8859-1">
<title>Compila Report</title>
<%
	String pageName = "createReport.jsp";
	String pageFolder = "GUIAdminRC";
	HttpSession sess = request.getSession();
	ArrayList<Suggestion> suggList = (ArrayList<Suggestion>) request.getAttribute("suggList");
	ArrayList<String> suggOverwrite = (ArrayList<String>) request.getAttribute("suggOverwrite");
	ArrayList<Exam> examsList = (ArrayList<Exam>) request.getAttribute("examsList");
	int examRow = 1;
%>
<script type='text/javascript'>
	var examNameRegex = /^(\w+\s?\-?)*(\-?\s*\w*)*$/;
	var CFURegex = /[0-9]{1,2}/;
	var suggestionActiveBG = "aliceblue";
	//var suggestionActiveBG = "#ddf0fe";
	var suggestionActiveBorder = '#93CAE6';
	//var suggestionActiveBorder = '#acd6ec';
	
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
		
		var succ = '<%=request.getAttribute("successCR")%>';
		if(succ != "null") {
			showAlert(0, succ);
		}
	}
	
	function fillRowWithSuggestion(rowNumber, suggestedCFU, suggestedProcedure) {
		// Get the validated CFU field
		validatedCFUField = document.getElementById("validatedExamCFU" + rowNumber);
		validatedCFUField.value = suggestedCFU;
		validatedCFUField.style.backgroundColor = suggestionActiveBG;
		validatedCFUField.style.borderColor = suggestionActiveBorder;
		
		// Get the validation mode field
		validationModeField = document.getElementById("validatedExamMode" + rowNumber);
		validationModeField.value  = suggestedProcedure;
		validationModeField.style.backgroundColor = suggestionActiveBG;
		validationModeField.style.borderColor = suggestionActiveBorder;
		
		// Get the overwrite suggestion
		suggOverwriteCheck = document.getElementById("suggOverwrite" + rowNumber);
		suggOverwriteCheck.checked = false;
		suggOverwriteCheck.disabled = true;
		
		// Get the applied suggestion label
		appliedSuggLabel = document.getElementById("appliedSuggLabel" + rowNumber);
		appliedSuggLabel.style.visibility = "visible";
		
		// Hiding the suggestion
		$("#suggestion" + rowNumber).collapse('hide');
	}
	
	function checkSuggestionMatchForRow(rowNumber, suggestedCFU, suggestedProcedure) {
		// Get the validated CFU field
		validatedCFUField = document.getElementById("validatedExamCFU" + rowNumber);
		// Get the validation mode field
		validationModeField = document.getElementById("validatedExamMode" + rowNumber);
		// Get the overwrite suggestion
		suggOverwriteCheck = document.getElementById("suggOverwrite" + rowNumber);
		// Get the applied suggestion label
		appliedSuggLabel = document.getElementById("appliedSuggLabel" + rowNumber);
		
		if (validatedCFUField.value == suggestedCFU && validationModeField.value == suggestedProcedure) {
			validatedCFUField.style.backgroundColor = suggestionActiveBG;
			validatedCFUField.style.borderColor = suggestionActiveBorder;
			validationModeField.style.backgroundColor = suggestionActiveBG;
			validationModeField.style.borderColor = suggestionActiveBorder;
			appliedSuggLabel.style.visibility = "visible";
			suggOverwriteCheck.checked = false;
			suggOverwriteCheck.disabled = true;
		} else {
			validationModeField.style.backgroundColor = "#ffffff";
			validationModeField.style.borderColor = "";
			validatedCFUField.style.backgroundColor = "#ffffff";
			validatedCFUField.style.borderColor = "";
			appliedSuggLabel.style.visibility = "hidden";
			suggOverwriteCheck.disabled = false;
		}
	}
	
	function allowNumbersOnly(e, object) {
	    if (object.value.length > object.max.length)
	      object.value = object.value.slice(0, object.max.length)
	    var code = (e.which) ? e.which : e.keyCode;
	    if (code > 31 && (code < 48 || code > 57)) {
	        e.preventDefault();
	        showAlert(1, "Sono consentiti solo caratteri numerici.");
	    }
	}
	
	function validateCFU(object, maxValue) {
		if(this.value <= maxValue) {
			return true;
		} else if(object.value > maxValue) {
			object.value = maxValue;
			showAlert(1, "Il valore massimo dei CFU per questo esame &#232; " + maxValue);
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
			<jsp:param name="pageName" value="<%=pageName%>"/>
			<jsp:param name="pageFolder" value="<%=pageFolder%>"/>
		</jsp:include>
		<div class="sidebar-page-container basePage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
									<br>
									<h1 class="text-left">Compila il report</h1>
									<h4 class="text-left description">
										<em>per la richiesta di <%=request.getAttribute("studentName")%></em>
									</h4>
								</div>
<!-- Editable exams list -->
								<div class="col-lg-11 col-md-11" id="editableExamsList">
									<div id="examsListHeader">
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
										<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-center"
											id="buttons">
											<h4 class="text-left field-title">
												<b>Modalità di convalida</b>
											</h4>
										</div>
										<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"
											id="buttons">
											<h4 class="text-left field-title" style="padding-left: 49px;">
												<b>Opzioni suggerimento</b>
											</h4>
										</div>
									</div>
									
								<form action="./ReportManagementServlet">
									<c:forEach items="${validatedExamList}" var="vExam">
										<div id="examsListRow<%=examRow%>" class="examRow">
	<!-- Exam external name -->
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"
												id="validatedExamName<%=examRow%>" name="validatedExamName<%=examRow%>">
												<h4 class="list-element">${vExam.examName}</h4>
											</div>
											
								<input type="hidden" name="validatedExamName<%=examRow%>" id="validatedExamName<%=examRow%>" value="${vExam.examName}"/>
	<!-- Exam external name end -->
	<!-- Exam CFU -->
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" id="CFU">

												<span> <input class="form-control cfuField" type="text"
													placeholder="es. 9" id="validatedExamCFU<%=examRow%>"
													name = "validatedExamCFU<%=examRow%>"
													min="1" max="30" minlength="1" maxlength="2" required
													onkeypress="allowNumbersOnly(event, this)"
													onblur="validateCFU(this, <%=examsList.get(examRow - 1).getCFU()%>)">
															<script type="text/javascript">
															// if a draft of the exam cfu
															// is already present, fill the field with it
																if ('${vExam.validatedCFU}' >= 0) {
																	document.getElementById("validatedExamCFU<%=examRow%>").value = '${vExam.validatedCFU}';
																}
															</script>
													<h3 class="inline">
														/
														</h3>
														<h4 class="inline">
														<%=examsList.get(examRow - 1).getCFU()%>
														</h4>
										<input type="hidden" name="externalExamCFU<%=examRow%>" id="externalExamCFU<%=examRow%>" value="<%=examsList.get(examRow - 1).getCFU()%>"/>
														</span>
											</div>
	<!-- Exam CFU end -->
	<!-- Exam validation mode -->
											<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-center validationModeDiv"
												id="validationMode">

												<textarea class="form-control validationModeTxtArea"
													id="validatedExamMode<%=examRow%>"
													name = "validatedExamMode<%=examRow%>"
													placeholder="es. L'esame é stato convalidato come PROGRAMMAZIONE 1"
													rows="3" required></textarea>
														<script type="text/javascript">
																if ("${vExam.validationProcedure}".length != 0) {
																	// if a draft of the exam validation mode
																	// is already present, fill the field with it
																	document.getElementById("validatedExamMode<%=examRow%>").value = "${vExam.validationProcedure}";
																}
														</script>
														<div id="appliedSuggLabel<%=examRow%>" class="appliedSuggLabel">
													        <h5>Suggerimento applicato!</h5>
													      </div>
											</div>
	<!-- Exam validation mode end-->
	<!-- Exam suggestion -->
										
											<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 suggestionControlsDiv">
											<%
															if (suggList.size() >= examRow && suggList.get(examRow - 1) != null) {
														%>
														
											<span id="collapsableBtn<%=examRow%>" data-toggle="collapse"
												data-target="#suggestion<%=examRow%>" aria-expanded="false"
												aria-controls="suggestion<%=examRow%>">
												<button class="btn btn-primary btn-square" type="button"
													data-toggle="tooltip" data-html="true"
													data-placement="top"
													title="<b><em>Visualizza<br>suggerimento</em></b>">
													<img src="css/svg/help-circle.svg" class="btn-icon">
												</button>
											</span>
	<!-- Update suggestion checkbox -->
											<div class="custom-control custom-checkbox inline">
											  <input type="checkbox" class="custom-control-input suggestionOverwriteCheckbox" id="suggOverwrite<%=examRow%>" name = "suggOverwrite<%=examRow%>">
											  <label for="suggOverwrite<%=examRow%>"><h5>Aggiorna il<br>suggerimento</h5></label>
											</div>
											<%
											
														if (suggOverwrite.size() >= examRow && suggOverwrite.get(examRow - 1) != null) {
														%>
														<script type="text/javascript">
	 														document.getElementById("suggOverwrite<%=examRow%>").checked = true;
														</script>
											<%
															}
														%>
	<!-- Update suggestion checkbox end -->
										</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse"
											id="suggestion<%=examRow%>">
											<div class="suggestion">
												<div
													class="col-lg-12 col-md-12 col-sm-12 col-xs-12 no-left-margin">
													<h4 id="suggestion<%=examRow%>Body" class="suggestion-body"><%=suggList.get(examRow - 1).getValidationMode()%></h4>
													<br>
												</div>
												<div
													class="col-lg-3 col-md-3 col-sm-3 col-xs-3 no-left-margin">
													<h5>VALIDATO IL</h5>
													<h4>
														<b><%=Utils.getFormattedDate(suggList.get(examRow - 1).getValidationDate())%></b>
													</h4>
												</div>
												<div
													class="col-lg-4 col-md-4 col-sm-4 col-xs-4 no-left-margin">
													<h5>CFU RICONOSCIUTI</h5>
													<h4>
														<b> <%=suggList.get(examRow - 1).getValidatedCFU()%>/<%=suggList.get(examRow - 1).getExternalStudentCFU()%></b>
													</h4>
												</div>
												<div
													class="col-lg-3 col-md-3 col-sm-3 col-xs-3 no-left-margin">
													<button onclick = "fillRowWithSuggestion(<%=examRow%>, "<%=suggList.get(examRow - 1).getValidatedCFU()%>", "<%=suggList.get(examRow - 1).getValidationMode()%>")"
														class="btn btn-success" type="button">Usa suggerimento</button>
												</div>
												<!-- Adding an extra div in order to make the suggestion resize correctly -->
												<div>&nbsp;</div>
												<div>&nbsp;</div>

											</div>
										</div>
										<script type="text/javascript">
	 														document.getElementById("validatedExamCFU<%=examRow%>").addEventListener("input", checkSuggestionMatchForRow.bind(null, <%=examRow%>, "<%=suggList.get(examRow - 1).getValidatedCFU()%>", "<%=suggList.get(examRow - 1).getValidationMode()%>"));
															document.getElementById("validatedExamMode<%=examRow%>").addEventListener("input", checkSuggestionMatchForRow.bind(null, <%=examRow%>, "<%=suggList.get(examRow - 1).getValidatedCFU()%>", "<%=suggList.get(examRow - 1).getValidationMode()%>"));
															checkSuggestionMatchForRow(<%=examRow%>, "<%=suggList.get(examRow - 1).getValidatedCFU()%>", "<%=suggList.get(examRow - 1).getValidationMode()%>");
										</script>
										<%
													} else {
												%>
										<span data-toggle="tooltip" data-html="true"
											data-placement="right"
											title="<b><em>Suggerimento<br>non disponibile</em></b>"
											style="padding-top: 13px;">
											<button class="btn btn-primary btn-square" type="button"
												disabled>
												<img src="css/svg/help-circle.svg" class="btn-icon">
											</button>
										</span>
									</div>
								</div>
	<!-- Exam suggestion end -->
								<%
										}
											examRow++;
									%>
									
									
								</c:forEach>
	<!-- Extra fields that the servlet needs -->
								<input type="hidden" name="rowCount" id="rowCount" value="<%=examRow%>"/>
								<input type="hidden" name="idRequestRC" id="idRequestRC" value="<%=request.getAttribute("idRequestRC")%>"/>
							</div>
<!-- Editable exams list end -->
<!-- Additional notes section -->
							<div class="col-lg-10 col-md-10" id="additionalNotesDiv">
								<h4 class="text-left" style="margin-top:40px">
									<b>Note aggiuntive</b>
								</h4>
								<textarea class="form-control list-element notes" id="additionalNotes"  name="additionalNotes"
									placeholder="" rows="5"></textarea>
								<script type="text/javascript">
																if ("<%=request.getAttribute("note")%>" != "null") {
																	// if a draft of the report note
																	// is already present, fill the field with it
																	document.getElementById("additionalNotes").value = "<%=request.getAttribute("note")%>";
																}
								</script>
							</div>
<!-- Additional notes section end -->

<!-- Report buttons -->

							<div class="col-lg-12 col-md-12 reportButtons">
								
								
									<button id="saveDraftBtn" type="submit" class="saveDraft" formnovalidate>
										<span class="circle"> <span class="icon arrow"></span>
										</span> <span class="button-text">Salva bozza</span>
									</button>

									<button id="closeRCRequestBtn" name="closeRCRequestBtn"type="submit"
										class="closeRCRequest">
										<span class="circle"> <span class="icon arrow"></span>
										</span> <span class="button-text">Chiudi la richiesta e genera
											il report</span>
									</button>
								</form>
							</div>
<!-- Report buttons end -->

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