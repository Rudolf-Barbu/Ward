package org.bsoftware.ward.controllers;

import org.bsoftware.ward.exceptions.ApplicationNotSetUpException;
import org.bsoftware.ward.services.UsageService;
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
     * Autowired UsageService object
     * Used for getting usage information
     */
    @Autowired
    private UsageService usageService;

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getUsage() throws ApplicationNotSetUpException
    {
        return new ResponseEntity<>(usageService.getUsage(), HttpStatus.OK);
    }
}