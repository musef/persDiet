
function revisaIndex () {
	
	$("#formIndex\\:logIndex").css('background-color', 'white');
	$("#formIndex\\:passIndex").css('background-color', 'white');
	
	var $log=$("#formIndex\\:logIndex").val();
	var $pass=$("#formIndex\\:passIndex").val();
	
	if ($log.length<6 || $log.length>15) {
		alert ('La longitud del login es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		$("#formIndex\\:logIndex").css('background-color', 'red');
		return false;
	}

	if ($pass.length<6 || $pass.length>15) {
		alert ('La longitud de la contraseña es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		$("#formIndex\\:passIndex").css('background-color', 'red');
		return false;
	}
	
	return true;
}



function revisaAlta() {
	
	if (!confirm('¿Confirma los datos del nuevo usuario?')){
		return false;
	}
	
	$('#formNew\\:idlog').css('background-color', 'WHITE');
	$('#formNew\\:idpas').css('background-color', 'WHITE');
	$('#formNew\\:idnam').css('background-color', 'WHITE');
	$('#formNew\\:idwei').css('background-color', 'WHITE');
	$('#formNew\\:idhei').css('background-color', 'WHITE');
	$('#formNew\\:idage').css('background-color', 'WHITE');
	
	var $login=$('#formNew\\:idlog').val();	
	if ($login.length<6 || $login.length>15) {
		$('#formNew\\:idlog').css('background-color', 'RED');
		alert ('La longitud del login es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		return false;
	} else {
		$('#formNew\\:idlog').css('background-color', 'WHITE');
	}
	
	var $pass=$('#formNew\\:idpas').val();	
	if ($pass.length<6 || $pass.length>15) {
		$('#formNew\\:idpas').css('background-color', 'RED');
		alert ('La longitud de la contraseña es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		return false;
	} else {
		$('#formNew\\:idpas').css('background-color', 'WHITE');
	}
	
	var $name=$('#formNew\\:idnam').val();
	if ($name.length<3 || $name.length>50) {
		$('#formNew\\:idnam').css('background-color', 'RED');
		alert ('La longitud del nombre es inadecuada:\nDebe tener entre 3 y 50 caracteres');
		return false;
	} else {
		$('#formNew\\:idnam').css('background-color', 'WHITE');
	}
	
	var $wei=$('#formNew\\:idwei').val();
	if ($wei<25 || $wei>250) {
		$('#formNew\\:idwei').css('background-color', 'RED');
		alert ('Peso inadecuado o erróneo:\nDebe introducir un valor entre 25 y 250');
		return false;
	} else {
		$('#formNew\\:idwei').css('background-color', 'WHITE');
	}	
	
	var $hei=$('#formNew\\:idhei').val();
	if ($hei<50 || $hei>250) {
		$('#formNew\\:idhei').css('background-color', 'RED');
		alert ('Altura inadecuada o errónea:\nDebe introducir un valor entre 50 y 250');
		return false;
	} else {
		$('#formNew\\:idhei').css('background-color', 'WHITE');
	}		
	
	var $age=$('#formNew\\:idage').val();		
	if ($age<16 || $age>100) {
		$('#formNew\\:idage').css('background-color', 'RED');
		alert ('Edad inadecuada o errónea:\nDebe introducir un valor entre 16 y 100');
		return false;
	} else {
		$('#formNew\\:idage').css('background-color', 'WHITE');
	}

	return true;
	
}


function revisaModificacion() {
	
	if (!confirm('¿Desea grabar las modificaciones?')){
		return false;
	}
	
	$('#formMod\\:idlog').css('background-color', 'WHITE');
	$('#formMod\\:idpas').css('background-color', 'WHITE');
	$('#formMod\\:idnam').css('background-color', 'WHITE');
	$('#formMod\\:idwei').css('background-color', 'WHITE');
	$('#formMod\\:idhei').css('background-color', 'WHITE');
	$('#formMod\\:idage').css('background-color', 'WHITE');
	
	var $login=$('#formMod\\:idlog').val();	
	if ($login.length<6 || $login.length>15) {
		$('#formMod\\:idlog').css('background-color', 'RED');
		alert ('La longitud del login es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		return false;
	} else {
		$('#formMod\\:idlog').css('background-color', 'WHITE');
	}
	
	var $pass=$('#formMod\\:idpas').val();	
	if ($pass.length<6 || $pass.length>15) {
		$('#formMod\\:idpas').css('background-color', 'RED');
		alert ('La longitud de la contraseña es inadecuada:\nDebe tener entre 6 y 15 caracteres');
		return false;
	} else {
		$('#formMod\\:idpas').css('background-color', 'WHITE');
	}
	
	var $name=$('#formMod\\:idnam').val();
	if ($name.length<3 || $name.length>50) {
		$('#formMod\\:idnam').css('background-color', 'RED');
		alert ('La longitud del nombre es inadecuada:\nDebe tener entre 3 y 50 caracteres');
		return false;
	} else {
		$('#formMod\\:idnam').css('background-color', 'WHITE');
	}
	
	var $wei=$('#formMod\\:idwei').val();
	if ($wei<25 || $wei>250) {
		$('#formMod\\:idwei').css('background-color', 'RED');
		alert ('Peso inadecuado o erróneo:\nDebe introducir un valor entre 25 y 250');
		return false;
	} else {
		$('#formMod\\:idwei').css('background-color', 'WHITE');
	}	
	
	var $hei=$('#formMod\\:idhei').val();
	if ($hei<50 || $hei>250) {
		$('#formMod\\:idhei').css('background-color', 'RED');
		alert ('Altura inadecuada o errónea:\nDebe introducir un valor entre 50 y 250');
		return false;
	} else {
		$('#formMod\\:idhei').css('background-color', 'WHITE');
	}		
	
	var $age=$('#formMod\\:idage').val();		
	if ($age<16 || $age>100) {
		$('#formMod\\:idage').css('background-color', 'RED');
		alert ('Edad inadecuada o errónea:\nDebe introducir un valor entre 16 y 100');
		return false;
	} else {
		$('#formMod\\:idage').css('background-color', 'WHITE');
	}

	return true;
	
}


function limpia() {
	$('#loginI').css('background-color', '#FFFFFF');
	$('#passI').css('background-color', '#FFFFFF');
	$('#nameI').css('background-color', '#FFFFFF');
	$('#addressI').css('background-color', '#FFFFFF');
	$('#cityI').css('background-color', '#FFFFFF');
	$('#cpostI').css('background-color', '#FFFFFF');
	$('#phoneI').css('background-color', '#FFFFFF');
	$('#emailI').css('background-color', '#FFFFFF');
}