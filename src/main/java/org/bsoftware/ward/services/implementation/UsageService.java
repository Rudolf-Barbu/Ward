package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.components.dto.implementation.UsageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.Util;
import java.util.Arrays;

/**
 * UsageService provides principal information of processor, RAM and storage usage to rest controller
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Service
public class UsageService implements org.bsoftware.ward.services.Service
{
    /**
     * Autowired SystemInfo object
     * Used for getting usage information
     */
    private SystemInfo systemInfo;

    /**
     *  Dto to store all usage values
     */
    private UsageDto usageDto;

    /**
     * Takes UsageDto and put in to it processor usage for last second in 0-100% range
     */
    private void getProcessorUsage()
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        Util.sleep(1000);

        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        usageDto.setProcessor((int) Math.round((1 - ((double) (currIdleTicks - prevIdleTicks)) / ((double) (currTotalTicks - prevTotalTicks))) * 100));
    }

    /**
     * Takes UsageDto and put in to it current RAM usage in 0-100% range
     */
    private void getRamUsage()
    {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        long totalMemory = globalMemory.getTotal();
        long availableMemory = globalMemory.getAvailable();

        usageDto.setRam((int) Math.round(100 - (((double) availableMemory / totalMemory) * 100)));
    }

    /**
     * Takes UsageDto and put in to it current storage usage in 0-100% range
     */
    private void getStorageUsage()
    {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();

        long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
        long freeStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();

        usageDto.setStorage((int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100));
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return completed UsageDto with server usage info
     */
    @Override
    @SuppressWarnings("unchecked")
    public UsageDto get()
    {
        usageDto.clear();
        getProcessorUsage();
        getRamUsage();
        getStorageUsage();
        return usageDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     * @param usageDto autowired UsageDto object
     */
    @Autowired
    public UsageService(SystemInfo systemInfo, UsageDto usageDto)
    {
        this.systemInfo = systemInfo;
        this.usageDto = usageDto;
    }
}