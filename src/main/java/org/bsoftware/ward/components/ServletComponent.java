package org.bsoftware.ward.components;

import org.bsoftware.ward.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

/**
 * ServletComponent used for application port changing
 * @author Rudolf Barbu
 * @version 1.0.3
 */
@Component
public class ServletComponent implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{
    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    @Autowired
    private UtilitiesComponent utilitiesComponent;

    /**
     * Customizes port of application
     *
     * @param tomcatServletWebServerFactory servlet factory
     */
    @Override
    public void customize(TomcatServletWebServerFactory tomcatServletWebServerFactory)
    {
        if (!Ward.isFirstLaunch())
        {
            try
            {
                File file = new File(Ward.SETUP_FILE_PATH);
                tomcatServletWebServerFactory.setPort(Integer.parseInt(utilitiesComponent.getFromIniFile(file, "setup", "port")));
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }
        }
        else
        {
            tomcatServletWebServerFactory.setPort(Ward.INITIAL_PORT);
        }
    }
}