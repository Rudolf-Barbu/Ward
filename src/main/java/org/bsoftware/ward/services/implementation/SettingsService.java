package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.utilities.DtoUtility;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.SettingsDto;
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
     * Autowired DtoUtility object
     * Used for filling dto fields
     */
    private DtoUtility dtoUtility;

    /**
     * Fills data in database
     *
     * @param dto user settings data
     * @param <T> determines, that only Dto object can pass
     */
    @Override
    public <T extends Dto> void post(T dto)
    {
        settingsRepository.save(dtoUtility.convertSettingsDtoStringToSettingsEntity("port", ((SettingsDto) dto).getPort()));
        settingsRepository.save(dtoUtility.convertSettingsDtoStringToSettingsEntity("theme", ((SettingsDto) dto).getTheme()));

        Ward.restart();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param settingsRepository autowired SettingsRepository object
     * @param dtoUtility autowired DtoUtility object
     */
    @Autowired
    public SettingsService(SettingsRepository settingsRepository, DtoUtility dtoUtility)
    {
        this.settingsRepository = settingsRepository;
        this.dtoUtility = dtoUtility;
    }
}