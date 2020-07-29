package org.bsoftware.ward.components;

import org.ini4j.Ini;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

/**
 * Utilities used various functions, which are used in different classes
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Component
public class Utilities
{
    /**
     * Gets string data from ini file
     *
     * @param file ini file
     * @param sectionName section in ini filr
     * @param optionName option in section
     * @return String wth parsed data
     * @throws IOException if file does not exists
     */
    @SuppressWarnings(value = "MismatchedQueryAndUpdateOfCollection")
    public String getFromIniFile(File file, String sectionName, String optionName) throws IOException
    {
        if (file.exists())
        {
            Ini ini = new Ini(file);

            return ini.get(sectionName, optionName, String.class);
        }

        return null;
    }
}