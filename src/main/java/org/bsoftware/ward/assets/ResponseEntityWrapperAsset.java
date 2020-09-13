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
@Getter
public class ResponseEntityWrapperAsset<T extends Dto>
{
    /**
     * Response body which extends Dto
     */
    private final T body;

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
        return new ResponseEntity<>(body, httpStatus);
    }

    /**
     * Creates ResponseEntity with body witch extends Dto
     *
     * @param body dto object
     * @param httpStatus status of request
     */
    public ResponseEntityWrapperAsset(T body, HttpStatus httpStatus)
    {
        this.body = body;
        this.httpStatus = httpStatus;
    }
}