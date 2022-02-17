package dev.leons.ward.controllers;

import dev.leons.ward.exceptions.ApplicationNotConfiguredException;
import dev.leons.ward.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * IndexController displays index page of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Controller
@RequestMapping(value = "/")
public class IndexController
{
    /**
     * Autowired IndexService object
     * Used for getting index page template
     */
    @Autowired
    private IndexService indexService;

    /**
     * Get request to display index page
     *
     * @param model used for providing values in to html template
     * @return String name of html template with values from model param
     */
    @GetMapping
    public String getIndex(final Model model) throws IOException, ApplicationNotConfiguredException
    {
        return indexService.getIndex(model);
    }
}