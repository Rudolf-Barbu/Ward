package org.bsoftware.ward.components.dto.implementation;

import org.bsoftware.ward.components.dto.Dto;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * InfoDto is a values container for presenting server principal information
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Component
public class InfoDto implements Dto
{
    /**
     * Processor name field
     */
    private String processorName;

    /**
     * Core count field
     */
    private String coreCount;

    /**
     * Processor max frequency field
     */
    private String maxClockSpeed;

    /**
     * Processor architecture field
     */
    private String processorBitDepth;

    /**
     * Machine name usage field
     */
    private String machineName;

    /**
     * Amount of total installed ram field
     */
    private String totalRam;

    /**
     * Ram generation field
     */
    private String ramType;

    /**
     * Processes count field
     */
    private String procCount;

    /**
     * Host0 storage name field
     */
    private String storageName;

    /**
     * Host0 storage name field
     */
    private String totalStorage;

    /**
     * Amount of total installed storage field
     */
    private String diskCount;

    /**
     * Total amount of virtual memory (Swap on Linux) field
     */
    private String swapAmount;

    /**
     *  Map with uptime values field
     */
    private Map<String, String> uptime;

    /**
     * Getter for processorName field
     *
     * @return String processorName field value
     */
    public String getProcessorName()
    {
        return processorName;
    }

    /**
     * Setter for processorName field
     *
     * @param processorName new field value
     */
    public void setProcessorName(String processorName)
    {
        this.processorName = processorName;
    }

    /**
     * Getter for coreCount field
     *
     * @return String coreCount field value
     */
    public String getCoreCount()
    {
        return coreCount;
    }

    /**
     * Setter for coreCount field
     *
     * @param coreCount new field value
     */
    public void setCoreCount(String coreCount)
    {
        this.coreCount = coreCount;
    }

    /**
     * Getter for maxClockSpeed field
     *
     * @return String maxClockSpeed field value
     */
    public String getMaxClockSpeed()
    {
        return maxClockSpeed;
    }

    /**
     * Setter for maxClockSpeed field
     *
     * @param maxClockSpeed new field value
     */
    public void setMaxClockSpeed(String maxClockSpeed)
    {
        this.maxClockSpeed = maxClockSpeed;
    }

    /**
     * Getter for processorBitDepth field
     *
     * @return String processorBitDepth field value
     */
    public String getProcessorBitDepth()
    {
        return processorBitDepth;
    }

    /**
     * Setter for processorBitDepth field
     *
     * @param processorBitDepth new field value
     */
    public void setProcessorBitDepth(String processorBitDepth)
    {
        this.processorBitDepth = processorBitDepth;
    }

    /**
     * Getter for machineName field
     *
     * @return String machineName field value
     */
    public String getMachineName()
    {
        return machineName;
    }

    /**
     * Setter for machineName field
     *
     * @param machineName new field value
     */
    public void setMachineName(String machineName)
    {
        this.machineName = machineName;
    }

    /**
     * Getter for totalRam field
     *
     * @return String totalRam field value
     */
    public String getTotalRam()
    {
        return totalRam;
    }

    /**
     * Setter for totalRam field
     *
     * @param totalRam new field value
     */
    public void setTotalRam(String totalRam)
    {
        this.totalRam = totalRam;
    }

    /**
     * Getter for ramType field
     *
     * @return String ramType field value
     */
    public String getRamType()
    {
        return ramType;
    }

    /**
     * Setter for ramType field
     *
     * @param ramType new field value
     */
    public void setRamType(String ramType)
    {
        this.ramType = ramType;
    }

    /**
     * Getter for procCount field
     *
     * @return String procCount field value
     */
    public String getProcCount()
    {
        return procCount;
    }

    /**
     * Setter for procCount field
     *
     * @param procCount new field value
     */
    public void setProcCount(String procCount)
    {
        this.procCount = procCount;
    }

    /**
     * Getter for storageName field
     *
     * @return String storageName field value
     */
    public String getStorageName()
    {
        return storageName;
    }

    /**
     * Setter for storageName field
     *
     * @param storageName new field value
     */
    public void setStorageName(String storageName)
    {
        this.storageName = storageName;
    }

    /**
     * Getter for totalStorage field
     *
     * @return String totalStorage field value
     */
    public String getTotalStorage()
    {
        return totalStorage;
    }

    /**
     * Setter for totalStorage field
     *
     * @param totalStorage new field value
     */
    public void setTotalStorage(String totalStorage)
    {
        this.totalStorage = totalStorage;
    }

    /**
     * Getter for diskCount field
     *
     * @return String diskCount field value
     */
    public String getDiskCount()
    {
        return diskCount;
    }

    /**
     * Setter for diskCount field
     *
     * @param diskCount new field value
     */
    public void setDiskCount(String diskCount)
    {
        this.diskCount = diskCount;
    }

    /**
     * Getter for swapAmount field
     *
     * @return String swapAmount field value
     */
    public String getSwapAmount()
    {
        return swapAmount;
    }

    /**
     * Setter for swapAmount field
     *
     * @param swapAmount new field value
     */
    public void setSwapAmount(String swapAmount)
    {
        this.swapAmount = swapAmount;
    }

    /**
     * Getter for uptime field
     *
     * @return Map<String, String> uptime field value
     */
    public Map<String, String> getUptime()
    {
        return uptime;
    }

    /**
     * Setter for uptime field
     *
     * @param uptime new field value
     */
    public void setUptime(Map<String, String> uptime)
    {
        this.uptime = uptime;
    }

    /**
     * Resets dto values to defaults
     */
    @Override
    public void clear()
    {
        processorName = "";
        coreCount = "";
        maxClockSpeed = "";
        processorBitDepth = "";
        machineName = "";
        totalRam = "";
        ramType = "";
        procCount = "";
        storageName = "";
        totalStorage = "";
        diskCount = "";
        swapAmount = "";
        uptime = null;
    }
}