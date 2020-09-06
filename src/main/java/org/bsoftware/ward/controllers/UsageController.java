package org.bsoftware.ward.controllers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.wrappers.RestResponseEntityWrapper;
import org.bsoftware.ward.dto.implementation.ResponseDto;
import org.bsoftware.ward.services.implementation.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsageController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/usage")
public class UsageController
{
    /**
     * Autowired UsageService object
     * Used for getting usage information
     */
    private final UsageService usageService;

    /**
     * Autowired RestResponseEntityWrapper object
     * Used as response wrapper bean, which provides Json headers automatically
     */
    private final RestResponseEntityWrapper restResponseEntityWrapper;

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getUsage()
    {
        if (!Ward.isFirstLaunch())
        {
            return restResponseEntityWrapper.wrap(usageService.get());
        }
        else
        {
            return restResponseEntityWrapper.wrap(new ResponseDto("Set up application first"));
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param usageService autowired UsageService object
     * @param restResponseEntityWrapper autowired RestResponseEntityWrapper object
     */
    @Autowired
    public UsageController(UsageService usageService, RestResponseEntityWrapper restResponseEntityWrapper)
    {
        this.usageService = usageService;
        this.restResponseEntityWrapper = restResponseEntityWrapper;
    }
}