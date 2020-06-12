package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.components.dto.implementation.InfoDto;
import org.bsoftware.ward.components.utilities.ConverterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InfoService provides various information about machine, such as processor name, core count, Ram amount, e.t.c.
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@Service
public class InfoService implements org.bsoftware.ward.services.Service
{
    /**
     * Autowired SystemInfo object
     * Used for getting machine information
     */
    private SystemInfo systemInfo;

    /**
     * Autowired ConverterUtility object
     * Used for improve values readability
     */
    private ConverterUtility converterUtility;

    /**
     *  Dto to store all info values
     */
    private InfoDto infoDto;

    /**
     * Takes InfoDto and put in to it processor information
     *
     */
    private void getProcessorInfo()
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        String processorName = centralProcessor.getProcessorIdentifier().getName();
        if (processorName.contains("@"))
        {
            processorName = processorName.substring(0, processorName.indexOf('@') - 1);
        }
        infoDto.setProcessorName(processorName.trim());

        int coreCount = centralProcessor.getLogicalProcessorCount();
        infoDto.setCoreCount(coreCount + ((coreCount > 1) ? " Cores" : " Core"));
        infoDto.setMaxClockSpeed(converterUtility.getConvertedFrequency(centralProcessor.getMaxFreq()));

        String processorBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        infoDto.setProcessorBitDepth(processorBitDepthPrefix + "-bit Arch");
    }

    /**
     * Takes InfoDto and put in to it machine information
     */
    private void getMachineInfo()
    {
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVersionInfo = systemInfo.getOperatingSystem().getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        infoDto.setOperatingSystemInfo(operatingSystem.getFamily() + " " + osVersionInfo.getVersion() + ", " + osVersionInfo.getCodeName());

        infoDto.setTotalRam(converterUtility.getConvertedCapacity(globalMemory.getTotal()) + " Ram");
        List<PhysicalMemory> physicalMem = globalMemory.getPhysicalMemory();
        if (physicalMem.isEmpty()) {
            infoDto.setRamType("Unknown");
        } else {
            infoDto.setRamType(physicalMem.get(0).getMemoryType());
        }

        int processCount = operatingSystem.getProcessCount();
        infoDto.setProcCount(processCount + ((processCount > 1) ? " Procs" : " Proc"));
    }

    /**
     * Takes InfoDto and put in to it storage information
     */
    private void getStorageInfo()
    {
        List<HWDiskStore> hwDiskStores = systemInfo.getHardware().getDiskStores();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        String storageName = hwDiskStores.get(0).getModel();
        if (storageName.contains("(Standard disk drives)"))
        {
            storageName = storageName.substring(0, storageName.indexOf("(Standard disk drives)") - 1);
        }
        infoDto.setStorageName(storageName.trim());

        long totalStorage = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        infoDto.setTotalStorage(converterUtility.getConvertedCapacity(totalStorage) + " Total");

        int diskCount = hwDiskStores.size();
        infoDto.setDiskCount(diskCount + ((diskCount > 1) ? " Disks" : " Disk"));

        infoDto.setSwapAmount(converterUtility.getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()) + " Swap");
    }

    /**
     * Takes InfoDto and put in to it uptime information
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private void getUptimeInfo()
    {
        long uptimeInSeconds = systemInfo.getOperatingSystem().getSystemUptime();
        Map<String, String> uptimeMap = new HashMap<>();

        uptimeMap.put("days", String.format("%02d", (int) Math.floor(uptimeInSeconds / 86400)));
        uptimeMap.put("hours", String.format("%02d", (int) Math.floor((uptimeInSeconds % 86400) / 3600)));
        uptimeMap.put("minutes", String.format("%02d", (int) Math.floor((uptimeInSeconds / 60) % 60)));
        uptimeMap.put("seconds", String.format("%02d", (int) Math.floor(uptimeInSeconds % 60)));

        infoDto.setUptime(uptimeMap);
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return completed InfoDto with server info
     */
    @Override
    @SuppressWarnings("unchecked")
    public InfoDto get()
    {
        getProcessorInfo();
        getMachineInfo();
        getStorageInfo();
        getUptimeInfo();
        return infoDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     * @param converterUtility autowired ConverterUtility object
     * @param infoDto autowired InfoDto object
     */
    @Autowired
    public InfoService(SystemInfo systemInfo, ConverterUtility converterUtility, InfoDto infoDto)
    {
        this.systemInfo = systemInfo;
        this.converterUtility = converterUtility;
        this.infoDto = infoDto;
    }
}