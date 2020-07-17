package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;

/**
 * MavenDto is a values container for presenting maven fields information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class MavenDto implements Dto
{
    /**
     * Project version field
     */
    private String projectVersion;
}