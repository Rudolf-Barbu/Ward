package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * MachineDto is a values container for presenting machine principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class MachineDto implements Dto
{
    /**
     * OS info field
     */
    private String operatingSystemName;

    /**
     * Amount of total installed ram field
     */
    private String totalRam;

    /**
     * Ram generation field
     */
    private String ramTypeOrOSBitness;

    /**
     * Processes count field
     */
    private String procCount;
}