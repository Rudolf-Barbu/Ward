package dev.leons.ward.exceptions;

/**
 * ApplicationAlreadyConfiguredException indicates that user tried to access api, when application is already configured
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public class ApplicationAlreadyConfiguredException extends Exception
{
    /**
     * Call super class with exception message
     */
    public ApplicationAlreadyConfiguredException()
    {
        super("Application already configured");
    }
}
