package org.bsoftware.ward.components.dto.implementation;

import org.bsoftware.ward.components.dto.Dto;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class InfoDto implements Dto
{
    private String processorName;

    private String coreCount;

    private String maxClockSpeed;

    private String processorBitDepth;

    private String machineName;

    private String totalRam;

    private String ramType;

    private String procCount;

    private String storageName;

    private String totalStorage;

    private String diskCount;

    private String swapAmount;

    private Map<String, String> uptime;

    public String getProcessorName()
    {
        return processorName;
    }

    public void setProcessorName(String processorName)
    {
        this.processorName = processorName;
    }

    public String getCoreCount()
    {
        return coreCount;
    }

    public void setCoreCount(String coreCount)
    {
        this.coreCount = coreCount;
    }

    public String getMaxClockSpeed()
    {
        return maxClockSpeed;
    }

    public void setMaxClockSpeed(String maxClockSpeed)
    {
        this.maxClockSpeed = maxClockSpeed;
    }

    public String getProcessorBitDepth()
    {
        return processorBitDepth;
    }

    public void setProcessorBitDepth(String processorBitDepth)
    {
        this.processorBitDepth = processorBitDepth;
    }

    public String getMachineName()
    {
        return machineName;
    }

    public void setMachineName(String machineName)
    {
        this.machineName = machineName;
    }

    public String getTotalRam()
    {
        return totalRam;
    }

    public void setTotalRam(String totalRam)
    {
        this.totalRam = totalRam;
    }

    public String getRamType()
    {
        return ramType;
    }

    public void setRamType(String ramType)
    {
        this.ramType = ramType;
    }

    public String getProcCount()
    {
        return procCount;
    }

    public void setProcCount(String procCount)
    {
        this.procCount = procCount;
    }

    public String getStorageName()
    {
        return storageName;
    }

    public void setStorageName(String storageName)
    {
        this.storageName = storageName;
    }

    public String getTotalStorage()
    {
        return totalStorage;
    }

    public void setTotalStorage(String totalStorage)
    {
        this.totalStorage = totalStorage;
    }

    public String getDiskCount()
    {
        return diskCount;
    }

    public void setDiskCount(String diskCount)
    {
        this.diskCount = diskCount;
    }

    public String getSwapAmount()
    {
        return swapAmount;
    }

    public void setSwapAmount(String swapAmount)
    {
        this.swapAmount = swapAmount;
    }

    public Map<String, String> getUptime()
    {
        return uptime;
    }

    public void setUptime(Map<String, String> uptime)
    {
        this.uptime = uptime;
    }

    @Override
    public void clear()
    {

    }
}