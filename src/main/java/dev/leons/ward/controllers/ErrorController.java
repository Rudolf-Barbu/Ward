package dev.leons.ward.controllers;

import dev.leons.ward.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
    @Autowired
    private ErrorService errorService;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @return String name of html template
     */
    @GetMapping
    public String getError(final Model model) throws IOException
    {
        return errorService.getError(model);
    }

}