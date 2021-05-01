package org.jzz.spbootDemo.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

/** servlet Filter（过滤器）;
 *  注意与spring 拦截器（Interceptor）的区别
 *  现在我们权限和认证更多的是在Spring Boot中整合使用shiro或者Spring Security来完成，比较少用自定义拦截器了，不过可以用拦截器实现其他功能，比如ip黑名单等等特殊场景
 *  
 *  CROS过滤器，主要处理CROS跨域请求，当Method=OPTIONS（预检请求 浏览器打印为 preflight）时添加“简单请求”外的“请求头部、请求方法、请求类型”，即各种Access-Control-Allow信息
 *  测试结果：
 *  1.默认配置下，只要跨域就会被浏览器拦截（即MDN说的“简单请求”也会拦截）
 *  2.Access-Control-Allow-Origin这个头部在预检请求和实际请求时都需要添加（chrome浏览器），其余的头部仅需要在预检请求时添加

 *  总结：跨域解决方案之——服务器端配置，几种方法如下：
 * 1. 服务器使用统一跨域配置（https://blog.csdn.net/junmoxi/article/details/89402578）
 * 2. 在response中添加跨域头Access-Control-Allow-Origin
 * 3. 使用注解@CrossOrigin(origins = {})
 * 4. 以上前提：服务器开启OPTIONS请求，否则不能跨域
 */
public class CROSFilter implements Filter{
	Logger logger = LoggerFactory.getLogger(CROSFilter.class);

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;
        logger.info("CROSFilter :" + request.getRequestURI() + " " + request.getMethod());
        //预检请求和实际请求，都需要添加跨域域名信息
        response.setHeader("Access-Control-Allow-Origin", "http://localhost");	//指定跨域的域名，要带"http://"
//    	response.setHeader("Access-Control-Allow-Origin", "*");	//所有origin域名均允许
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
        	//预检请求时，添加头部“请求方法、请求类型”等信息，实际请求时不需要添加
        	//一定要在这添加，因为OPTIONS请求不会进入controller
        	if (request.getRequestURI().equals("/cros/header")) {
        		String headerName = request.getParameter("header");
        		String headerValue = (headerName == null ? null : request.getHeader(headerName));
        		logger.info("header:{} {}" , headerName, headerValue);	//value获取为空, 说明options请求并没有携带自定义头部过来	
        		response.setHeader("Access-Control-Allow-Headers", headerName);	//使用url参数的声明的headerKey作为跨域头部，允许头部动态改变跨域
        	} else if (request.getRequestURI().equals("/cros/method")) {
        		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE"); 	
        	} else if (request.getRequestURI().equals("/cros/contenttype")) {
        		
        	}
        } 
        chain.doFilter(srequest, sresponse);
	}
	
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    	logger.info("" + getClass() + " init");
    }
    
    @Override
    public void destroy() {
    	logger.info("" + getClass() + " destroy");
    }

}
