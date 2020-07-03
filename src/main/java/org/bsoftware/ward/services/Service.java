package org.bsoftware.ward.services;

import org.bsoftware.ward.dto.Dto;

/**
 * Service interface makes to all services return dto object
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public interface Service
{
    /**
     * Get info from the service
     *
     * @return Dto object
     */
    default <T extends Dto> T get() throws Exception
    {
        return null;
    }

    /**
     * Get info from user and managing it
     */
    @SuppressWarnings("UnusedReturnValue")
    default <T, K extends Dto> K post(T dto) throws Exception
    {
        return null;
    }
}