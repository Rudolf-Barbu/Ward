package org.bsoftware.ward.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * InfoDto is a container for other info objects
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Getter
@Setter
public class InfoDto
{
    /**
     *  Processor info dto field
     */
    private ProcessorDto processor;

    /**
     *  machine info dto field
     */
    private MachineDto machine;

    /**
     *  Storage info dto field
     */
    private StorageDto storage;

    /**
     *  Uptime info dto field
     */
    private UptimeDto uptime;

    /**
     *  Setup info dto field
     */
    private SetupDto setup;

    /**
     *  Maven info dto field
     */
    private ProjectDto project;
}