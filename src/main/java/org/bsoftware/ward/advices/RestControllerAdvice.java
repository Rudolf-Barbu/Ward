package org.bsoftware.ward.advices;

import org.bsoftware.ward.dto.implementation.ResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Optional;

/**
 * RestControllerAdvice provides error handling for rest api
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@org.springframework.web.bind.annotation.RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdvice extends ResponseEntityExceptionHandler
{
    /**
     * Handles MethodArgumentNotValidException when it was thrown
     *
     * @param exception MethodArgumentNotValidException which was thrown
     * @param httpHeaders http headers
     * @param httpStatus http status
     * @param webRequest http request
     * @return ResponseEntity with error message
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest)
    {
        Optional<FieldError> optionalFieldError = exception.getBindingResult().getFieldErrors().stream().findFirst();

        if (optionalFieldError.isPresent())
        {
            FieldError fieldError = optionalFieldError.get();
            String message = fieldError.getField() + " " + fieldError.getDefaultMessage();

            return handleExceptionInternal(exception, new ResponseDto(message), httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, webRequest);
        }
        else
        {
            return handleExceptionInternal(exception, new ResponseDto("Method not allowed"), httpHeaders, HttpStatus.METHOD_NOT_ALLOWED, webRequest);
        }
    }
}