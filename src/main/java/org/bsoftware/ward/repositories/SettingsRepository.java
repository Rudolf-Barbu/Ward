package org.bsoftware.ward.repositories;

import org.bsoftware.ward.entities.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * SettingsRepository is a repository which works with settings table
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Repository
public interface SettingsRepository extends JpaRepository<SettingsEntity, String>
{
    /**
     * Searches in database and returns SettingsEntity
     *
     * @param name search key
     * @return SettingsEntity for corresponding key
     */
    Optional<SettingsEntity> findSettingsEntityByName(String name);
}