package org.bsoftware.ward.controllers.api;

import org.bsoftware.ward.services.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsageController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 * @since 2020-05-17
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
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return Json string and response code
     */
    @GetMapping
    public ResponseEntity<?> getUsage()
    {
        return usageService.getUsage();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param usageService autowired UsageService object
     */
    @Autowired
    public UsageController(UsageService usageService)
    {
        this.usageService = usageService;
    }
}