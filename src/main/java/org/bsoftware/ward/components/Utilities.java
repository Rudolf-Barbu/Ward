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
     * Converts frequency to most readable format
     *
     * @param hertz raw frequency value in hertz
     * @return String with formatted frequency and postfix
     */
    public String getConvertedFrequency(long hertz)
    {
        if ((hertz / 1E+6) > 999)
        {
            return (Math.round((hertz / 1E+9) * 10.0) / 10.0) + " GHz";
        }
        else
        {
            return Math.round(hertz / 1E+6) + " MHz";
        }
    }

    /**
     * Converts capacity to most readable format
     *
     * @param bits raw capacity value in bits
     * @return String with formatted capacity and postfix
     */
    public String getConvertedCapacity(long bits)
    {
        if ((bits / 1.049E+6) > 999)
        {
            if ((bits / 1.074E+9) > 999)
            {
                return (Math.round((bits / 1.1E+12) * 10.0) / 10.0) + " TiB";
            }
            else
            {
                return Math.round(bits / 1.074E+9) + " GiB";
            }
        }
        else
        {
            return Math.round(bits / 1.049E+6) + " MiB";
        }
    }

    /**
     * Converts dto value to entity object
     *
     * @param key String name of setting
     * @param value String Value of setting
     * @return SettingsEntity object
     */
    public SettingsEntity convertSettingsDtoStringToSettingsEntity(String key, String value)
    {
        SettingsEntity settingsEntity = new SettingsEntity();

        settingsEntity.setName(key);
        settingsEntity.setValue(value);

        return settingsEntity;
    }

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