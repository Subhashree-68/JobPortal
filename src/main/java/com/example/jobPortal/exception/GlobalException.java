package com.example.jobPortal.exception;

import com.example.jobPortal.Dto.Response;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<?> handleUserNotFoundException(UserNotFoundException ex){
        return new Response<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),null);
    }
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors=ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
				error->error.getField(),
				error->error.getDefaultMessage()));
		return new Response<>(HttpStatus.BAD_REQUEST.value(),"Not valid",errors);
	
	}
}
