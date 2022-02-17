package dev.leons.ward.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * SetupDto is a values container for setup data
 *
 * @author Rudolf Barbu
 * @version 1.0.3
 */
@Getter
@Setter
public class SetupDto
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