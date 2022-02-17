package org.bsoftware.ward.components;

import org.bsoftware.ward.Ward;
import org.ini4j.Ini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * UtilitiesComponent provides various functions, which are used in different classes
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Component
public class UtilitiesComponent
{
    /**
     * Constant, that providing setup section name
     */
    private static final String SECTION_NAME = "setup";

    /**
     * Gets string data from ini file
     *
     * @param optionName option in section
     * @return String wth parsed data
     * @throws IOException if file does not exists
     */
    @SuppressWarnings(value = "MismatchedQueryAndUpdateOfCollection")
    public String getFromIniFile(final String optionName) throws IOException
    {
        File file = new File(Ward.SETUP_FILE_PATH);

        if (file.exists())
        {
            Ini ini = new Ini(file);
            return ini.get(SECTION_NAME, optionName, String.class);
        }

        return null;
    }
}