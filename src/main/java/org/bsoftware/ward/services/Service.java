package org.bsoftware.ward.services;

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
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto> T get() throws Exception
    {
        return null;
    }

    /**
     * Get info from the service, for correspondingly request status code
     *
     * @param httpServletResponse request status code
     * @param <T> generic type, which extends Dto class
     * @return Dto object
     */
    default <T extends Dto> T get(HttpServletResponse httpServletResponse)
    {
        return null;
    }

    /**
     * Post data to service and get the response
     *
     * @param dto data to post
     * @param <T> generic type, which extends Dto class
     * @param <K> generic type, which extends Dto class
     * @return @return Dto object
     * @throws Exception unexpected exception, which will forwarded to controller
     */
    default <T extends Dto, K extends Dto> K post(T dto) throws Exception
    {
        return null;
    }
}