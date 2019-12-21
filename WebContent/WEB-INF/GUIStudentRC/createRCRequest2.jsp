<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String pageName = "createRCRequest2.jsp";
	String pageFolder = "";
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/createRCRequest.css">
<jsp:include page="/partials/head.jsp" />
<meta charset="ISO-8859-1">
<title>Compila richiesta | Inserisci esami</title>
<script type='text/javascript'>
	var rowCount = 1;
	
	window.onload = function(){
		controlServlet();
	};

	function controlServlet(){
		var err = '<%= request.getAttribute("errorCR2") %>';
		if( err.toString() != "null"){
			showAlert(1, err.toString());
		}
	}

	function addRow() {
		rowCount++;
		document.getElementById('rowCount').value = rowCount;
		
		// Container <div> where dynamic content will be placed
		var container = document.getElementById("examInsertionRows");

		// Create the <div> element that contains the new row
		var rowDiv = document.createElement("div");
		rowDiv.className = 'form-row';
		container.appendChild(rowDiv);

		// Create the <input> element for the exam's name, set its type and name attributes
		var examNameDiv = document.createElement("div");
		examNameDiv.className = 'form-group col-md-4 mb-3';
		rowDiv.appendChild(examNameDiv);

		var inputExamName = document.createElement("input");
		inputExamName.type = "text";
		inputExamName.name = "examName" + rowCount;
		inputExamName.className = 'form-control';
		inputExamName.placeholder = 'es. Programmazione 1';
		inputExamName.required = true;
		inputExamName.onkeypress = allowAlphaNumericOnly;
		examNameDiv.appendChild(inputExamName);

		// Create the <input> element for the exam's CFU, set its type and name attributes
		var CFUDiv = document.createElement("div");
		CFUDiv.className = 'form-group col-md-1 mb-3';
		rowDiv.appendChild(CFUDiv);

		var inputCFU = document.createElement("input");
		inputCFU.type = "number";
		inputCFU.name = "CFU" + rowCount;
		inputCFU.className = 'form-control';
		inputCFU.placeholder = 'es. 9';
		inputCFU.required = true;
		inputCFU.min = 1;
		inputCFU.max = 30;
		inputCFU.onblur = validateCFU.bind(null, inputCFU);
		inputCFU.oninput = maxLengthCheck.bind(null, inputCFU);
		inputCFU.onkeypress = allowNumbersOnly;
		CFUDiv.appendChild(inputCFU);

		// Create the <input> element for the program's link, set its type and name attributes
		var programLinkDiv = document.createElement("div");
		programLinkDiv.className = 'form-group col-md-5 mb-3';
		rowDiv.appendChild(programLinkDiv);

		var inputProgramLink = document.createElement("input");
		inputProgramLink.type = "text";
		inputProgramLink.name = "programLink" + rowCount;
		inputProgramLink.className = 'form-control';
		inputProgramLink.placeholder = 'es. www.unisa.it/programmaEsame.html';
		inputProgramLink.required = true;
		//inputProgramLink.onChange = validateLink;
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
			alert("Il valore massimo dei CFU é 30");
			this.focus();
			//element.className += " is-invalid";
			return false;
		}
	}

	function allowAlphaNumericOnly(e) {
		var keyCode = (e.which) ? e.which : e.keyCode;
		
		if (!(keyCode == 32) && // space
			    !(keyCode > 47 && keyCode < 58) && // numeric (0-9)
			    !(keyCode > 64 && keyCode < 91) && // upper alpha (A-Z)
			    !(keyCode > 96 && keyCode < 123)) { // lower alpha (a-z)
			e.preventDefault();
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
			sess.setAttribute("flag",3);
		%>
		
		<div class="sidebar-page-container basePage createRequestRCPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel" display:inline;">
										<h1 class="text-left">Inserisci esami</h1>
									</div>

									<form id="createRequestRC2" name="createRequestRC2" method="post"
										action="StudentManagement"
										>

										<input type="hidden" name="rowCount" id="rowCount" value="1"/>

										<div class="form-row" id=examInsertionRows>
											<div class="form-group col-md-4 mb-3">
												<label for="examName1">Nome esame</label> <input type="text"
													class="form-control" name="examName1" id="examName1"
													placeholder="es. Programmazione 1" minlength="2"
													maxlength="50" required pattern="([A-z0-9À-ž\s]){2,50}"
													onkeypress = "allowAlphaNumericOnly(event)">
											</div>

											<div class="form-group col-md-1 mb-3">
												<label for="CFU1">CFU</label> <input type="number"
													class="form-control" name="CFU1" id="CFU1" placeholder="es. 9"
													min="1" max="30" minlength="1" maxlength="2" required
													onkeypress="allowNumbersOnly(event)" onblur="validateCFU(this)" oninput="maxLengthCheck(this)">
											</div>

											<div class="form-group col-md-5 mb-3">
												<label for="programLink1">Link al programma d'esame</label> <input
													type="text" class="form-control" name="programLink1" id="programLink1"
													placeholder="es. www.unisa.it/programmaEsame.html"
													pattern="^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$"
													minlength="4" maxlength="256" required>
											</div>

											<br>
										</div>

										<div class="panel" style="margin-left: 15px; float: left;">
											<button type="button" class="btn btn-primary" id="btnAdd"
												onclick="addRow() ">Aggiungi esame</button>
											<button type="button" class="btn btn-danger" id="btnAdd"
												onclick="deleteRow()">Rimuovi ultimo esame</button>
										</div>


										<div class="form-group"
											style="position: fixed; padding: 32px; bottom: 0; right: 0;">
											<p class="text-center" style="float: right;">
												Sottometti richiesta&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="submit" class="btn btn-primary btn-submit"
													style="border-radius: 20px;">&#10004;</button>
											</p>
										</div>
									</form>
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
	<%-- 	<script src="<%= request.getContextPath() %>/jsRC/createRCRequest2.js"></script> --%>
</body>
</html>