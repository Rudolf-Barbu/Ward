package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.bsoftware.ward.exceptions.ApplicationNotConfiguredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
     * Autowired UptimeService object
     * Used for getting uptime for html template
     */
    @Autowired
    private UptimeService uptimeService;

    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    @Autowired
    private UtilitiesComponent utilitiesComponent;

    /**
     * Gets project version information
     *
     * @return MavenDto with filled field
     * @throws IOException if file does not exists
     */
    private String getVersion() throws IOException
    {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/META-INF/maven/org.b-software/ward/pom.properties");

        if (inputStream != null)
        {
            properties.load(inputStream);
            String version = properties.getProperty("version");

            return "Ward: v" + version;
        }
        else
        {
            return "Developer mode";
        }
    }

    /**
     * Fills model and returns template name
     *
     * @param model strings container
     * @return template name
     */
    public String getIndex(final Model model) throws IOException, ApplicationNotConfiguredException
    {
        if (Ward.isFirstLaunch())
        {
            return "setup";
        }

        model.addAttribute("theme", utilitiesComponent.getFromIniFile("theme"));
        model.addAttribute("serverName", utilitiesComponent.getFromIniFile("serverName"));

        model.addAttribute("info", infoService.getInfo());
        model.addAttribute("uptime", uptimeService.getUptime());
        model.addAttribute("version", getVersion());

        return "index";
    }
}