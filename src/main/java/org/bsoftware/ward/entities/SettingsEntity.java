package org.bsoftware.ward.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * SettingsEntity provides fields for database
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
public class SettingsEntity
{
    /**
     * Name of setting and id field
     */
    @Id
    @Column(name = "name")
    private String name;

    /**
     *  Value of setting field
     */
    @Column(name = "value")
    private String value;
}