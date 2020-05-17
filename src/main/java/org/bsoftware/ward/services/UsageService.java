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

@Service
public class UsageService
{
    private SystemInfo systemInfo;
    private HttpHeaders httpHeaders;

    private Map<String, Integer> getProcessorUsage(Map<String, Integer> infoBuffer)
    {
        long[] prevTicksArray = systemInfo.getHardware().getProcessor().getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        Util.sleep(1000);
        long[] currTicksArray = systemInfo.getHardware().getProcessor().getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        infoBuffer.put("processor",
                (int) Math.round((1 - ((double) (currIdleTicks - prevIdleTicks)) / ((double) (currTotalTicks - prevTotalTicks))) * 100));
        return infoBuffer;
    }

    private Map<String, Integer> getRamUsage(Map<String, Integer> infoBuffer)
    {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long totalMemory = globalMemory.getTotal();
        long availableMemory = globalMemory.getAvailable();
        infoBuffer.put("ram", (int) Math.round(100 - (((double) availableMemory / totalMemory) * 100)));
        return infoBuffer;
    }

    private Map<String, Integer> getStorageUsage(Map<String, Integer> infoBuffer)
    {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
        long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
        long freeStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();
        infoBuffer.put("storage", (int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100));
        return infoBuffer;
    }

    public ResponseEntity<?> getUsage()
    {
        return new ResponseEntity<>(new Gson().toJson(getStorageUsage(getRamUsage(getProcessorUsage(new HashMap<>())))),
                httpHeaders, HttpStatus.OK);
    }

    @Autowired
    public UsageService(SystemInfo systemInfo, HttpHeaders httpHeaders)
    {
        this.systemInfo = systemInfo;
        this.httpHeaders = httpHeaders;
    }
}