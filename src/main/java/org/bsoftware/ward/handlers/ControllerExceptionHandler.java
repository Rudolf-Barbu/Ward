package org.bsoftware.ward.handlers;

import org.bsoftware.ward.components.UtilitiesComponent;
import org.bsoftware.ward.dto.ErrorDto;
import org.bsoftware.ward.exceptions.ApplicationNotSetUpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
     * Handles ApplicationNotSetUpException, then it thrown
     */
    @ResponseBody
    @ExceptionHandler(value = ApplicationNotSetUpException.class)
    public ResponseEntity<?> applicationNotSetUpExceptionHandler(ApplicationNotSetUpException applicationNotSetUpException)
    {
        return new ResponseEntity<>(new ErrorDto(applicationNotSetUpException), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException, then it thrown
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        return new ResponseEntity<>(new ErrorDto(methodArgumentNotValidException), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles all other servlet exceptions, which were not handled by others handlers
     *
     * @throws IOException if ini file is unreachable
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Model model) throws IOException
    {
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        return "error/500";
    }
}