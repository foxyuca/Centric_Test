package com.centric.products.config;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.centric.products.exception.ExceptionResponseEntity;
import com.centric.products.exception.NotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new ExceptionResponseEntity(BAD_REQUEST, error, ex), headers);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(BAD_REQUEST);
		exceptionResponseEntity.setMessage("Validation error");
		exceptionResponseEntity.addValidationErrors(ex.getBindingResult().getFieldErrors());
		exceptionResponseEntity.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(exceptionResponseEntity, headers);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		String error = "Malformed JSON request";
		return buildResponseEntity(new ExceptionResponseEntity(HttpStatus.BAD_REQUEST, error), headers);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> forbidden(final AccessDeniedException e, final WebRequest request) {
		return buildResponseEntity(
				new ExceptionResponseEntity(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase(), e),
				new HttpHeaders());

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFound(final NotFoundException e, final WebRequest request) {
		return buildResponseEntity(new ExceptionResponseEntity(HttpStatus.NOT_FOUND, e), new HttpHeaders());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(status);
		exceptionResponseEntity.setMessage(status.getReasonPhrase());
		exceptionResponseEntity.setHttpStatus(status);
		exceptionResponseEntity.setRootException(ex.getMessage());
		return buildResponseEntity(exceptionResponseEntity, headers);
	}

	private ResponseEntity<Object> buildResponseEntity(ExceptionResponseEntity exceptionResponseEntity,
			HttpHeaders headers) {
		return new ResponseEntity<>(exceptionResponseEntity, headers, exceptionResponseEntity.getHttpStatus());
	}
}