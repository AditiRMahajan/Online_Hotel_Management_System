package com.micro.receptionistservice.exception;

import java.util.*;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Exception for handling input validation
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.error(ex.getMessage());
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ResponseEntity<Object> entity = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		return entity;
	}

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
        return new ResponseEntity<String>("Input field is empty, Please look into it", HttpStatus.BAD_REQUEST);
    }

     //Exception for Id not found
     @ExceptionHandler(IdNotFoundException.class)
     public ResponseEntity<String> handleIdNotFoundtException(IdNotFoundException idNotFoundException) {
         return new ResponseEntity<String>("Id/number does not exists, Please enter valid id/number", HttpStatus.BAD_REQUEST);
     }

    //NoSuchElementPresentException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException) {
        return new ResponseEntity<String>("No value is present in DB, Please change your request", HttpStatus.NOT_FOUND );
    }

    //RequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                return new ResponseEntity<Object>("Please change http method type(get/post/delete)", HttpStatus.NOT_FOUND );
       // return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    //MediaTypeNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                return new ResponseEntity<Object>("Please check your media type)", HttpStatus.NOT_FOUND );        
        //return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    //MissingPathVariableException
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
                return new ResponseEntity<Object>("Please change the requested pathvariable", HttpStatus.NOT_FOUND );
        //return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
                return new ResponseEntity<Object>("Seems like you have given wrong input", HttpStatus.NOT_FOUND );
        //return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    
}
