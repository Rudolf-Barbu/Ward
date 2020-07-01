package org.bsoftware.ward.controllers.api;

import org.bsoftware.ward.components.wrappers.RestResponseEntityWrapper;
import org.bsoftware.ward.dto.implementation.SettingsDto;
import org.bsoftware.ward.services.implementation.SettingsService;
import org.bsoftware.ward.validators.RequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * SettingsController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/settings")
public class SettingsController
{
    /**
     * Autowired SettingsService object
     * Used for posting settings information in database
     */
    private SettingsService settingsService;

    /**
     * Autowired RestResponseEntityWrapper object
     * Used as response wrapper bean, which provides Json headers automatically
     */
    private RestResponseEntityWrapper restResponseEntityWrapper;

    /**
     * Posting settings into database
     *
     * @param settingsDto dto with data
     * @return OR response with empty body
     */
    @PostMapping
    public ResponseEntity<?> postSettings(@Validated(value = {RequestDtoValidator.PostSettings.class}) @RequestBody SettingsDto settingsDto) throws Exception
    {
        settingsService.post(settingsDto);

        return restResponseEntityWrapper.wrap(null, HttpStatus.OK);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param settingsService autowired SettingsService object
     * @param restResponseEntityWrapper autowired RestResponseEntityWrapper object
     */
    @Autowired
    public SettingsController(SettingsService settingsService, RestResponseEntityWrapper restResponseEntityWrapper)
    {
        this.settingsService = settingsService;
        this.restResponseEntityWrapper = restResponseEntityWrapper;
    }
}