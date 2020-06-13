package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * UsageDto is a values container for presenting server usage
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Getter
@Setter
public class UsageDto implements Dto
{
    /**
     * Processor usage field
     */
    private int processorUsage;

    /**
     * Ram usage field
     */
    private int ramUsage;

    /**
     * Storage usage field
     */
    private int storageUsage;
}