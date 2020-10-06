package org.bsoftware.ward.controllers;

import org.bsoftware.ward.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

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
     * Autowired ErrorService object
     * Used to determine error page
     */
    private final ErrorService errorService;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    public String getError(HttpServletResponse httpServletResponse, Model model) throws Exception
    {
        return errorService.getError(httpServletResponse, model);
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
     * @param errorService autowired ErrorService object
     */
    @Autowired
    public ErrorController(ErrorService errorService)
    {
        this.errorService = errorService;
    }
}