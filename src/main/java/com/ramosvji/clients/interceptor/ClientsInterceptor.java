package com.ramosvji.clients.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class ClientsInterceptor implements HandlerInterceptor {
	
	@Autowired
	LocaleChangeInterceptor l;
	
	@Autowired LocaleResolver resolver;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		
		if(request.getHeader("Accept-Language") != null) {
			resolver.setLocale(request, response, new Locale(request.getHeader("Accept-Language")));
		}
	    
	    return true;
	}   
}
