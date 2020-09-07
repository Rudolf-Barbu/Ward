package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.ResponseDto;
import org.bsoftware.ward.dto.implementation.SetupDto;
import org.ini4j.Ini;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

/**
 * SettingsService manipulated settings data
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Service
public class SetupService implements org.bsoftware.ward.services.Service
{
    /**
     * Puts new data in ini file
     *
     * @param file ini file
     * @param sectionName section in ini file
     * @param optionName option in section
     * @throws IOException if file does not exists
     */
    @SuppressWarnings(value = {"ResultOfMethodCallIgnored", "SameParameterValue"})
    private void putInIniFile(File file, String sectionName, String optionName, String value) throws IOException
    {
        file.createNewFile();
        Ini ini = new Ini(file);

        ini.put(sectionName, optionName, value);

        ini.store();
    }

    /**
     * Fills data in ini file
     *
     * @param dto user settings data
     * @param <T> determines, that only Dto object can pass
     * @param <K> determines, that only Dto object can be returned
     * @return Dto
     * @throws Exception IoException if file is fot found, and cant be created
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public <T extends Dto, K extends Dto> T post(K dto) throws Exception
    {
        if (Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);

            putInIniFile(file, "setup", "serverName", ((SetupDto) dto).getServerName());
            putInIniFile(file, "setup", "theme", ((SetupDto) dto).getTheme());
            putInIniFile(file, "setup", "language", ((SetupDto) dto).getLanguage());
            putInIniFile(file, "setup", "port", ((SetupDto) dto).getPort());

            Ward.restart();
        }

        return (T) new ResponseDto("Settings saved correctly");
    }
}