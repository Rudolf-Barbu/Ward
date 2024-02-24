package dev.leons.ward.components;

import dev.leons.ward.Ward;
import org.ini4j.Ini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * UtilitiesComponent provides various functions, which are used in different classes
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Component
public class UtilitiesComponent {
    /**
     * Constant, that providing setup section name
     */
    private static final String SECTION_NAME = "setup";

    /**
     * Gets string data from ini file
     *
     * @param optionName option in section
     * @return String wth parsed data
     * @throws IOException if file does not exist
     */
    @SuppressWarnings(value = "MismatchedQueryAndUpdateOfCollection")
    public String getFromIniFile(final String optionName) throws IOException {
        final File file = new File(Ward.SETUP_FILE_PATH);

        if (file.exists()) {
            final Ini ini = new Ini(file);
            return ini.get(SECTION_NAME, optionName, String.class);
        }

        return null;
    }

    /**
     * Sets string data to the ini file
     *
     * @param optionName option in section
     * @param value value to put
     * @throws IOException if file does not exist
     */
    public void putInIniFile(final String optionName, final String value) throws IOException {
        final File file = new File(Ward.SETUP_FILE_PATH);

        if (file.exists()) {
            final Ini ini = new Ini(file);
            ini.put(SECTION_NAME, optionName, value);
            ini.store();
        } else {
            throw new IOException();
        }
    }
}