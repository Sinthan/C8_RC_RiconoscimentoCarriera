<%-- 
  - Author(s): Gianluca Rossi
  - Date: 22/12/2019
  - Description: This page allows the student to compile the exams list related to his request of previous career recognition and to submit it.
  --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String pageName = "createRCRequest2.jsp";
	String pageFolder = "GUIStudentRC";
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/RC/createRCRequest.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Compila richiesta | Inserisci esami</title>
<script type='text/javascript'>
	var rowCount = 1;
	var urlRegex = /^(?:(http(s)?|ftp):\/\/)?([-a-zA-Z0-9@:%._\\+~#=]+(\.[a-zA-Z0-9]{1,6})+)(\/([-a-zA-Z0-9()@:%_\\+.~#?&=]*))*/;
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
	
	// Adds a new row to let the user insert another exam
	function addRow() {
		rowCount++;
		document.getElementById('rowCount').value = rowCount;
		
		// Container <div> where dynamic content will be placed
		var container = document.getElementById("examInsertionRows");

		// Creates the <div> element that contains the new row
		var rowDiv = document.createElement("div");
		rowDiv.className = 'form-row';
		container.appendChild(rowDiv);

		// Creates the <div> element that contains the exam's name input
		var examNameDiv = document.createElement("div");
		examNameDiv.className = 'form-group col-md-4 mb-3';
		rowDiv.appendChild(examNameDiv);
		// Creates the <input> element for the exam's name, sets its type and attributes
		var inputExamName = document.createElement("input");
		inputExamName.type = "text";
		inputExamName.name = "examName" + rowCount;
		inputExamName.className = 'form-control';
		inputExamName.placeholder = 'es. Programmazione 1';
		inputExamName.required = true;
		inputExamName.minLength = 2;
		inputExamName.maxLength = 50;
		inputExamName.pattern = examNameRegex.source;
		inputExamName.onkeypress = allowAlphaNumericOnly;
		inputExamName.title = "Sono ammessi solo caratteri alfanumerici"
			+ " pi� i caratteri \"-\" e \"_\". La lunghezza deve essere compresa tra i 2 e i 50 caratteri.\nes. Programmazione 1";
		examNameDiv.appendChild(inputExamName);

		// Creates the <div> element that contains the exam's CFU input
		var CFUDiv = document.createElement("div");
		CFUDiv.className = 'form-group col-md-1 mb-3';
		rowDiv.appendChild(CFUDiv);
		// Create the <input> element for the exam's CFU, set its type and attributes
		var inputCFU = document.createElement("input");
		inputCFU.type = "number";
		inputCFU.name = "CFU" + rowCount;
		inputCFU.className = 'form-control';
		inputCFU.placeholder = 'es. 9';
		inputCFU.required = true;
		inputCFU.minLength = 1;
		inputCFU.maxLength = 2;
		inputCFU.min = 1;
		inputCFU.max = 30;
		inputCFU.pattern = CFURegex.source;
		inputCFU.onblur = validateCFU.bind(null, inputCFU);
		inputCFU.oninput = maxLengthCheck.bind(null, inputCFU);
		inputCFU.onkeypress = allowNumbersOnly;
		inputCFU.title = "Sono ammessi solo caratteri numerici, la lunghezza deve essere di 1 o 2 cifre.\nes. 9";
		CFUDiv.appendChild(inputCFU);

		// Creates the <div> element that contains the plan of study's link input
		var programLinkDiv = document.createElement("div");
		programLinkDiv.className = 'form-group col-md-5 mb-3';
		rowDiv.appendChild(programLinkDiv);
		// Create the <input> element for the plan of study's link, set its type attributes
		var inputProgramLink = document.createElement("input");
		inputProgramLink.type = "text";
		inputProgramLink.name = "programLink" + rowCount;
		inputProgramLink.className = 'form-control';
		inputProgramLink.placeholder = 'es. unisa.it/programma_esame.html';
		inputProgramLink.required = true;
		inputProgramLink.minLength = 4;
		inputProgramLink.maxLength = 256;
		inputProgramLink.pattern = urlRegex.source;
		inputProgramLink.title = "Sono ammessi solo url, la lunghezza deve essere compresa tra i 4 e i 256 caratteri.\nes. unisa.it/programma_esame.html";
		programLinkDiv.appendChild(inputProgramLink);

		// Append a line break 
		container.appendChild(document.createElement("br"));
	}

	function deleteRow() {
		// Container <div> where dynamic content will be placed
		var container = document.getElementById("examInsertionRows");
		if (rowCount > 1) {
			for (i = 2; i > 0; i--) { // Removes the row and the br
				container.removeChild(container.lastChild);
			}
			rowCount--;
			document.getElementById('rowCount').value = rowCount;
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
		if(this.value < 30) {
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

		<%
			HttpSession sess = request.getSession();
			sess.setAttribute("flag", 3);
		%>

		<div class="sidebar-page-container basePage createRequestRCPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="panel">
									<br>
									<h1 class="text-left">Inserisci gli esami da convalidare</h1>
								</div>

								<form class="needs-validation" id="createRequestRC2"
									name="createRequestRC2" method="post"
									action="StudentManagement">
									<!-- 										The next field lets the servlet know how many exams were added -->
									<input type="hidden" name="rowCount" id="rowCount" value="1" />

									<div class="form-row inputDiv" id=examInsertionRows>
										<div class="form-group col-md-4 mb-3">
											<label for="examName1">Nome esame</label> <input type="text"
												title="Sono ammessi solo caratteri alfanumerici pi� i caratteri - e _
													La lunghezza deve essere compresa tra i 2 e i 50 caratteri.
													es. Programmazione 1"
												class="form-control" name="examName1"
												placeholder="es. Programmazione 1" minlength="2"
												maxlength="50" required pattern="^(\w+\s?\-?)*(\-?\s*\w*)*$"
												onkeypress="allowAlphaNumericOnly(event)">
										</div>

										<div class="form-group col-md-1 mb-3">
											<label for="CFU1">CFU</label> <input type="number"
												title="Sono ammessi solo caratteri numerici, la lunghezza deve essere di 1 o 2 cifre.
													es. 9"
												class="form-control" name="CFU1" placeholder="es. 9" min="1"
												max="30" minlength="1" maxlength="2" required
												onkeypress="allowNumbersOnly(event)"
												onblur="validateCFU(this)" oninput="maxLengthCheck(this)">
										</div>

										<div class="form-group col-md-5 mb-3">
											<label for="programLink1">Link al programma d'esame</label> <input
												type="text" class="form-control" name="programLink1"
												title="Sono ammessi solo url, la lunghezza deve essere compresa tra i 4 e i 256 caratteri.
													es. unisa.it/programma_esame.html"
												placeholder="es. unisa.it/programma_esame.html"
												minlength="4" maxlength="256"
												pattern="^(?:(http(s)?|ftp):\/\/)?([-a-zA-Z0-9@:%._\\+~#=]+(\.[a-zA-Z0-9]{1,6})+)(\/([-a-zA-Z0-9()@:%_\\+.~#?&=]*))*"
												required>
										</div>

										<br>
									</div>

									<div class="examBtnDiv">
										<button type="button" class="btn btn-primary" id="btnAdd"
											onclick="addRow() ">Aggiungi esame</button>
										<button type="button" class="btn btn-danger" id="btnRemove"
											onclick="deleteRow()" data-toggle="tooltip" data-html="true"
											data-placement="right"
											title="<b><em>I dati inseriti nell'ultima riga verranno cancellati</em></b>">
											Rimuovi ultimo esame</button>
									</div>

									<div class="form-group"
										style="position: fixed; padding: 32px; bottom: 0; right: 0;">
										<button type="submit" class="finish">
											<span class="circle"> <span class="icon arrow"></span>
											</span> <span class="button-text">Sottometti richiesta</span>
										</button>
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
	<%-- 	<script src="<%= request.getContextPath() %>/jsRC/createRCRequest2.js"></script> --%>
</body>
</html>