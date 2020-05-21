package org.bsoftware.ward.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * UsageService provides principal information of processor, RAM and storage usage to rest controller
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 * @since 2020-05-17
 */
@Service
public class UsageService
{
    /**
     * Autowired SystemInfo object
     * Used for getting usage information
     */
    private SystemInfo systemInfo;

    /**
     * Autowired HttpHeaders object
     * Used as bean, which provides Json headers automatically
     */
    private HttpHeaders httpHeaders;

    /**
     * Takes a buffer map and put in to it processor usage for last second in 0-100% range
     *
     * @param usageBuffer buffer for usage values
     * @return Map<String, Integer>, which contain processor usage
     */
    private Map<String, Integer> getProcessorUsage(Map<String, Integer> usageBuffer)
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();

        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        Util.sleep(1000);

        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];

        usageBuffer.put("processor",
                (int) Math.round((1 - ((double) (currIdleTicks - prevIdleTicks)) / ((double) (currTotalTicks - prevTotalTicks))) * 100));

        return usageBuffer;
    }

    /**
     * Takes a buffer map and put in to it current RAM usage in 0-100% range
     *
     * @param usageBuffer buffer for usage values
     * @return Map<String, Integer>, which contain processor usage and RAM usage
     */
    private Map<String, Integer> getRamUsage(Map<String, Integer> usageBuffer)
    {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        long totalMemory = globalMemory.getTotal();
        long availableMemory = globalMemory.getAvailable();
        usageBuffer.put("ram", (int) Math.round(100 - (((double) availableMemory / totalMemory) * 100)));

        return usageBuffer;
    }

    /**
     * Takes a buffer map and put in to it current storage usage in 0-100% range
     *
     * @param usageBuffer buffer for usage values
     * @return Map<String, Integer>, which contain processor usage, RAM and storage usage
     */
    private Map<String, Integer> getStorageUsage(Map<String, Integer> usageBuffer)
    {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();

        long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
        long freeStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();
        usageBuffer.put("storage", (int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100));

        return usageBuffer;
    }

    /**
     * Used as responce builder for rest controller
     *
     * @return completed response with body, Json headers and status code
     */
    public ResponseEntity<?> getUsage()
    {
        return new ResponseEntity<>(new Gson().toJson(getStorageUsage(getRamUsage(getProcessorUsage(new HashMap<>())))),
                httpHeaders, HttpStatus.OK);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param systemInfo autowired SystemInfo object
     * @param httpHeaders autowired HttpHeaders object
     */
    @Autowired
    public UsageService(SystemInfo systemInfo, HttpHeaders httpHeaders)
    {
        this.systemInfo = systemInfo;
        this.httpHeaders = httpHeaders;
    }
}