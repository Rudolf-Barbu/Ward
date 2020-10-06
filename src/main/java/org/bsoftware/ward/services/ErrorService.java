package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * ErrorService displays error pages of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class ErrorService
{
    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    /**
     * Returns error page
     *
     * @param httpServletResponse current responce
     * @param model container for strings
     * @return template name
     * @throws IOException if ini file is unreachable
     */
    public String getError(HttpServletResponse httpServletResponse, Model model) throws IOException
    {
        if (!Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);
            model.addAttribute("theme", utilitiesComponent.getFromIniFile(file, "setup", "theme"));

            if (HttpStatus.resolve(httpServletResponse.getStatus()) == HttpStatus.NOT_FOUND)
            {
                return "error/404";
            }
            else
            {
                return "error/500";
            }
        }
        else
        {
            return "setup";
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param utilitiesComponent autowired UtilitiesComponent object
     */
    public ErrorService(UtilitiesComponent utilitiesComponent)
    {
        this.utilitiesComponent = utilitiesComponent;
    }
}