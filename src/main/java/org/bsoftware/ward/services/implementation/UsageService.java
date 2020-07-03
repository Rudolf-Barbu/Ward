package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.UsageDto;
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
 * @version 1.0.3
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
     * Gets processor usage
     *
     * @return int that display processor usage
     */
    private int getProcessorUsage()
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        Util.sleep(1000);

        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        return (int) Math.round((1 - ((double) (currIdleTicks - prevIdleTicks)) / ((double) (currTotalTicks - prevTotalTicks))) * 100);
    }

    /**
     * Gets ram usage
     *
     * @return int that display ram usage
     */
    private int getRamUsage()
    {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        long totalMemory = globalMemory.getTotal();
        long availableMemory = globalMemory.getAvailable();

        return (int) Math.round(100 - (((double) availableMemory / totalMemory) * 100));
    }

    /**
     * Gets storage usage
     *
     * @return int that display storage usage
     */
    private int getStorageUsage()
    {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();

        long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
        long freeStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();

        return (int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100);
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return UsageDto filled with usage info
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Dto> T get()
    {
        UsageDto usageDto = new UsageDto();

        usageDto.setProcessorUsage(getProcessorUsage());
        usageDto.setRamUsage(getRamUsage());
        usageDto.setStorageUsage(getStorageUsage());

        return (T) usageDto;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     */
    @Autowired
    public UsageService(SystemInfo systemInfo)
    {
        this.systemInfo = systemInfo;
    }
}