package org.bsoftware.ward.controllers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.services.implementation.InfoService;
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
     * Used for various utility functions
     */
    private Utilities utilities;

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
    public String getIndex(Model model) throws Exception
    {
        if (Ward.isFirstLaunch())
        {
            return "setup";
        }

        model.addAttribute("infoDto", infoService.get());

        File file = new File(Ward.SETTINGS_FILE_PATH);
        model.addAttribute("theme", utilities.getFromIniFile(file, "setup", "theme"));

        return "index";
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param infoService autowired InfoService object
     * @param utilities autowired Utilities object
     */
    @Autowired
    public IndexController(InfoService infoService, Utilities utilities)
    {
        this.infoService = infoService;
        this.utilities = utilities;
    }
}