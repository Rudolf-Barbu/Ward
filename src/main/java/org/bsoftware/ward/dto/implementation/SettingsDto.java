package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.validators.RequestDtoValidator;

import javax.validation.constraints.*;

/**
 * SettingsDto s a values container for settings
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Getter
@Setter
public class SettingsDto implements Dto
{
    /**
     * Server name Field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Size(groups = {RequestDtoValidator.PostSettings.class}, max = 16)
    private String serverName;

    /**
     * Port port field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Max(groups = {RequestDtoValidator.PostSettings.class}, value = 65535)
    @Min(groups = {RequestDtoValidator.PostSettings.class}, value = 0)
    private String port;

    /**
     * Theme name field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Pattern(groups = {RequestDtoValidator.PostSettings.class}, regexp = "light|dark")
    private String theme;
}