package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * ProcessorDto is a values container for presenting processor principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class ProcessorDto implements Dto
{
    /**
     * Processor name field
     */
    private String processorName;

    /**
     * Core count field
     */
    private String coreCount;

    /**
     * Processor max frequency field
     */
    private String maxClockSpeed;

    /**
     * Processor architecture field
     */
    private String processorBitDepth;
}