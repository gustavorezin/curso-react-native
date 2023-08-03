package com.midas.api.handler;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.mail.SendFailedException;
import javax.validation.ConstraintViolationException;

import org.hibernate.LazyInitializationException;
import org.hibernate.PropertyAccessException;
import org.springframework.beans.FatalBeanException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.midas.api.error.ErrorDetails;
import com.midas.api.error.ResourceNotFoundDetails;
import com.midas.api.error.ResourceNotFoundException;
import com.midas.api.error.ValidationErrorDetails;

//Tratamento de erros gerais
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	// Pode ser criado quantos metodos quiser, para cada excessao, ou apenas deixar
	// o ultimo para puxar automatico tudo
	// Para quando der Not Found Exception...
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(ResourceNotFoundException exc) {
		ResourceNotFoundDetails rnfDt = ResourceNotFoundDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value()).title("Recurso nao encontrado!").detail(exc.getMessage())
				.developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Erro interno do Servidor!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Item nulo para valores informados!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PropertyAccessException.class)
	public ResponseEntity<?> handlePropertyAccessException(PropertyAccessException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Item nulo não permitido!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Identificador do item não localizado!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Informado um valor inválido na requisição!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<?> handleInvalidDataAccessResourceUsageException(
			InvalidDataAccessResourceUsageException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Problemas na gestão no Banco de dados!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(JpaSystemException.class)
	public ResponseEntity<?> handleJpaSystemException(JpaSystemException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Problemas na gestão no Banco de dados!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<?> handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Valor inválido para leitura!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<?> handleArithmeticException(ArithmeticException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Houve um erro ao calcular um valor BigDecimal!").detail(exc.getMessage())
				.developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<?> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Informado valor não compatível com esperado!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnexpectedRollbackException.class)
	public ResponseEntity<?> handleUnexpectedRollbackException(UnexpectedRollbackException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder()
				.title("Erro de persistência. Rollback gerado internamente em métodos!")
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).detail(exc.getMessage())
				.timestamp(new Date().getTime()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConcurrentModificationException.class)
	public ResponseEntity<?> handleConcurrentModificationException(ConcurrentModificationException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder()
				.title("Erro de persistência ou inconsistência. Concorrência na transição de dados!")
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).detail(exc.getMessage())
				.timestamp(new Date().getTime()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().title("Erro de validações de campos!")
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).detail(exc.getMessage())
				.timestamp(new Date().getTime()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Erro de integridade de campos!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FatalBeanException.class)
	public ResponseEntity<?> handleFatalBeanException(FatalBeanException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Erro de integridade de campos - Erro Bean!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(LazyInitializationException.class)
	public ResponseEntity<?> handleFatalBeanException(LazyInitializationException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Sessão fechada - Impossível ler os atributos!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<?> handleTransactionSystemException(TransactionSystemException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Nao foi possivel executar a operacao na base de dados!").detail(exc.getMessage())
				.developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<?> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Resultado da consulta possui inconsistências!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<?> handleMultipartException(MultipartException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Formato de arquivo ou requisição não compatível!").detail(exc.getMessage())
				.developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).title("Tamanho do arquivo em MB acima do permitido!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(SendFailedException.class)
	public ResponseEntity<?> handleSendFailedException(SendFailedException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Configuração do e-mail não compatível com servidor informado!").detail(exc.getMessage())
				.developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.UNAUTHORIZED.value()).title("Sem autorizacao para esta ação!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException exc) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.UNAUTHORIZED.value()).title("Problemas na autorizacao ou login!")
				.detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.UNAUTHORIZED);
	}

	// Metodo criado sobre item ja existente no Extends acima
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<FieldError> fieldError = exc.getBindingResult().getFieldErrors();
		String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		ValidationErrorDetails rnfDt = ValidationErrorDetails.Builder.newBuilder().timestamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value()).title("Erro de validacao dos campos!")
				.detail("Validacao de campos").developerMessage(exc.getClass().getName()).field(fields)
				.fieldMessage(fieldMessages).build();
		return new ResponseEntity<>(rnfDt, HttpStatus.BAD_REQUEST);
	}

	// Metodo criado sobre item ja existente no Extends acima
	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception exc, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails rnfDt = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(status.value())
				.title("Erro interno!").detail(exc.getMessage()).developerMessage(exc.getClass().getName()).build();
		return new ResponseEntity<>(rnfDt, headers, status);
	}
}
