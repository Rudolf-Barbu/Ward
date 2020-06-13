package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * StorageDto is a values container for presenting storage principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class StorageDto implements Dto
{
    /**
     * Host0 storage name field
     */
    private String storageName;

    /**
     * Amount of total installed storage field
     */
    private String totalStorage;

    /**
     * Disk count field
     */
    private String diskCount;

    /**
     * Total amount of virtual memory (Swap on Linux) field
     */
    private String swapAmount;
}