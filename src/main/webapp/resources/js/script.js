
function redirect(){
	if(document.forms['header']['header:tipo'][0].checked == true){

		document.location.href = 'basic.xhtml'
	}else if(document.forms['header']['header:tipo'][1].checked == true){
		document.location.href = 'scientific.xhtml'
	}
}

window.onload = function() {

	setInterval(function() { document.getElementById("j_idt17:refresh").click(); }, 5000);

}



