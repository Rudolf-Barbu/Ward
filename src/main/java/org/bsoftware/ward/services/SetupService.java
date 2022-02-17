package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.dto.ResponseDto;
import org.bsoftware.ward.dto.SetupDto;
import org.bsoftware.ward.exceptions.ApplicationAlreadyConfiguredException;
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
     * Constant, that providing setup section name
     */
    private static final String SECTION_NAME = "setup";

    /**
     * Puts new data in ini file
     *
     * @param file ini file
     * @param optionName option in section
     * @throws IOException if file does not exists
     */
    private void putInIniFile(final File file, final String optionName, final String value) throws IOException
    {
        Ini ini = new Ini(file);
        ini.put(SECTION_NAME, optionName, value);
        ini.store();
    }

    /**
     * Fills setup data in ini file
     *
     * @param setupDto user settings data
     * @return ResponseEntityWrapperAsset filled with ResponseDto
     * @throws IOException IoException if file is fot found, and cant be created
     */
    public ResponseDto postSetup(final SetupDto setupDto) throws IOException, ApplicationAlreadyConfiguredException
    {
        if (Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);

            if (file.createNewFile())
            {
                putInIniFile(file, "serverName", setupDto.getServerName());
                putInIniFile(file, "theme", setupDto.getTheme());
                putInIniFile(file, "port", setupDto.getPort());

                Ward.restart();
            }
            else
            {
                throw new IOException();
            }
        }
        else
        {
            throw new ApplicationAlreadyConfiguredException();
        }

        return new ResponseDto("Settings saved correctly");
    }
}