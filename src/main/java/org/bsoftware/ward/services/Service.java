package org.bsoftware.ward.services;

import org.bsoftware.ward.assets.ResponseEntityWrapperAsset;
import org.bsoftware.ward.dto.Dto;
import javax.servlet.http.HttpServletResponse;

/**
 * Service interface makes to all services return dto object
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
public interface Service
{
    /**
     * Get info from the service
     *
     * @return ResponseEntityWrapperAsset which contain dto and result status
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default ResponseEntityWrapperAsset<?> get() throws Exception
    {
        return null;
    }

    /**
     * Get info from the service, for correspondingly request status code
     *
     * @param httpServletResponse request status code
     * @return ResponseEntityWrapperAsset which contain dto and result status
     */
    default ResponseEntityWrapperAsset<?> get(HttpServletResponse httpServletResponse)
    {
        return null;
    }

    /**
     * Post data to service and get the response
     *
     * @param dto data to post
     * @param <T> generic type, which extends Dto class
     * @return ResponseEntityWrapperAsset which contain dto and result status
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto> ResponseEntityWrapperAsset<?> post(T dto) throws Exception
    {
        return null;
    }
}