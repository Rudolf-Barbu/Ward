package org.bsoftware.ward.components;

import org.bsoftware.ward.entities.SettingsEntity;
import org.bsoftware.ward.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class Utilities
{
    /**
     * Autowired SettingsRepository object
     * Settings repository to manipulate data
     */
    private SettingsRepository settingsRepository;

    /**
     * Get theme and return it to model
     *
     * @return String which contains theme
     */
    public String getApplicationTheme()
    {
        Optional<SettingsEntity> optionalSettingsEntity = settingsRepository.findSettingsEntityByName("theme");

        if (optionalSettingsEntity.isPresent() && !optionalSettingsEntity.get().getValue().equals(""))
        {
            return optionalSettingsEntity.get().getValue();
        }
        else
        {
            return "light";
        }
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param settingsRepository autowired SettingsRepository object
     */
    @Autowired
    public Utilities(SettingsRepository settingsRepository)
    {
        this.settingsRepository = settingsRepository;
    }
}