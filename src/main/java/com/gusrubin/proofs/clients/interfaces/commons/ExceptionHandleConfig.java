package com.gusrubin.proofs.clients.interfaces.commons;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandleConfig extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({IllegalArgumentException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler({IllegalStateException.class, NullPointerException.class})
	void handleBadStateRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}

}
