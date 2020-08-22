package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * ErrorService used to get error page messages
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class ErrorService implements org.bsoftware.ward.services.Service
{
    /**
     * Autowired MessageSource object
     * Used for getting messages bundle
     */
    private final MessageSource messageSource;

    /**
     * Get messages for error page
     *
     * @param httpServletResponse request status code
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public <T extends Dto> T get(HttpServletResponse httpServletResponse)
    {
        ErrorDto errorDto = new ErrorDto();

        errorDto.setCode(httpServletResponse.getStatus());

        errorDto.setTitle(messageSource.getMessage("error."
                .concat(String.valueOf(httpServletResponse.getStatus()))
                .concat(".title"), null, Locale.getDefault()));

        errorDto.setExplanation(messageSource.getMessage("error."
                .concat(String.valueOf(httpServletResponse.getStatus()))
                .concat(".explanation"), null, Locale.getDefault()));

        errorDto.setAdvice(messageSource.getMessage("error."
                .concat(String.valueOf(httpServletResponse.getStatus()))
                .concat(".advice"), null, Locale.getDefault()));

        return (T) errorDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param messageSource autowired MessageSource object
     */
    @Autowired
    public ErrorService(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }
}