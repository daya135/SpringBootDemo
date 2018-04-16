function textAjax() {
	var url = document.getElementById("url").value;
	var method = document.getElementById("method").value;
	var enctype = document.getElementById("enctype").value;
	var inputs = document.getElementById("userform").childNodes;
	var content = "";
	for (var i = 0; i < inputs.length; i++) {
		if(inputs[i].nodeName == "INPUT" && inputs[i].getAttribute("type") == "text") {
			var name = inputs[i].getAttribute("name");
			var value = inputs[i].value;
			if (value == undefined || value == null || value == "") {
				alert("表单项不能为空！");
				return false;
			}
			if (enctype == "application/x-www-form-urlencoded") {
				content += '&';
				content += encodeURI(name);
				content += '=';
				content += encodeURI(value);
			} else if (enctype == "application/json") {
				content += '"';
				content += name;
				content += '"："';
				content += value;
				content += '",';
			}
		}
	}
	if (enctype == "application/x-www-form-urlencoded") {
		if (method == "GET") {
			content = content.substring(1, content.length);
			url = url + "?" + content; 
		} else if (method == "POST") {
			content = content.substring(1, content.length);
		}
	} else if (enctype == "application/json") {
		content = content.substring(0, content.length -1);
		content = "{" + content + "}";
	}
	console.log("url: " + url);
	console.log("method: " + method);
	console.log("enctype: " + enctype);
	console.log("content: " + content);
	
	realAjax(url, method, enctype, content);
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
        