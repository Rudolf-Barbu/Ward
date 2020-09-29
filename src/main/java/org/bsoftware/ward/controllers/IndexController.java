package org.bsoftware.ward.controllers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.bsoftware.ward.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;

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
    private final InfoService infoService;

    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    /**
     * Get request to display index page
     *
     * @param model used for providing values in to html template
     * @return String name of html template with values from model param
     */
    @GetMapping
    public String getIndex(Model model) throws Exception
    {
        if (Ward.isFirstLaunch())
        {
            return "setup";
        }

        File file = new File(Ward.SETUP_FILE_PATH);

        model.addAttribute("infoDto", infoService.getInfo().getDto());
        model.addAttribute("theme", utilitiesComponent.getFromIniFile(file, "setup", "theme"));

        return "index";
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param infoService autowired InfoService object
     * @param utilitiesComponent autowired UtilitiesComponent object
     */
    @Autowired
    public IndexController(InfoService infoService, UtilitiesComponent utilitiesComponent)
    {
        this.infoService = infoService;
        this.utilitiesComponent = utilitiesComponent;
    }
}