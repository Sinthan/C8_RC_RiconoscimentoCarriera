/**
 * 
 */

	function formValidation(){
		var fileInput1 = document.getElementByID("file1");
		var fileInput2 = document.getElementByID("file2");
		var filePath1 = fileInput1.value("file1");
		var filePath2 = fileInput2.value("file2");
		var allowedExtenzions = /(\.pdf)$/i;
		if(!allowedExtensions.exec(filePath1) || !allowedExtensions.exec(filePath1) ){
			alert("cass");
		}
	}