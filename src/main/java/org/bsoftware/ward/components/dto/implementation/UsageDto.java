package org.bsoftware.ward.components.dto.implementation;

import org.bsoftware.ward.components.dto.Dto;
import org.springframework.stereotype.Component;

/**
 * UsageDto is a values container for presenting server usage
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class UsageDto implements Dto
{
    /**
     * Processor usage field
     */
    private int processor;

    /**
     * Ram usage field
     */
    private int ram;

    /**
     * Storage usage field
     */
    private int storage;

    /**
     * Getter for processor field
     *
     * @return int processor field value
     */
    public int getProcessor()
    {
        return processor;
    }

    /**
     * Setter for processor field
     *
     * @param processor new field value
     */
    public void setProcessor(int processor)
    {
        this.processor = processor;
    }

    /**
     * Getter for ram field
     *
     * @return int ram field value
     */
    public int getRam()
    {
        return ram;
    }

    /**
     * Setter for ram field
     *
     * @param ram new field value
     */
    public void setRam(int ram)
    {
        this.ram = ram;
    }

    /**
     * Getter for storage field
     *
     * @return int storage field value
     */
    public int getStorage()
    {
        return storage;
    }

    /**
     * Setter for processor field
     *
     * @param storage new field value
     */
    public void setStorage(int storage)
    {
        this.storage = storage;
    }

    /**
     * Resets dto values to defaults
     */
    @Override
    public void clear()
    {
        processor = 0;
        ram = 0;
        storage = 0;
    }
}