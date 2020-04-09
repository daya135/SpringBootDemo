function testGetAndPostController() {
	var url = document.getElementById("url").value;
	var method = "";
	var contentType = "";
	var content = "";
	
	var id = document.getElementById("userid").value; 
	var name = document.getElementById("username").value;
	var age = document.getElementById("userage").value;
	var birth = document.getElementById("userbirth").value;
	var userObj = {
		id:Number(id),	//字段一定要转化为对应类型（符合json串标准），否则反序列化为bean时出现bug，比如name字段包含全部内容
		userName:name,
		age:Number(age),
		birth:birth
	}

	switch (url) {
		case 'get_url':
			method = "GET";
			contentType = "application/x-www-form-urlencoded";
			url = url + "?" + getContent(contentType, userObj);
			break;
		case 'get_urlencoded_body':
			method = "GET";
			contentType = "application/x-www-form-urlencoded";
			content = getContent(contentType, userObj);
			break;
		case 'get_json_body':
			method = "GET";
			contentType = "application/json";
			content = getContent(contentType, userObj);
			break;	
		case 'post_url':
			method = "POST";
			contentType = "application/x-www-form-urlencoded";
			url = url + "?" + getContent(contentType, userObj);
			break;
		case 'post_urlencoded_body':
			method = "POST";
			contentType = "application/x-www-form-urlencoded";
			content = getContent(contentType, userObj);
			break;
		case 'post_json_body':
			method = "POST";
			contentType = "application/json";
			content = getContent(contentType, userObj);
			break;
	}
	url = 'getandpost/' + url;
	console.log("url: " + url);
	console.log("method: " + method);
	console.log("contentType: " + contentType);
	console.log("content: " + content);
	
	realAjax(url, method, contentType, content, "GetAndPostMsg");
}

//测试参数提交
function testParmController(){
	var id = document.getElementById("userid_parm").value; 
	var name = document.getElementById("username_parm").value;
	var age = document.getElementById("userage_parm").value;
	var birth = document.getElementById("userbirth_parm").value;
	var userObj = {
		id:Number(id),	//字段一定要转化为对应类型（符合json串标准），否则反序列化为bean时出现bug，比如name字段包含全部内容
		userName:name,
		age:Number(age),
		birth:birth
	}
	
	var method = document.getElementById("method_parm").value;
	var parmPosition = document.getElementById("parmPosition_parm").value;
	var contentType = document.getElementById("content_type_parm").value;
	var url = document.getElementById("url_parm").value;
	var content = null;
	url = 'parm/' + url;
	if("url" == parmPosition) {
		url = url + "?" + getContent(contentType, userObj);	//将参数放到头部
	} else {
		content = getContent(contentType, userObj);	//将参数放到body
	}
	
	console.log("url: " + url);
	console.log("method: " + method);
	console.log("contentType: " + contentType);
	console.log("content: " + content);
	
	realAjax(url, method, contentType, content, "ParmMsg");
}

//拼接json或者urlencoded格式字符串数据
function getContent(contentType, contentObj) {
	var content = "";
	var inputs = document.getElementById("userform").childNodes;
	
	if (contentType == "application/x-www-form-urlencoded") {
		for (let key in contentObj) {
			if(contentObj[key] == null || contentObj[key] == undefined || contentObj[key] == "") {
				continue;
			}
			content += '&';
			content += encodeURI(key);
			content += '=';
			content += encodeURI(contentObj[key]);
		}
		if (content.length > 1)
			content = content.substring(1, content.length); //key1=value1&key2=value2
	} else if (contentType == "application/json") {
		content = JSON.stringify(contentObj);
	}
	return content;
}

function realAjax(url, method, contentType, bodyContent, msgId) {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	  if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
	    document.getElementById(msgId).innerHTML = xmlhttp.responseText;
	  } else {
		document.getElementById(msgId).innerHTML = xmlhttp.status;
	  }
	}
	xmlhttp.open(method, url, true);
	xmlhttp.setRequestHeader("Content-type", contentType);	//设置头部类型
	if (bodyContent != undefined && bodyContent != null && bodyContent.length > 0) {
		xmlhttp.send(bodyContent); //http协议本身没有规定get不能在body放请求数据，这个看不同的浏览器或者测试工具决定，实际测试谷歌浏览器get请求不会把content加入到body
	} else {
		xmlhttp.send();
	}
}

function defaultData() {
	document.getElementById("userid").setAttribute('value', '1'); 
	document.getElementById("username").setAttribute('value', 'jzz');
	document.getElementById("userage").setAttribute('value', '20');
	document.getElementById("userbirth").setAttribute('value', '2000-02-06 11:20:00');
}