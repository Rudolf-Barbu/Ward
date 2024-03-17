package dev.leons.ward.services;

import dev.leons.ward.Ward;
import dev.leons.ward.dto.UsageDto;
import dev.leons.ward.exceptions.ApplicationNotConfiguredException;
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
public class UsageService
{
    /**
     * Autowired SystemInfo object
     * Used for getting usage information
     */
    @Autowired
    private SystemInfo systemInfo;

    /**
     * Gets processor usage
     *
     * @return int that display processor usage
     */
    private int getProcessor() {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        Util.sleep(1000);

        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        long idleTicksDelta = currIdleTicks - prevIdleTicks;
        long totalTicksDelta = currTotalTicks - prevTotalTicks;

        // Handle possible division by zero
        if (totalTicksDelta == 0) {
            return 0; // or handle in a way suitable for your application
        }

        // Calculate CPU usage percentage
        return (int) ((1 - (double) idleTicksDelta / totalTicksDelta) * 100);
    }

    /**
     * Gets ram usage
     *
     * @return int that display ram usage
     */
    private int getRam() {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long totalMemory = globalMemory.getTotal();
        long availableMemory = globalMemory.getAvailable();

        // Handle possible division by zero
        if (totalMemory == 0) {
            return 0; // or handle in a way suitable for your application
        }

        // Calculate RAM usage percentage
        return (int) (100 - ((double) availableMemory / totalMemory * 100));
    }

    /**
     * Gets storage usage
     *
     * @return int that display storage usage
     */
    private int getStorage() {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();

        // Calculate total storage and free storage for all drives
        long totalStorage = 0;
        long freeStorage = 0;
        for (OSFileStore fileStore : fileSystem.getFileStores()) {
            totalStorage += fileStore.getTotalSpace();
            freeStorage += fileStore.getFreeSpace();
        }

        // Handle possible division by zero
        if (totalStorage == 0) {
            return 0; // or handle in a way suitable for your application
        }

        // Calculate total storage usage percentage for all drives
        return (int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100);
    }

    /**
     * Used to deliver dto to corresponding controller
     *
     * @return ResponseEntityWrapperAsset filled with usageDto
     */
    public UsageDto getUsage() throws ApplicationNotConfiguredException
    {
        if (!Ward.isFirstLaunch())
        {
            UsageDto usageDto = new UsageDto();

            usageDto.setProcessor(getProcessor());
            usageDto.setRam(getRam());
            usageDto.setStorage(getStorage());

            return usageDto;
        }
        else
        {
            throw new ApplicationNotConfiguredException();
        }
    }
}