package org.bsoftware.ward.services;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.bsoftware.ward.dto.*;
import org.bsoftware.ward.exceptions.ApplicationNotSetUpException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProcessorDto getProcessor()
    {
        ProcessorDto processorDto = new ProcessorDto();

        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        String name = centralProcessor.getProcessorIdentifier().getName();
        if (name.contains("@"))
        {
            name = name.substring(0, name.indexOf('@') - 1);
        }
        processorDto.setName(name.trim());

        int coreCount = centralProcessor.getLogicalProcessorCount();
        processorDto.setCoreCount(coreCount + ((coreCount > 1) ? " Cores" : " Core"));
        processorDto.setClockSpeed(getConvertedFrequency(centralProcessor.getCurrentFreq()));

        String BitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        processorDto.setBitDepth(BitDepthPrefix + "-bit");

        return processorDto;
    }

    /**
     * Gets machine information
     *
     * @return MachineDto with filled fields
     */
    private MachineDto getMachine()
    {
        MachineDto machineDto = new MachineDto();

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVersionInfo = systemInfo.getOperatingSystem().getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        machineDto.setOperatingSystem(operatingSystem.getFamily() + " " + osVersionInfo.getVersion() + ", " + osVersionInfo.getCodeName());
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
        machineDto.setProcCount(processCount + ((processCount > 1) ? " Procs" : " Proc"));

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

        Optional<HWDiskStore> hwDiskStoreOptional = hwDiskStores.stream().findFirst();
        if (hwDiskStoreOptional.isPresent())
        {
            String mainStorage = hwDiskStoreOptional.get().getModel();

            if (mainStorage.contains("(Standard disk drives)"))
            {
                mainStorage = mainStorage.substring(0, mainStorage.indexOf("(Standard disk drives)") - 1);
            }

            storageDto.setMainStorage(mainStorage.trim());
        }
        else
        {
            storageDto.setMainStorage("Undefined");
        }

        long total = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        storageDto.setTotal(getConvertedCapacity(total) + " Total");

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
    @SuppressWarnings(value = "IntegerDivisionInFloatingPointContext")
    private UptimeDto getUptime()
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
    private SetupDto getSetup() throws IOException
    {
        SetupDto setupDto = new SetupDto();
        File file = new File(Ward.SETUP_FILE_PATH);

        setupDto.setServerName(utilitiesComponent.getFromIniFile(file, "setup", "serverName"));

        return setupDto;
    }

    /**
     * Gets project version information
     *
     * @return MavenDto with filled field
     * @throws IOException if file does not exists
     */
    private ProjectDto getProject() throws IOException
    {
        ProjectDto projectDto = new ProjectDto();
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/META-INF/maven/org.b-software/ward/pom.properties");

        if (inputStream != null)
        {
            properties.load(inputStream);
            String version = properties.getProperty("version");

            projectDto.setVersion("Ward: v" + version);
        }
        else
        {
            projectDto.setVersion("Developer mode");
        }

        return projectDto;
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return InfoDto filled with server info
     */
    public InfoDto getInfo() throws Exception
    {
        if (!Ward.isFirstLaunch())
        {
            InfoDto infoDto = new InfoDto();

            infoDto.setProcessor(getProcessor());
            infoDto.setMachine(getMachine());
            infoDto.setStorage(getStorage());
            infoDto.setUptime(getUptime());
            infoDto.setSetup(getSetup());
            infoDto.setProject(getProject());

            return infoDto;
        }
        else
        {
            throw new ApplicationNotSetUpException();
        }
    }
}