package dev.leons.ward.controllers;

import dev.leons.ward.dto.InfoDto;
import dev.leons.ward.exceptions.ApplicationNotConfiguredException;
import dev.leons.ward.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoController displays responses from rest API, about server
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
    @Autowired
    private InfoService infoService;

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<InfoDto> getInfo() throws ApplicationNotConfiguredException
    {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }
}