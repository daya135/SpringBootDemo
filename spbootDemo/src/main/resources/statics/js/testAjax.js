function textAjax() {
	var url = document.getElementById("url").value;
	var method = document.getElementById("method").value;
	var enctype = document.getElementById("enctype").value;
	var userid = document.getElementById("userid");
	var userName = document.getElementById("userName");
	
	realAjax(url, method, enctype, bodyContent);
}

function realAjax(url, method, enctype, bodyContent) {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	  if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
	    document.getElementById("ajaxmsg").innerHTML = xmlhttp.responseText;
	  } else {
		document.getElementById("ajaxmsg").innerHTML = xmlhttp.status;
	  }
	}
	xmlhttp.open(method, url, true);
	xmlhttp.setRequestHeader("Content-type", enctype);
	if (bodyContent != undefined && bodyContent != null && bodyContent.length > 0) {
		xmlhttp.send(bodyContent);
	} else {
		xmlhttp.send();
	}
}
        