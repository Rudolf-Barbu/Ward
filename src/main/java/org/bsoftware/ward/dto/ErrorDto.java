package org.bsoftware.ward.dto;

import lombok.Getter;
import java.time.LocalDateTime;

/**
 * ErrorDto is a container for error response
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Getter
public final class ErrorDto
{
    /**
     *  Error timestamp field
     */
    private final String timestamp = LocalDateTime.now().toString();

    /**
     *  Error message field
     */
    private final String errMessage;

    /**
     *  Exception name field
     */
    private final String exceptionName;

    /**
     * Setter for errMessage and exceptionName fields
     *
     * @param exception thrown exception
     */
    public ErrorDto(Exception exception)
    {
        this.errMessage = exception.getMessage();
        this.exceptionName = exception.getClass().getName();
    }
}