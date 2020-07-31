package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.validators.RequestValidator;
import javax.validation.constraints.*;

/**
 * SettingsDto s a values container for settings
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Getter
@Setter
public class SetupDto implements Dto
{
    /**
     * Server name Field
     */
    @NotNull(groups = {RequestValidator.PostRequest.class})
    @NotEmpty(groups = {RequestValidator.PostRequest.class})
    @Size(groups = {RequestValidator.PostRequest.class}, min = 1, max = 10)
    private String serverName;

    /**
     * Port port field
     */
    @NotNull(groups = {RequestValidator.PostRequest.class})
    @NotEmpty(groups = {RequestValidator.PostRequest.class})
    @Max(groups = {RequestValidator.PostRequest.class}, value = 65535)
    @Min(groups = {RequestValidator.PostRequest.class}, value = 0)
    private String port;

    /**
     * Theme name field
     */
    @NotNull(groups = {RequestValidator.PostRequest.class})
    @NotEmpty(groups = {RequestValidator.PostRequest.class})
    @Pattern(groups = {RequestValidator.PostRequest.class}, regexp = "light|dark")
    private String theme;
}