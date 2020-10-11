package org.bsoftware.ward.dto;

import lombok.Getter;

/**
 * ResponseDto is a values container for presenting response info
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
public final class ResponseDto
{
    /**
     * Response message field
     */
    private final String message;

    /**
     * Setter for message field
     *
     * @param message message to display
     */
    public ResponseDto(String message)
    {
        this.message = message;
    }
}