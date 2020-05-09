package org.jzz.spbootDemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Merin
 * 测试跨域请求，作为服务方，应该启动第二个springboot服务:
 * http://localhost2:81/cros/**
 */
@Controller
@RequestMapping("/cros")
public class CROSController {
	Logger logger = LoggerFactory.getLogger(CROSController.class);
	
	@RequestMapping(value="header")
	public @ResponseBody String testheader(HttpServletRequest request, HttpServletResponse response) {
		String headerName = request.getParameter("header");
		String headerValue = (headerName == null ? null : request.getHeader(headerName));
		String rspString = String.format("recive header:%s=%s", headerName, headerValue );
		logger.info(rspString);
		return rspString;
	}
	
	@RequestMapping(value="method")
	public @ResponseBody String testMethod(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		String rspString = String.format("request method:%s", method);
		logger.info(rspString);
		return rspString;
	}
	
	@RequestMapping(value="contenttype")	
	public @ResponseBody String testContentType(HttpServletRequest request, HttpServletResponse response) {
		String contentType = request.getHeader("Content-type");
		String rspString = String.format("request contentType:%s", contentType);
		logger.info(rspString);
		return rspString;
	}
}
