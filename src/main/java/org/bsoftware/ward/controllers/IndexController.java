package org.bsoftware.ward.controllers;

import org.bsoftware.ward.services.implementation.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController displays index page of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController
{
    /**
     * Autowired InfoService object
     * Used for getting machine information for html template
     */
    private InfoService infoService;

    /**
     * Get request to display index page
     *
     * @param model used for providing values in to html template
     * @return String name of html template with values from model param
     */
    @GetMapping
    public String getDashboard(Model model)
    {
        model.addAttribute("info", infoService.get());

        return "index";
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param infoService autowired InfoService object
     */
    @Autowired
    public IndexController(InfoService infoService)
    {
        this.infoService = infoService;
    }
}