package org.bsoftware.ward.controllers.api;

import org.bsoftware.ward.services.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/usage")
public class UsageController
{
    private UsageService usageService;

    @GetMapping
    public ResponseEntity<?> getUsage()
    {
        return usageService.getUsage();
    }

    @Autowired
    public UsageController(UsageService usageService)
    {
        this.usageService = usageService;
    }
}