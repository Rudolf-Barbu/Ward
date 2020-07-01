package org.bsoftware.ward.components.containers;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.entities.SettingsEntity;
import org.bsoftware.ward.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * ServletContainer used for application port changing
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class ServletContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{
    /**
     * Autowired SettingsRepository object
     * Settings repository to manipulate data
     */
    private SettingsRepository settingsRepository;

    /**
     * Customizes port of application
     *
     * @param factory servlet factory
     */
    @Override
    public void customize(TomcatServletWebServerFactory factory)
    {
        Optional<SettingsEntity> optionalSettingsEntity = settingsRepository.findSettingsEntityByName("port");

        if (!Ward.isFirstLaunch() && optionalSettingsEntity.isPresent() && !optionalSettingsEntity.get().getValue().equals(""))
        {
            factory.setPort(Integer.parseInt(optionalSettingsEntity.get().getValue()));
        }
        else
        {
            factory.setPort(4000);
        }
    }
    /**
     * Used for autowiring necessary objects
     *
     * @param settingsRepository autowired SettingsRepository object
     */
    @Autowired
    public ServletContainer(SettingsRepository settingsRepository)
    {
        this.settingsRepository = settingsRepository;
    }
}