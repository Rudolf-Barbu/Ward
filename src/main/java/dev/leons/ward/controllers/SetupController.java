package dev.leons.ward.controllers;

import dev.leons.ward.dto.ResponseDto;
import dev.leons.ward.exceptions.ApplicationAlreadyConfiguredException;
import dev.leons.ward.services.SetupService;
import dev.leons.ward.dto.SetupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.io.IOException;

/**
 * SetupController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/setup")
public class SetupController
{
    /**
     * Autowired SetupService object
     * Used for posting settings information in ini file
     */
    @Autowired
    private SetupService setupService;

    /**
     * Posting setup info in database
     *
     * @param setupDto dto with data
     * @return ResponseEntity to servlet
     */
    @PostMapping
    public ResponseEntity<ResponseDto> postSetup(@RequestBody @Valid final SetupDto setupDto) throws IOException, ApplicationAlreadyConfiguredException
    {
        return new ResponseEntity<>(setupService.postSetup(setupDto), HttpStatus.OK);
    }
}