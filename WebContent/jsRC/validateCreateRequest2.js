/**
 * 
 */

function formValidation() {
	for (i = 0; i < rowCount; i++) {
		// Getting parameters
		var examName = document.getElementById('examName' + rowCount);
		var CFU = document.getElementById('CFU' + rowCount);
		var programLink = document.getElementById('programLink' + rowCount);

		// DEBUG
		console.log(rowCount + examName + CFU + programLink);
		

		if (isExamName(examName)) {
			if (isNumeric(CFU)) {
				if (isLink(programLink)) {}
			}
		}
	}
}

function isNumeric(element) { 
	var numbers = /^[0-9]+$/;
	if(element.value.match(numbers)) {
		return true;
	} else {
		alert(element.id + " puo contenere solo numeri");
		element.focus();
		//element.className += " is-invalid";
		return false;
	}
}

function validateNumeric() { 
	var numbers = /^[0-9]+$/;
	if(this.value.match(numbers)) {
		return true;
	} else {
		alert(this.id + " puo contenere solo numeri");
		this.focus();
		//element.className += " is-invalid";
		return false;
	}
}

function isLink(element) {
	var urlRegex =/(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
	if(element.value.match(urlRegex)) {
		return true;
	} else {
		alert(element.id + " deve essere un link");
		element.focus();
		//element.className += " is-invalid";
		return false;
	}
}

function validateLink() {
	var urlRegex =/(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig;
	if(this.value.match(urlRegex)) {
		return true;
	} else {
		alert(this.id + " deve essere un link");
		this.focus();
		//element.className += " is-invalid";
		return false;
	}
}

function isExamName(element, e) {
	var alphaNumeric =/^[0-9a-zA-Z]+$/;
	if(element.value.match(alphaNumeric)) {
		return true;
	} else {
		e.preventDefault();
		//alert(element.name + " deve essere un nome valido");
		element.focus();
		//element.className += " is-invalid";
		return false;
	}
}

function validateExamName() {
	var alphaNumeric =/^[0-9a-zA-Z]+$/;
	if(this.value.match(alphaNumeric)) {
		return true;
	} else {
		alert(this.name + " deve essere un nome valido");
		this.focus();
		//element.className += " is-invalid";
		return false;
	}
}