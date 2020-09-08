package org.bsoftware.ward.components;

import org.bsoftware.ward.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * RestResponseEntityComponent provides Json headers automatically and controls dto response objects
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class RestResponseEntityComponent
{
    /**
     * Autowired HttpHeaders object
     * Used as a bean, which already provides Json headers
     */
    private final HttpHeaders httpHeaders;

    /**
     *  Wrapping Json headers, status and specified dto to ResponseEntity
     *
     * @param body object, which implements Dto interface
     * @return ResponseEntity object with Json headers and Dto
     */
    public <T extends Dto> ResponseEntity<?> wrap(T body)
    {
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.OK);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param httpHeaders autowired HttpHeaders object
     */
    @Autowired
    public RestResponseEntityComponent(HttpHeaders httpHeaders)
    {
        this.httpHeaders = httpHeaders;
    }
}