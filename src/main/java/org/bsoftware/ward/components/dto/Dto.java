package org.bsoftware.ward.components.dto;

/**
 * Dto interface marks other classes to work with RestResponseEntityWrapper as dto
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@FunctionalInterface
public interface Dto
{
    /**
     * Resets dto values to defaults
     */
    void clear();
}