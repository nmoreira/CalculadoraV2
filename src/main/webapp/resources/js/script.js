/**
 * 
 */

function redirect(){
	if(document.forms['header']['header:tipo'][0].checked == true){

		document.location.href = 'basic.xhtml'
	}else if(document.forms['header']['header:tipo'][1].checked == true){
		document.location.href = 'scientific.xhtml'
	}
}

window.onload = function() {

	setInterval(function() { document.getElementById("j_idt20:refresh").click(); }, 5000);

}



/*setInterval("nextMessage()", 200); //update the chart every 200 ms               

function updateMessages(xhr, status, args) {
    if(!args.ok) return;
    $('#chat').append('<div class="msg">[' +args.dateSent+ '] <strong>'+args.user+'</strong>: '+args.text+'</div>');
}*/