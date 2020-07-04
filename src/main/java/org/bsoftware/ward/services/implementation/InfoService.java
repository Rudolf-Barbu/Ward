package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.*;
import org.bsoftware.ward.exceptions.CantGetPhysicalMemoryArrayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * InfoService provides various information about machine, such as processor name, core count, Ram amount, etc.
 *
 * @author Rudolf Barbu
 * @version 1.0.2
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
     * Autowired Utilities object
     * Used for various utility functions
     */
    private Utilities utilities;

    /**
     * Converts frequency to most readable format
     *
     * @param hertz raw frequency value in hertz
     * @return String with formatted frequency and postfix
     */
    private String getConvertedFrequency(long hertz)
    {
        if ((hertz / 1E+6) > 999)
        {
            return (Math.round((hertz / 1E+9) * 10.0) / 10.0) + " GHz";
        }
        else
        {
            return Math.round(hertz / 1E+6) + " MHz";
        }
    }

    /**
     * Converts capacity to most readable format
     *
     * @param bits raw capacity value in bits
     * @return String with formatted capacity and postfix
     */
    private String getConvertedCapacity(long bits)
    {
        if ((bits / 1.049E+6) > 999)
        {
            if ((bits / 1.074E+9) > 999)
            {
                return (Math.round((bits / 1.1E+12) * 10.0) / 10.0) + " TiB";
            }
            else
            {
                return Math.round(bits / 1.074E+9) + " GiB";
            }
        }
        else
        {
            return Math.round(bits / 1.049E+6) + " MiB";
        }
    }

    /**
     * Gets processor information
     *
     * @return ProcessorDto with filled fields
     */
    private ProcessorDto getProcessorDto()
    {
        ProcessorDto processorDto = new ProcessorDto();

        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        String processorName = centralProcessor.getProcessorIdentifier().getName();
        if (processorName.contains("@"))
        {
            processorName = processorName.substring(0, processorName.indexOf('@') - 1);
        }
        processorDto.setProcessorName(processorName.trim());

        int coreCount = centralProcessor.getLogicalProcessorCount();
        processorDto.setCoreCount(coreCount + ((coreCount > 1) ? " Cores" : " Core"));
        processorDto.setMaxClockSpeed(getConvertedFrequency(centralProcessor.getMaxFreq()));

        String processorBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        processorDto.setProcessorBitDepth(processorBitDepthPrefix + "-bit Arch");

        return processorDto;
    }

    /**
     * Gets machine information
     *
     * @return MachineDto with filled fields
     */
    private MachineDto getMachineDto() throws Exception
    {
        MachineDto machineDto = new MachineDto();

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVersionInfo = systemInfo.getOperatingSystem().getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        machineDto.setOperatingSystemInfo(operatingSystem.getFamily() + " " + osVersionInfo.getVersion() + ", " + osVersionInfo.getCodeName());

        machineDto.setTotalRam(getConvertedCapacity(globalMemory.getTotal()) + " Ram");

        List<PhysicalMemory> physicalMemory = globalMemory.getPhysicalMemory();
        if (physicalMemory.isEmpty())
        {
            throw new CantGetPhysicalMemoryArrayException();
        }
        machineDto.setRamType(physicalMemory.stream().findAny().get().getMemoryType());

        int processCount = operatingSystem.getProcessCount();
        machineDto.setProcCount(processCount + ((processCount > 1) ? " Procs" : " Proc"));

        return machineDto;
    }

    /**
     * Gets storage information
     *
     * @return StorageDto with filled fields
     */
    private StorageDto getStorageDto()
    {
        StorageDto storageDto = new StorageDto();

        List<HWDiskStore> hwDiskStores = systemInfo.getHardware().getDiskStores();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        String storageName = hwDiskStores.get(0).getModel();
        if (storageName.contains("(Standard disk drives)"))
        {
            storageName = storageName.substring(0, storageName.indexOf("(Standard disk drives)") - 1);
        }
        storageDto.setStorageName(storageName.trim());

        long totalStorage = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        storageDto.setTotalStorage(getConvertedCapacity(totalStorage) + " Total");

        int diskCount = hwDiskStores.size();
        storageDto.setDiskCount(diskCount + ((diskCount > 1) ? " Disks" : " Disk"));

        storageDto.setSwapAmount(getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()) + " Swap");

        return storageDto;
    }

    /**
     * Gets uptime information
     *
     * @return UptimeDto with filled fields
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private UptimeDto getUptimeDto()
    {
        UptimeDto uptimeDto = new UptimeDto();

        long uptimeInSeconds = systemInfo.getOperatingSystem().getSystemUptime();

        uptimeDto.setDays(String.format("%02d", (int) Math.floor(uptimeInSeconds / 86400)));
        uptimeDto.setHours(String.format("%02d", (int) Math.floor((uptimeInSeconds % 86400) / 3600)));
        uptimeDto.setMinutes(String.format("%02d", (int) Math.floor((uptimeInSeconds / 60) % 60)));
        uptimeDto.setSeconds(String.format("%02d", (int) Math.floor(uptimeInSeconds % 60)));

        return uptimeDto;
    }

    private SettingsDto getSettingsDto() throws IOException
    {
        SettingsDto settingsDto = new SettingsDto();
        File file = new File(Ward.SETTINGS_FILE_PATH);

        settingsDto.setServerName(utilities.getFromIniFile(file, "settings", "serverName").toUpperCase());

        return settingsDto;
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return InfoDto filled with server info
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Dto> T get() throws Exception
    {
        InfoDto infoDto = new InfoDto();

        infoDto.setProcessorDto(getProcessorDto());
        infoDto.setMachineDto(getMachineDto());
        infoDto.setStorageDto(getStorageDto());
        infoDto.setUptimeDto(getUptimeDto());
        infoDto.setSettingsDto(getSettingsDto());

        return (T) infoDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     * @param utilities autowired Utilities object
     */
    @Autowired
    public InfoService(SystemInfo systemInfo, Utilities utilities)
    {
        this.systemInfo = systemInfo;
        this.utilities = utilities;
    }
}