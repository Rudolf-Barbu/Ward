package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.io.File;

/**
 * IndexService displays index page of Ward application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class IndexService
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
    public IndexService(InfoService infoService, UtilitiesComponent utilitiesComponent)
    {
        this.infoService = infoService;
        this.utilitiesComponent = utilitiesComponent;
    }
}