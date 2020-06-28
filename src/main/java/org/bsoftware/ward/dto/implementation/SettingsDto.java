package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.validators.RequestDtoValidator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * SettingsDto s a values container for settings
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
public class SettingsDto implements Dto
{
    /**
     * Port name field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    private String port;

    /**
     * Theme name field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    private String theme;
}