package dev.leons.ward.handlers;

import dev.leons.ward.components.UtilitiesComponent;
import dev.leons.ward.dto.ErrorDto;
import dev.leons.ward.exceptions.ApplicationAlreadyConfiguredException;
import dev.leons.ward.exceptions.ApplicationNotConfiguredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * ControllerExceptionHandler is standard exception handler for rest api, and white labels
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler
{
    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    @Autowired
    private UtilitiesComponent utilitiesComponent;

    /**
     * Handles exceptions with BAD_REQUEST status, then they thrown
     */
    @ResponseBody
    @ExceptionHandler(value = {ApplicationNotConfiguredException.class, ApplicationAlreadyConfiguredException.class})
    public ResponseEntity<ErrorDto> applicationNotSetUpExceptionHandler(final Exception exception)
    {
        return new ResponseEntity<>(new ErrorDto(exception), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions with UNPROCESSABLE_ENTITY status, then they thrown
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDto> methodArgumentNotValidExceptionHandler(final Exception exception)
    {
        return new ResponseEntity<>(new ErrorDto(exception), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles MethodArgumentNotValidException, then it thrown
     * Also handles all other servlet exceptions, which were not handled by others handlers
     *
     * @throws IOException if ini file is unreachable
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(final Exception exception, final Model model) throws IOException
    {
        model.addAttribute("theme", utilitiesComponent.getFromIniFile("theme"));
        System.out.println(exception.getMessage());
        return (exception instanceof HttpRequestMethodNotSupportedException) ? "error/404" : "error/500";
    }
}