package org.bsoftware.ward.controllers;

import org.bsoftware.ward.components.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

/**
 * ErrorController displays error pages of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
    /**
     * Autowired InfoService object
     * Used for various utility functions
     */
    private Utilities utilities;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    @SuppressWarnings("ConstantConditions")
    public String getError(HttpServletResponse httpServletResponse, Model model)
    {
        final HttpStatus httpStatus = HttpStatus.resolve(httpServletResponse.getStatus());
        model.addAttribute("theme", utilities.getApplicationTheme());

        switch (httpStatus)
        {
            case NOT_FOUND:
            {
                return "error/404";
            }
            case METHOD_NOT_ALLOWED:
            {
                return "error/405";
            }
            case INTERNAL_SERVER_ERROR:
            {
                return "error/500";
            }
            default:
            {
                return "index";
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

    /**
     * Used for autowiring necessary objects
     *
     * @param utilities autowired Utilities object
     */
    @Autowired
    public ErrorController(Utilities utilities)
    {
        this.utilities = utilities;
    }
}