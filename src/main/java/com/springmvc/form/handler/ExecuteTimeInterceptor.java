package com.springmvc.form.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.form.web.UserController;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		modelAndView.addObject("executeTime", executeTime);
		if (logger.isDebugEnabled()) {
			logger.debug("[" + handler + "] executeTime : " + executeTime+ "ms");
		}
	}
}
