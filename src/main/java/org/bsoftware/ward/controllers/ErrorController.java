package org.bsoftware.ward.controllers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    @SuppressWarnings(value = "ConstantConditions")
    public String getError(HttpServletResponse httpServletResponse, Model model) throws Exception
    {
        if (!Ward.isFirstLaunch())
        {
            File file = new File(Ward.SETUP_FILE_PATH);
            model.addAttribute("theme", utilitiesComponent.getFromIniFile(file, "setup", "theme"));

            switch (HttpStatus.resolve(httpServletResponse.getStatus()))
            {
                case NOT_FOUND:
                {
                    return "error/404";
                }
                case METHOD_NOT_ALLOWED:
                {
                    return "error/405";
                }
                default:
                {
                    return "error/500";
                }
            }
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
     * @param utilitiesComponent autowired UtilitiesComponent object
     */
    @Autowired
    public ErrorController(UtilitiesComponent utilitiesComponent)
    {
        this.utilitiesComponent = utilitiesComponent;
    }
}