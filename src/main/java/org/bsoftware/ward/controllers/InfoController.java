package org.bsoftware.ward.controllers;

import org.bsoftware.ward.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/info")
public class InfoController
{
    /**
     * Autowired InfoService object
     * Used for getting information about server
     */
    private final InfoService infoService;

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getInfo() throws Exception
    {
        return infoService.getInfo().wrap();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param infoService autowired InfoService object
     */
    @Autowired
    public InfoController(InfoService infoService)
    {
        this.infoService = infoService;
    }
}