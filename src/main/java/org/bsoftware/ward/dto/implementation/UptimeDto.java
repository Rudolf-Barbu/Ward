package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * UptimeDto is a values container for presenting uptime principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class UptimeDto implements Dto
{
    /**
     * Uptime days field
     */
    private String days;

    /**
     * Uptime hours field
     */
    private String hours;

    /**
     * Uptime minutes field
     */
    private String minutes;

    /**
     * Uptime seconds field
     */
    private String seconds;
}