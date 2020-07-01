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
 * @version 1.0.0
 */
@Getter
@Setter
public class SettingsDto implements Dto
{
    /**
     * Port database address field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Size(groups = {RequestDtoValidator.PostSettings.class}, max = 32)
    private String databaseLink;

    /**
     * Port username field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Size(groups = {RequestDtoValidator.PostSettings.class}, max = 32)
    private String username;

    /**
     * Port password field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @Size(groups = {RequestDtoValidator.PostSettings.class}, max = 16)
    private String password;

    /**
     * Port port field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Max(groups = {RequestDtoValidator.PostSettings.class}, value = 65535)
    private String port;

    /**
     * Theme name field
     */
    @NotNull(groups = {RequestDtoValidator.PostSettings.class})
    @NotEmpty(groups = {RequestDtoValidator.PostSettings.class})
    @Pattern(groups = {RequestDtoValidator.PostSettings.class}, regexp = "light|dark")
    private String theme;
}