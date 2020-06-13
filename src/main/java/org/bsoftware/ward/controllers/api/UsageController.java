package org.bsoftware.ward.controllers.api;

import org.bsoftware.ward.components.wrappers.RestResponseEntityWrapper;
import org.bsoftware.ward.services.implementation.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * Autowired InfoService object
     * Used for getting usage information
     */
    private UsageService usageService;

    /**
     * Autowired RestResponseEntityWrapper object
     * Used as response wrapper bean, which provides Json headers automatically
     */
    private RestResponseEntityWrapper restResponseEntityWrapper;

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getUsage()
    {
        return restResponseEntityWrapper.wrap(usageService.get(), HttpStatus.OK);
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