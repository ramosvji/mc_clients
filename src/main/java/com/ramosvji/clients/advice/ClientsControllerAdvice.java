package com.ramosvji.clients.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ramosvji.clients.advice.dto.BadRequestResponse;
import com.ramosvji.clients.advice.dto.MessageDto;
import com.ramosvji.clients.advice.dto.SourceNotFoundDto;

@ControllerAdvice
public class ClientsControllerAdvice {
	
	@Autowired
	MessageSource messageSource;
	
	@ResponseBody
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BadRequestResponse getBadRequestResponse(MethodArgumentNotValidException e) {
		BadRequestResponse response = new BadRequestResponse(); 
		List<MessageDto> messages = new ArrayList<MessageDto>();
	
		for (ObjectError error : e.getBindingResult().getAllErrors() ) {
			MessageDto message = new MessageDto();
			message.setCode(error.getCode());
			message.setDescription(error.getDefaultMessage());
			messages.add(message);
		}
		
		response.setNumberOfErrors(e.getBindingResult().getErrorCount());
		response.setMessages(messages);
		
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public SourceNotFoundDto getSourceNotFound(IllegalArgumentException e) {
		SourceNotFoundDto response = new SourceNotFoundDto();
		response.setMessage(e.getMessage());
		
		return response;
	}

}
