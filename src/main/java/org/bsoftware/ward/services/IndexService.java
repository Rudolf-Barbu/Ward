package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * IndexService displays index page of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Service
public class IndexService
{
    /**
     * Autowired InfoService object
     * Used for getting machine information for html template
     */
    @Autowired
    private InfoService infoService;

    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    @Autowired
    private UtilitiesComponent utilitiesComponent;

    /**
     * Fills model and returns template name
     *
     * @param model strings container
     * @return template name
     */
    public String getIndex(Model model) throws Exception
    {
        if (Ward.isFirstLaunch())
        {
            return "setup";
        }

        model.addAttribute("info", infoService.getInfo());
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        return "index";
    }
}