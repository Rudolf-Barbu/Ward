package org.bsoftware.ward.dto.implementation;

import lombok.Getter;
import lombok.Setter;
import org.bsoftware.ward.dto.Dto;
import javax.validation.constraints.*;

/**
 * SettingsDto s a values container for settings
 *
 * @author Rudolf Barbu
 * @version 1.0.3
 */
@Getter
@Setter
public class SetupDto implements Dto
{
    /**
     * Server name Field
     */
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String serverName;

    /**
     * Theme name field
     */
    @NotNull
    @NotEmpty
    @Pattern(regexp = "light|dark")
    private String theme;

    /**
     * Port port field
     */
    @NotNull
    @NotEmpty
    @Min(value = 10)
    @Max(value = 65535)
    private String port;
}