package org.bsoftware.ward.assets;

import lombok.Getter;
import org.bsoftware.ward.dto.Dto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * ResponseEntityWrapperAsset provides that body will extend dto objects
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class ResponseEntityWrapperAsset<T extends Dto>
{
    /**
     * Response body which extends Dto
     */
    @Getter
    private final T dto;

    /**
     * Http response code
     */
    private final HttpStatus httpStatus;

    /**
     * Wraps body and response code to ResponseEntity
     *
     * @return ResponseEntity to servlet
     */
    public ResponseEntity<?> wrap()
    {
        return new ResponseEntity<>(dto, httpStatus);
    }

    /**
     * Creates ResponseEntityWrapperAsset with body witch extends Dto
     *
     * @param dto dto object
     * @param httpStatus status of request
     */
    public ResponseEntityWrapperAsset(T dto, HttpStatus httpStatus)
    {
        this.dto = dto;
        this.httpStatus = httpStatus;
    }
}