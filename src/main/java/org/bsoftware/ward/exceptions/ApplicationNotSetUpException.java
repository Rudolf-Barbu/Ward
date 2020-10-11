package org.bsoftware.ward.exceptions;

/**
 * ApplicationNotSetUpException indicates that user tried to access api, without setting up application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
public final class ApplicationNotSetUpException extends Exception
{
    /**
     * @param message exception message
     */
    public ApplicationNotSetUpException(String message)
    {
        super(message);
    }
}