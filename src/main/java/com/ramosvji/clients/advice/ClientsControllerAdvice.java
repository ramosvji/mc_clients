package com.ramosvji.clients.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientsControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String emptyResult(org.springframework.web.bind.MethodArgumentNotValidException e) {
		return "Valor vacio. 1";
	}
	
	@ResponseBody
	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String emptyResult() {
		return "Valor no encontrado";
	}

}
