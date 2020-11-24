package org.bsoftware.ward.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * MachineDto is a values container for presenting machine principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class MachineDto
{
    /**
     * OS info field
     */
    private String operatingSystem;

    /**
     * Amount of total installed ram field
     */
    private String totalRam;

    /**
     * Ram generation field
     */
    private String ramTypeOrOSBitDepth;

    /**
     * Processes count field
     */
    private String procCount;
}