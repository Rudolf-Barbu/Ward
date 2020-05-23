package org.bsoftware.ward.components.utilities;

import org.springframework.stereotype.Component;

/**
 * ConverterUtility provides various functions for converting values
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class ConverterUtility
{
    /**
     * Converts frequency to most readable format
     *
     * @param hertz raw frequency value in hertz
     * @return String with formatted frequency and postfix
     */
    public String getConvertedFrequency(long hertz)
    {
        if ((hertz / 1E+6) > 999)
        {
            return (Math.round((hertz / 1E+9) * 10.0) / 10.0) + " GHz";
        }
        else
        {
            return Math.round(hertz / 1E+6) + " MHz";
        }
    }

    /**
     * Converts capacity to most readable format
     *
     * @param bits raw capacity value in bits
     * @return String with formatted capacity and postfix
     */
    public String getConvertedCapacity(long bits)
    {
        if ((bits / 1.049E+6) > 999)
        {
            if ((bits / 1.074E+9) > 999)
            {
                return (Math.round((bits / 1.1E+12) * 10.0) / 10.0) + " TiB";
            }
            else
            {
                return Math.round(bits / 1.074E+9) + " GiB";
            }
        }
        else
        {
            return Math.round(bits / 1.049E+6) + " MiB";
        }
    }
}