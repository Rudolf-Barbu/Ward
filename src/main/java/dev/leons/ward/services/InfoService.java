package dev.leons.ward.services;

import dev.leons.ward.Ward;
import dev.leons.ward.dto.InfoDto;
import dev.leons.ward.dto.MachineDto;
import dev.leons.ward.dto.ProcessorDto;
import dev.leons.ward.dto.StorageDto;
import dev.leons.ward.components.UtilitiesComponent;
import dev.leons.ward.exceptions.ApplicationNotConfiguredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * InfoService provides various information about machine, such as processor name, core count, Ram amount, etc.
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Service
public class InfoService
{
    /**
     * Autowired SystemInfo object
     * Used for getting machine information
     */
    @Autowired
    private SystemInfo systemInfo;

    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    @Autowired
    private UtilitiesComponent utilitiesComponent;

    /**
     * Converts frequency to most readable format
     *
     * @param hertzArray raw frequency array values in hertz for each logical processor
     * @return String with formatted frequency and postfix
     */
    private String getConvertedFrequency(final long[] hertzArray)
    {
        long totalFrequency = Arrays.stream(hertzArray).sum();
        long hertz = totalFrequency / hertzArray.length;

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
    private String getConvertedCapacity(final long bits)
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
    private ProcessorDto getProcessor() {
        ProcessorDto processorDto = new ProcessorDto();
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        // Extract processor name
        String name = centralProcessor.getProcessorIdentifier().getName().split("@")[0].trim();
        processorDto.setName(name);

        // Set core count
        int coreCount = centralProcessor.getLogicalProcessorCount();
        processorDto.setCoreCount(coreCount + (coreCount > 1 ? " Cores" : " Core"));

        // Set clock speed
        processorDto.setClockSpeed(getConvertedFrequency(centralProcessor.getCurrentFreq()));

        // Set bit depth
        String bitDepth = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64-bit" : "32-bit";
        processorDto.setBitDepth(bitDepth);

        return processorDto;
    }

    /**
     * Gets machine information
     *
     * @return MachineDto with filled fields
     */
    private MachineDto getMachine() {
        MachineDto machineDto = new MachineDto();

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVersionInfo = operatingSystem.getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        String osDescription = operatingSystem.getFamily() + " " + osVersionInfo.getVersion() + ", "
                + osVersionInfo.getCodeName();
        machineDto.setOperatingSystem(osDescription);

        long totalRam = globalMemory.getTotal();
        machineDto.setTotalRam(getConvertedCapacity(totalRam) + " Ram");

        Optional<PhysicalMemory> physicalMemoryOptional = globalMemory.getPhysicalMemory().stream().findFirst();
        String ramTypeOrOSBitDepth;
        if (physicalMemoryOptional.isPresent()) {
            ramTypeOrOSBitDepth = physicalMemoryOptional.get().getMemoryType();
        } else {
            ramTypeOrOSBitDepth = operatingSystem.getBitness() + "-bit";
        }
        machineDto.setRamTypeOrOSBitDepth(ramTypeOrOSBitDepth);

        int processCount = operatingSystem.getProcessCount();
        String procCount = processCount + ((processCount > 1) ? " Procs" : " Proc");
        machineDto.setProcCount(procCount);

        return machineDto;
    }

    /**
     * Gets storage information
     *
     * @return StorageDto with filled fields
     */
    private StorageDto getStorage()
    {
        StorageDto storageDto = new StorageDto();
        List<HWDiskStore> hwDiskStores = systemInfo.getHardware().getDiskStores();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

    // Retrieve main storage model
        String mainStorage = hwDiskStores.isEmpty() ? "Undefined" : hwDiskStores.get(0).getModel().replaceAll("\\(.+?\\)", "").trim();
        storageDto.setMainStorage(mainStorage);

        long total = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        storageDto.setTotal(getConvertedCapacity(total) + " Total");

        int diskCount = hwDiskStores.size();
        storageDto.setDiskCount(diskCount + (diskCount > 1 ? " Disks" : " Disk"));

        storageDto.setSwapAmount(getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()) + " Swap");

        return storageDto;
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return InfoDto filled with server info
     */
    public InfoDto getInfo() throws ApplicationNotConfiguredException
    {
        if (!Ward.isFirstLaunch())
        {
            InfoDto infoDto = new InfoDto();

            infoDto.setProcessor(getProcessor());
            infoDto.setMachine(getMachine());
            infoDto.setStorage(getStorage());

            return infoDto;
        }
        else
        {
            throw new ApplicationNotConfiguredException();
        }
    }
}