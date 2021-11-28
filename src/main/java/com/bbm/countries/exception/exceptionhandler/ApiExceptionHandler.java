package com.bbm.countries.exception.exceptionhandler;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bbm.countries.exception.NegocioException;
import com.bbm.countries.exception.exceptionhandler.ErrorObject.Campo;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = new ArrayList<ErrorObject.Campo>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			
			// Pega o campo onde ocorreu o problema
			String nome = ((FieldError) error).getField();
			
			String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new ErrorObject.Campo(nome, msg));
		}
		
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatus(status.value() + " ==> " + status.getReasonPhrase());
		errorObject.setTitle("Um ou mais campos estão inválidos. Faca o devido preenchimento e tente novamente");
		errorObject.setTime(OffsetDateTime.now());
		errorObject.setCampos(campos);
		
		return super.handleExceptionInternal(ex, errorObject, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> negocioExceptionHandler(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatus(status.value() + " ==> " + status.getReasonPhrase());
		errorObject.setTitle(ex.getMessage());
		errorObject.setTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, errorObject, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof ConstraintViolationException) {
			msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof SQLException) {
			msg = ((SQLException) ex).getCause().getCause().getMessage();

		} else {
			msg = ex.getMessage();
		}
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;				

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatus(status.value() + " ==> " + status.getReasonPhrase());
		errorObject.setTitle("Erro Interno Violacão da Integridade de Dados: \n"+msg);
		errorObject.setTime(OffsetDateTime.now());
		
		return new ResponseEntity<>(errorObject, new HttpHeaders(), status);
	}
}
