package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.SettingsDto;
import org.ini4j.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

/**
 * SettingsService manipulated settings data
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Service
public class SettingsService implements org.bsoftware.ward.services.Service
{
    /**
     * Puts new data in ini file
     *
     * @param file ini file
     * @param sectionName section in ini filr
     * @param optionName option in section
     * @throws IOException if file does not exists
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void putInIniFile(File file, String sectionName, String optionName, String value) throws IOException
    {
        file.createNewFile();
        Ini ini = new Ini(file);

        ini.put(sectionName, optionName, value);

        ini.store();
    }

    /**
     * Fills data in database
     *
     * @param dto user settings data
     * @param <T> determines, that only Dto object can pass
     */
    @Override
    public <T extends Dto> void post(T dto) throws Exception
    {
        File file = new File("settings.ini");

        putInIniFile(file, "settings", "serverName", ((SettingsDto) dto).getServerName());
        putInIniFile(file, "settings", "theme", ((SettingsDto) dto).getTheme());
        putInIniFile(file, "settings", "port", ((SettingsDto) dto).getPort());

        Ward.restart();
    }
}