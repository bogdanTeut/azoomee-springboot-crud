package com.azoomee.handler;

import com.azoomee.service.exception.DepartmentAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ControllerAdvice
class GlobalControllerExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(GlobalControllerExceptionHandler.class.getName());

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(DepartmentAlreadyExistsException e) {
		LOGGER.log(Level.SEVERE, e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		LOGGER.log(Level.SEVERE, e.getMessage());
		return new ResponseEntity<>("Request was not in a json format", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		LOGGER.log(Level.SEVERE, e.getMessage());
		final List<String> messages = buildFailureMessage(e);

		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

	private List<String> buildFailureMessage(final MethodArgumentNotValidException e)
	{
		final BindingResult result = e.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();

		return fieldErrors.stream()
				.map(fieldError -> String
						.format("Field [%s] validation failed due to reason: [%s]", fieldError.getField(), fieldError
								.getDefaultMessage()))
				.collect(Collectors.toList());
	}
}
