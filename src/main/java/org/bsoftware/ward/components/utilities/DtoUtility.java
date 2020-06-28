package org.bsoftware.ward.components.utilities;

import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.entities.SettingsEntity;
import org.springframework.stereotype.Component;

/**
 * DtoUtility provides methods for manipulating with dtos
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class DtoUtility
{
    /**
     * Converts dto value to entity object
     *
     * @param key String name of setting
     * @param value String Value of setting
     * @param <T> determines, that only Dto object can pass
     * @return SettingsEntity object
     */
    public <T extends Dto> SettingsEntity convertSettingsDtoStringToSettingsEntity(String key, String value)
    {
        SettingsEntity settingsEntity = new SettingsEntity();

        settingsEntity.setName(key);
        settingsEntity.setValue(value);

        return settingsEntity;
    }
}