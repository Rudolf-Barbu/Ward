package dev.leons.ward.exceptions;

/**
 * ApplicationNotSetUpException indicates that user tried to access api, without setting up the application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class ApplicationNotConfiguredException extends Exception
{
    /**
     * Call super class with exception message
     */
    public ApplicationNotConfiguredException()
    {
        super("Set up application first");
    }
}