package org.bsoftware.ward.components.containers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

/**
 * ServletContainer used for application port changing
 * @author Rudolf Barbu
 * @version 1.0.3
 */
@Component
public class ServletContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{
    /**
     * Autowired Utilities object
     * Used for various utility functions
     */
    private Utilities utilities;

    /**
     * Customizes port of application
     *
     * @param factory servlet factory
     */
    @Override
    public void customize(TomcatServletWebServerFactory factory)
    {
        if (!Ward.isFirstLaunch())
        {
            try
            {
                File file = new File(Ward.SETTINGS_FILE_PATH);
                factory.setPort(Integer.parseInt(utilities.getFromIniFile(file, "setup", "port")));
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }
        }
        else
        {
            factory.setPort(Ward.INITIAL_PORT);
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param utilities autowired Utilities object
     */
    @Autowired
    public ServletContainer(Utilities utilities)
    {
        this.utilities = utilities;
    }
}