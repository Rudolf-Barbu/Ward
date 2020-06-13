package org.bsoftware.ward.services;

import org.bsoftware.ward.dto.Dto;

/**
 * Service interface makes to all services return dto object
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@FunctionalInterface
public interface Service
{
    /**
     * Get info from the service
     *
     * @return Dto object
     */
    <T extends Dto> T get() throws Exception;
}