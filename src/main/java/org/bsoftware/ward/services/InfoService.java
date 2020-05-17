package org.bsoftware.ward.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InfoService provides various information about machine, such as processor name, core count, Ram amount, e.t.c.
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 * @since 2020-05-17
 */
@Service
public class InfoService
{
    /**
     * Autowired SystemInfo object
     * Used for getting machine information
     */
    private SystemInfo systemInfo;

    /**
     * Takes a buffer map and put in to it processor information
     *
     * @param infoBuffer buffer for info values
     * @return Map<String, Integer>, which contain processor information
     */
    private Map<String, String> getProcessorInfo(Map<String, String> infoBuffer)
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        String processorName = centralProcessor.getProcessorIdentifier().getName();
        if (processorName.contains("@"))
        {
            processorName = processorName.substring(0, processorName.indexOf('@') - 1);
        }
        infoBuffer.put("processorName", processorName.trim());
        int coreCount = centralProcessor.getLogicalProcessorCount();
        infoBuffer.put("coreCount", coreCount + ((coreCount > 1) ? " Cores" : " Core"));
        infoBuffer.put("maxClockSpeed", (Math.round((centralProcessor.getMaxFreq() / 1E+9) * 10.0) / 10.0) + " GHz");
        String processorBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        infoBuffer.put("processorBitDepth", processorBitDepthPrefix + "-bit Arch");
        return infoBuffer;
    }

    /**
     * Takes a buffer map and put in to it machine information
     *
     * @param infoBuffer buffer for info values
     * @return Map<String, Integer>, which contain processor and machine information
     */
    private Map<String, String> getMachineInfo(Map<String, String> infoBuffer)
    {
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        infoBuffer.put("machineName", computerSystem.getModel().trim());
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long totalPhysicalMemory = globalMemory.getTotal();
        infoBuffer.put("totalRam", Math.round(totalPhysicalMemory / 1.074E+9) + " GiB Ram");
        infoBuffer.put("ramClockSpeed", Math.round(globalMemory.getPhysicalMemory().get(0).getClockSpeed() / 1e+6) + " MHz");
        infoBuffer.put("ramType", globalMemory.getPhysicalMemory().get(0).getMemoryType());
        return infoBuffer;
    }

    /**
     * Takes a buffer map and put in to it storage information
     *
     * @param infoBuffer buffer for info values
     * @return Map<String, Integer>, which contain processor, machine and storage information
     */
    private Map<String, String> getStorageInfo(Map<String, String> infoBuffer)
    {
        List<HWDiskStore> hwDiskStores = systemInfo.getHardware().getDiskStores();
        String storageName = hwDiskStores.get(0).getModel();
        if (storageName.contains("(Standard disk drives)"))
        {
            storageName = storageName.substring(0, storageName.indexOf("(Standard disk drives)") - 1);
        }
        infoBuffer.put("storageName", storageName.trim());
        long totalStorage = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        infoBuffer.put("totalStorage", Math.round(totalStorage / 1.074E+9) + " GiB Total");
        int diskCount = hwDiskStores.size();
        infoBuffer.put("diskCount", diskCount + ((diskCount > 1) ? " Disks" : " Disk"));
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        infoBuffer.put("swapAmount", globalMemory.getVirtualMemory().getSwapTotal() + " GiB Swap");
        return infoBuffer;
    }

    /**
     * Takes a buffer map and put in to it uptime information
     *
     * @param infoBuffer buffer for info values
     * @return Map<String, Integer>, which contain processor, machine, storage and uptime information
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private Map<String, String> getUptimeInfo(Map<String, String> infoBuffer)
    {
        long uptimeInSeconds = systemInfo.getOperatingSystem().getSystemUptime();
        infoBuffer.put("uptimeDays", String.format("%02d", (int) Math.floor(uptimeInSeconds / 86400)));
        infoBuffer.put("uptimeHours", String.format("%02d", (int) Math.floor((uptimeInSeconds % 86400) / 3600)));
        infoBuffer.put("uptimeMinutes", String.format("%02d", (int) Math.floor((uptimeInSeconds / 60) % 60)));
        infoBuffer.put("uptimeSeconds", String.format("%02d", (int) Math.floor(uptimeInSeconds % 60)));
        return infoBuffer;
    }

    /**
     * Used as responce builder for controller
     *
     * @return provides map with info values for html template
     */
    public Map<String, String> getInfo()
    {
        return getUptimeInfo(getStorageInfo(getMachineInfo(getProcessorInfo(new HashMap<>()))));
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     */
    @Autowired
    public InfoService(SystemInfo systemInfo)
    {
        this.systemInfo = systemInfo;
    }
}