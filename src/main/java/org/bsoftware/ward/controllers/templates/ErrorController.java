package org.bsoftware.ward.controllers.templates;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.services.implementation.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * ErrorController displays error pages of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
    /**
     * Autowired InfoService object
     * Used for getting machine information for html template
     */
    private final ErrorService errorService;

    /**
     * Autowired Utilities object
     * Used for various utility functions
     */
    private final Utilities utilities;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    public String getError(HttpServletResponse httpServletResponse, Model model) throws Exception
    {
        if (!Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);

            model.addAttribute("errorDto", errorService.get(httpServletResponse));
            model.addAttribute("theme", utilities.getFromIniFile(file, "setup", "theme"));

            return "error";
        }
        else
        {
            return "setup";
        }
    }

    /**
     * Returns error path
     *
     * @return String error path
     */
    @Override
    public String getErrorPath()
    {
        return "/error";
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param utilities autowired Utilities object
     */
    @Autowired
    public ErrorController(ErrorService errorService, Utilities utilities)
    {
        this.errorService = errorService;
        this.utilities = utilities;
    }
}