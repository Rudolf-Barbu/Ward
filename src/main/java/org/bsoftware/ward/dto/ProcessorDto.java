package org.bsoftware.ward.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ProcessorDto is a values container for presenting processor principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class ProcessorDto
{
    /**
     * Processor name field
     */
    private String name;

    /**
     * Core count field
     */
    private String coreCount;

    /**
     * Processor max frequency field
     */
    private String clockSpeed;

    /**
     * Processor architecture field
     */
    private String bitDepth;
}