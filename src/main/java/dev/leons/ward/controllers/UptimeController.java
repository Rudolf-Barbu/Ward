package dev.leons.ward.controllers;

import dev.leons.ward.dto.UptimeDto;
import dev.leons.ward.services.UptimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SetupController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/uptime")
public class UptimeController
{
    /**
     * Autowired UptimeService object
     * Used for getting uptime information
     */
    @Autowired
    private UptimeService uptimeService;

    /**
     * Get request to display uptime information
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<UptimeDto> getUptime()
    {
        return new ResponseEntity<>(uptimeService.getUptime(), HttpStatus.OK);
    }
}