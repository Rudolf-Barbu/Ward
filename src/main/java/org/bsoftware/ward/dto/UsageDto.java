package org.bsoftware.ward.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * UsageDto is a values container for presenting server usage
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Getter
@Setter
public class UsageDto
{
    /**
     * Processor usage field
     */
    private int processor;

    /**
     * Ram usage field
     */
    private int ram;

    /**
     * Storage usage field
     */
    private int storage;
}