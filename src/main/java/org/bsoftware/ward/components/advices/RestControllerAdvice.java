package org.bsoftware.ward.components.advices;

import org.bsoftware.ward.dto.implementation.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Autowired HttpHeaders object
     * Used as a bean, which already provides Json headers
     */
    private final HttpHeaders httpHeaders;

    /**
     * Handles MethodArgumentNotValidException when if was thrown
     *
     * @param exception MethodArgumentNotValidException which was thrown
     * @param headers http headers
     * @param status http status
     * @param request http request
     * @return ResponseEntity with error message
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        Optional<FieldError> optionalFieldError = exception.getBindingResult().getFieldErrors().stream().findFirst();

        if (optionalFieldError.isPresent())
        {
            FieldError fieldError = optionalFieldError.get();
            String message = fieldError.getField() + " " + fieldError.getDefaultMessage();

            return handleExceptionInternal(exception, new ResponseDto(message), httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, request);
        }
        else
        {
            return handleExceptionInternal(exception, new ResponseDto("Method not allowed"), httpHeaders, HttpStatus.METHOD_NOT_ALLOWED, request);
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param httpHeaders autowired HttpHeaders object
     */
    @Autowired
    public RestControllerAdvice(HttpHeaders httpHeaders)
    {
        this.httpHeaders = httpHeaders;
    }
}