package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.SettingsDto;
import org.bsoftware.ward.entities.SettingsEntity;
import org.bsoftware.ward.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SettingsService manipulated settings data
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class SettingsService implements org.bsoftware.ward.services.Service
{
    /**
     * Autowired SettingsRepository object
     * Used for store info in database
     */
    private SettingsRepository settingsRepository;

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
     * Fills data in database
     *
     * @param dto user settings data
     * @param <T> determines, that only Dto object can pass
     */
    @Override
    public <T extends Dto> void post(T dto)
    {
        settingsRepository.save(convertSettingsDtoStringToSettingsEntity("theme", ((SettingsDto) dto).getTheme()));
        settingsRepository.save(convertSettingsDtoStringToSettingsEntity("port", ((SettingsDto) dto).getPort()));
        Ward.restart();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param settingsRepository autowired SettingsRepository object
     * @param utilities autowired Utilities object
     */
    @Autowired
    public SettingsService(SettingsRepository settingsRepository, Utilities utilities)
    {
        this.settingsRepository = settingsRepository;
    }
}