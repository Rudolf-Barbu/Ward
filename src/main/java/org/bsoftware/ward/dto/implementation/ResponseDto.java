package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import org.bsoftware.ward.dto.Dto;

/**
 * ResponseDto is a values container for presenting response info
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
public class ResponseDto implements Dto
{
    /**
     * Response message field
     */
    private String message;

    /**
     * Setter for message field
     *
     * @param message messdage to display
     */
    public ResponseDto(String message)
    {
        this.message = message;
    }
}