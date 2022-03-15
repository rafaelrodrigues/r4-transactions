package com.r4.transaction.controller.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestControllerAdvice
public class ErroValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDto> dto = new ArrayList<>();
		
		Field[] aliasFields = exception.getParameter().getParameter().getType().getDeclaredFields();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDto erro = new ErroFormDto(getJsonAlias(aliasFields,e), mensage);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
	public ErroFormDto handle(IllegalArgumentException exception) {
        ErroFormDto erro = new ErroFormDto("ERROR", exception.getMessage());
        return erro;
    }
	
	String getJsonAlias(Field[] aliasFields, FieldError e) {

		JsonProperty[] alisas = getAnnotationsForField(aliasFields, e.getField());
        if (alisas == null || alisas.length == 0) {
            return e.getField();
        }

        String values = alisas[0].value();
        if (values.length() == 0) {
            return e.getField();
        }

        return values;
    }
	JsonProperty[] getAnnotationsForField(Field[] aliasFields, String fieldName) {
        Optional<Field> first = Stream.of(aliasFields).filter(f -> f.getName().equals(fieldName)).findFirst();
        return first.map(field -> field.getAnnotationsByType(JsonProperty.class)).orElse(null);
    }

}
