package com.outonofashion.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.outonofashion.api.exceptionhandler.exception.ValidacaoException;
import com.outonofashion.domain.exception.EntidadeEmUsoException;
import com.outonofashion.domain.exception.EntidadeNaoEncontradaException;
import com.outonofashion.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String USER_MESSAGE = "Erro interno na aplicação, tente novamente mais "
			+ "tarde. Se o erro persistir, contacte o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;

	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = ApiError.builder().status(status.value()).title(status.getReasonPhrase()).build();
		} else if (body instanceof String) {
			body = ApiError.builder().status(status.value()).title((String) body).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);

	}

	private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType apiErrorType,
			String detail) {

		return ApiError.builder().status(status.value()).type(apiErrorType.getUri()).title(apiErrorType.getTitle())
				.detail(detail);

	}

	private String joinPath(List<Reference> path) {
		return path.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}

	// FALHA_REQUISICAO #1
	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiErrorType apiErrorType = ApiErrorType.FALHA_REQUISICAO;
		String message = ex.getMessage();

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, message).userMessage(message).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// RECURSO_NAO_ENCONTRADO (ENTIDADE_NAO_ENCONTRADA) #2
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiErrorType apiErrorType = ApiErrorType.RECURSO_NAO_ENCONTRADO;
		String message = ex.getMessage();

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, message).userMessage(message).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// RECURSO_NAO_ENCOTRADO (URI INVALIDA) #2
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ApiErrorType apiErrorType = ApiErrorType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s não existe.", ex.getRequestURL());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);

	}

	// ENTIDADE_EM_USO #3
	@ExceptionHandler(EntidadeEmUsoException.class)
	private ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// CONTEUDO_INVALIDO #4
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex.getCause());

		if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		} else if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}

		ApiErrorType apiErrorType = ApiErrorType.CONTEUDO_INVALIDO;
		String detail = "Requisição vazia ou os campos estão inválidos. Corrija e tente novamente.";

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(USER_MESSAGE).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);
	}

	// PROPRIEDADE_INEXISTENTE #5
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ApiErrorType apiErrorType = ApiErrorType.PROPRIEDADE_INEXISTENTE;

		String path = joinPath(ex.getPath());
		String detail = String.format("A propriedade '%s' foi ignorada ou é inexistente na classe '%s'.", path,
				ex.getReferringClass().getSimpleName());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(USER_MESSAGE).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);
	}

	// FORMATO_INVALIDO #6
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ApiErrorType apiErrorType = ApiErrorType.FORMATO_INVALIDO;

		String path = joinPath(ex.getPath());
		String detail = String.format(
				"A propriedade '%s' recebeu o valor '%s' inválido. O esperado" + " era um valor do tipo '%s'", path,
				ex.getValue(), ex.getTargetType().getSimpleName());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(USER_MESSAGE).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);
	}

	// CAMPOS_INVALIDOS (POST/PUT) #7
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiErrorType apiErrorType = ApiErrorType.CAMPOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Por favor corrija e tente novamente.";

		BindingResult bindingResult = ex.getBindingResult();

		List<ApiError.Field> apiFieldsError = bindingResult.getAllErrors().stream().map(objectError -> {

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			return ApiError.Field.builder().name(name).message(message).build();

		}).collect(Collectors.toList());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail)
				.fields(apiFieldsError).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);

	}

	// CAMPOS_INVALIDOS (PATCH) #7
	@ExceptionHandler(ValidacaoException.class)
	protected ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiErrorType apiErrorType = ApiErrorType.CAMPOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Por favor corrija e tente novamente.";

		BindingResult bindingResult = ex.getBindingResult();

		List<ApiError.Field> apiFieldsError = bindingResult.getAllErrors().stream().map(objectError -> {
			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			return ApiError.Field.builder().name(name).message(message).build();
		}).collect(Collectors.toList());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail)
				.fields(apiFieldsError).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// PARAMETRO_INVALIDO #8
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status,
					request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiErrorType apiErrorType = ApiErrorType.PARAMETRO_INVALIDO;
		String detail = String.format(
				"O parâmetro da URL '%s' recebeu o valor '%s', que é um tipo inválido. Devendo ser " + "do tipo '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// ERRO_INTERNO #9
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handeException(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiErrorType apiErrorType = ApiErrorType.ERRO_INTERNO;

		String detail = USER_MESSAGE;

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);

	}

	// ACESSO_NEGADO #10
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;

		ApiErrorType apiErrorType = ApiErrorType.ACESSO_NEGADO;
		String detail = ex.getMessage();

		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail)
				.userMessage("Você não possui permissão para executar esta operação.").build();

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
	}

}
