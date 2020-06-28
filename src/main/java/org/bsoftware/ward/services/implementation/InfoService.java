package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.dto.implementation.*;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.exceptions.CantGetPhysicalMemoryArrayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
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
     * Autowired utilities object
     * Used for improve values readability
     */
    private Utilities utilities;

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
        processorDto.setMaxClockSpeed(utilities.getConvertedFrequency(centralProcessor.getMaxFreq()));

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

        machineDto.setTotalRam(utilities.getConvertedCapacity(globalMemory.getTotal()) + " Ram");

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
        storageDto.setTotalStorage(utilities.getConvertedCapacity(totalStorage) + " Total");

        int diskCount = hwDiskStores.size();
        storageDto.setDiskCount(diskCount + ((diskCount > 1) ? " Disks" : " Disk"));

        storageDto.setSwapAmount(utilities.getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()) + " Swap");

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

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return InfoDto filled with server info
     */
    @Override
    @SuppressWarnings("unchecked")
    public InfoDto get() throws Exception
    {
        InfoDto infoDto = new InfoDto();

        infoDto.setProcessorDto(getProcessorDto());
        infoDto.setMachineDto(getMachineDto());
        infoDto.setStorageDto(getStorageDto());
        infoDto.setUptimeDto(getUptimeDto());

        return infoDto;
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