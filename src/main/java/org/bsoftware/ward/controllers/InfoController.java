package org.bsoftware.ward.controllers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.wrappers.RestResponseEntityWrapper;
import org.bsoftware.ward.dto.implementation.ResponseDto;
import org.bsoftware.ward.services.implementation.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Validated
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
    public ResponseEntity<?> getInfo() throws Exception
    {
        if (!Ward.isFirstLaunch())
        {
            return restResponseEntityWrapper.wrap(infoService.get());
        }
        else
        {
            return restResponseEntityWrapper.wrap(new ResponseDto("Set up application first"));
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param infoService autowired InfoService object
     * @param restResponseEntityWrapper autowired RestResponseEntityWrapper object
     */
    @Autowired
    public InfoController(InfoService infoService, RestResponseEntityWrapper restResponseEntityWrapper)
    {
        this.infoService = infoService;
        this.restResponseEntityWrapper = restResponseEntityWrapper;
    }
}