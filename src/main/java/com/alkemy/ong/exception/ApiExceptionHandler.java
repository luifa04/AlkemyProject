package com.alkemy.ong.exception;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import freemarker.template.TemplateException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.MethodNotAllowedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ExceptionMessage returnError(Exception e) {
		e.printStackTrace();
		return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());

	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ExceptionMessage returnErrorBadRequest(NotFoundException e) {
		return new ExceptionMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ExceptionMessage returnErrorIllegalArgument(IllegalArgumentException e) {
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ExceptionMessage returnErrorMethodArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(),"Bad Request", LocalDateTime.now(), errors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailExistException.class)
	@ResponseBody
	public ExceptionMessage handleEmailExist(HttpServletRequest request,
													 EmailExistException e) {

		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorMultipartException(MultipartException e, HttpServletResponse response){
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorConstraintViolationException(ConstraintViolationException e){

		String errorMessage = e.getConstraintViolations().stream().map(error ->error.getMessageTemplate()).collect(Collectors.joining());
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDateTime.now());

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AmazonServiceException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorAmazonServiceException(AmazonServiceException e){
		Map<String, String> errors = new HashMap<>();
		errors.put(e.getServiceName(), e.getErrorCode());
		return new ExceptionMessage(e.getStatusCode(), e.getErrorMessage(), LocalDateTime.now(), errors);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SdkClientException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorSdkClientException(SdkClientException e){
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorIOException(IOException e){
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TemplateException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorTemplateException(TemplateException e){
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(EmptyDataException.class)
	@ResponseBody
	public ExceptionMessage returnEmptyDataException(EmptyDataException e) {
		return new ExceptionMessage(HttpStatus.OK.value(), e.getMessage(), LocalDateTime.now());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorAccessDeniedException(AccessDeniedException e){
		return new ExceptionMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), LocalDateTime.now());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorBadCredentialsException(BadCredentialsException e){
		return new ExceptionMessage(HttpStatus.FORBIDDEN.value(), e.getMessage(), LocalDateTime.now());
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	@ResponseBody
	public ExceptionMessage  returnErrorInternalAuthenticationServiceException(InternalAuthenticationServiceException e){
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
	}


}
