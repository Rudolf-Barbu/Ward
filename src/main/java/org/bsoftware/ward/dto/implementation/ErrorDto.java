package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

@Getter
@Setter
public class ErrorDto implements Dto
{
    /**
     * Used to determine error code
     */
    private int code;

    /**
     * Used to determine error title
     */
    private String title;

    /**
     * Used to determine error explanation
     */
    private String explanation;

    /**
     * Used to determine error advice
     */
    private String advice;
}