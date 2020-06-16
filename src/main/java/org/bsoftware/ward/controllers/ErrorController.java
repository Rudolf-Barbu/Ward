package org.bsoftware.ward.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

/**
 * ErrorController displays error pages of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
    /**
     * Error code for page not found exception
     */
    private static final int NOT_FOUND = 404;

    /**
     * Error code for internal server error exception
     */
    private static final int INTERNAL_SERVER_ERROR = 500;

    /**
     * Used for determine application theme from properties file
     */
    @Value(value = "${ward.theme.name}")
    private String themeName;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    public String getError(HttpServletResponse httpServletResponse, Model model)
    {
        model.addAttribute("theme", themeName);

        switch (httpServletResponse.getStatus())
        {
            case ErrorController.NOT_FOUND:
            {
                return "error/404";
            }
            case INTERNAL_SERVER_ERROR:
            {
                return "error/500";
            }
            default:
            {
                return "500";
            }
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
}