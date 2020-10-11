package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.dto.ResponseDto;
import org.bsoftware.ward.dto.SetupDto;
import org.ini4j.Ini;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

/**
 * SetupService manipulating setup data
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Service
public class SetupService
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
     * Fills setup data in ini file
     *
     * @param setupDto user settings data
     * @return ResponseEntityWrapperAsset filled with ResponseDto
     * @throws Exception IoException if file is fot found, and cant be created
     */
    public ResponseDto postSetup(SetupDto setupDto) throws Exception
    {
        if (Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);

            putInIniFile(file, "setup", "serverName", setupDto.getServerName());
            putInIniFile(file, "setup", "theme", setupDto.getTheme());
            putInIniFile(file, "setup", "port", setupDto.getPort());

            Ward.restart();
        }

        return new ResponseDto("Settings saved correctly");
    }
}