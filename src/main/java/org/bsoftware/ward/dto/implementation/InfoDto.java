package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * InfoDto is a container for other info dtos
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Getter
@Setter
public class InfoDto implements Dto
{
    /**
     *  Processor info dto field
     */
    private ProcessorDto processorDto;

    /**
     *  machine info dto field
     */
    private MachineDto machineDto;

    /**
     *  Storage info dto field
     */
    private StorageDto storageDto;

    /**
     *  Uptime info dto field
     */
    private UptimeDto uptimeDto;

    /**
     *  Settings info dto field
     */
    private SettingsDto settingsDto;
}