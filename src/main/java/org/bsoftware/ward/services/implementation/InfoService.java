package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.Utilities;
import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    private final SystemInfo systemInfo;

    /**
     * Autowired Utilities object
     * Used for various utility functions
     */
    private final Utilities utilities;

    /**
     * Autowired MessageSource object
     * Used for getting messages bundle
     */
    private final MessageSource messageSource;

    /**
     * Converts frequency to most readable format
     *
     * @param hertzArray raw frequency array values in hertz for each logical processor
     * @return String with formatted frequency and postfix
     */
    private String getConvertedFrequency(long[] hertzArray)
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
        processorDto.setCoreCount(coreCount + " " + messageSource
                .getMessage("info.processor.cores".concat(((coreCount > 1) ? ".plural" : ".single")), null, Locale.getDefault()));

        processorDto.setClockSpeed(getConvertedFrequency(centralProcessor.getCurrentFreq()));

        String processorBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        processorDto.setProcessorBitDepth(processorBitDepthPrefix + "-bit");

        return processorDto;
    }

    /**
     * Gets machine information
     *
     * @return MachineDto with filled fields
     */
    private MachineDto getMachineDto()
    {
        MachineDto machineDto = new MachineDto();

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVersionInfo = systemInfo.getOperatingSystem().getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        machineDto.setOperatingSystemName(operatingSystem.getFamily() + " " + osVersionInfo.getVersion() + ", " + osVersionInfo.getCodeName());
        machineDto.setTotalRam(getConvertedCapacity(globalMemory.getTotal()) + " Ram");

        Optional<PhysicalMemory> physicalMemoryOptional = globalMemory.getPhysicalMemory().stream().findFirst();
        if (physicalMemoryOptional.isPresent())
        {
            machineDto.setRamTypeOrOSBitDepth(physicalMemoryOptional.get().getMemoryType());
        }
        else
        {
            machineDto.setRamTypeOrOSBitDepth(operatingSystem.getBitness() + "-bit");
        }

        int processCount = operatingSystem.getProcessCount();
        machineDto.setProcCount(processCount + " " + messageSource
                .getMessage("info.machine.processes".concat(((processCount > 1) ? ".plural" : ".single")), null, Locale.getDefault()));

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

        Optional<HWDiskStore> hwDiskStoreOptional = hwDiskStores.stream().findFirst();
        if (hwDiskStoreOptional.isPresent())
        {
            String storageName = hwDiskStoreOptional.get().getModel();

            if (storageName.contains("(Standard disk drives)"))
            {
                storageName = storageName.substring(0, storageName.indexOf("(Standard disk drives)") - 1);
            }

            storageDto.setStorageName(storageName.trim());
        }
        else
        {
            storageDto.setStorageName(messageSource
                    .getMessage("info.storage.undefined", null, Locale.getDefault()));
        }

        long totalStorage = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        storageDto.setTotalStorage(getConvertedCapacity(totalStorage) + " " + messageSource
                .getMessage("info.storage.total", null, Locale.getDefault()));

        int diskCount = hwDiskStores.size();
        storageDto.setDiskCount(diskCount + " " + messageSource
                .getMessage("info.storage.disks".concat(((diskCount > 1) ? ".plural" : ".single")), null, Locale.getDefault()));

        storageDto.setSwapAmount(getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()) + " Swap");

        return storageDto;
    }

    /**
     * Gets uptime information
     *
     * @return UptimeDto with filled fields
     */
    @SuppressWarnings(value = "IntegerDivisionInFloatingPointContext")
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
     * Gets server name information
     *
     * @return SetupDto with filled field
     * @throws IOException if file does not exists
     */
    private SetupDto getSetupDto() throws IOException
    {
        SetupDto setupDto = new SetupDto();
        File file = new File(Ward.SETUP_FILE_PATH);

        setupDto.setServerName(utilities.getFromIniFile(file, "setup", "serverName"));

        return setupDto;
    }

    /**
     * Gets project version information
     *
     * @return MavenDto with filled field
     * @throws IOException if file does not exists
     */
    private MavenDto getMavenDto() throws IOException
    {
        MavenDto mavenDto = new MavenDto();
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/META-INF/maven/org.b-software/ward/pom.properties");

        if (inputStream != null)
        {
            properties.load(inputStream);

            String version = properties.getProperty("version", messageSource
                    .getMessage("info.maven.version.unknown", null, Locale.getDefault()));

            mavenDto.setProjectVersion("Ward: " + version);
        }
        else
        {
            mavenDto.setProjectVersion(messageSource
                    .getMessage("info.maven.version.developer", null, Locale.getDefault()));
        }

        return mavenDto;
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return InfoDto filled with server info
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public <T extends Dto> T get() throws Exception
    {
        InfoDto infoDto = new InfoDto();

        infoDto.setProcessorDto(getProcessorDto());
        infoDto.setMachineDto(getMachineDto());
        infoDto.setStorageDto(getStorageDto());
        infoDto.setUptimeDto(getUptimeDto());
        infoDto.setSetupDto(getSetupDto());
        infoDto.setMavenDto(getMavenDto());

        return (T) infoDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     * @param utilities autowired Utilities object
     * @param messageSource autowired MessageSource object
     */
    @Autowired
    public InfoService(SystemInfo systemInfo, Utilities utilities, MessageSource messageSource)
    {
        this.systemInfo = systemInfo;
        this.utilities = utilities;
        this.messageSource = messageSource;
    }
}