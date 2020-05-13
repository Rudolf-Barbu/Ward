package org.bsoftware.ward.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService
{
    private SystemInfo systemInfo;

    private Map<String, String> getProcessorInfo(Map<String, String> infoBuffer)
    {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        infoBuffer.put("modelName", centralProcessor.getProcessorIdentifier().getName());
        String coreCountPostfix = (centralProcessor.getLogicalProcessorCount() > 1) ? " Cores" : " Core";
        infoBuffer.put("coreCount", centralProcessor.getLogicalProcessorCount() + coreCountPostfix);
        infoBuffer.put("maxClockSpeed", (Math.round((centralProcessor.getMaxFreq() / 1E+9) * 10.0) / 10.0) + " GHz");
        String cpuBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        infoBuffer.put("cpuBitDepth", cpuBitDepthPrefix + "-bit Arch");
        return infoBuffer;
    }

    private Map<String, String> getMachineInfo(Map<String, String> infoBuffer)
    {
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        infoBuffer.put("machineName", computerSystem.getModel().trim());
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long totalPhysicalMemory = globalMemory.getPhysicalMemory().stream().mapToLong(PhysicalMemory::getCapacity).sum();
        infoBuffer.put("totalPhysicalMemory", Math.round(totalPhysicalMemory / 1.074E+9) + " GiB Ram");
        infoBuffer.put("physicalMemoryClockSpeed", Math.round(globalMemory.getPhysicalMemory().get(0).getClockSpeed() / 1e+6) + " MHz");
        infoBuffer.put("physicalMemoryType", globalMemory.getPhysicalMemory().get(0).getMemoryType());
        return infoBuffer;
    }

    private Map<String, String> getStorageInfo(Map<String, String> infoBuffer)
    {
        return infoBuffer;
    }

    private Map<String, String> getUptimeInfo(Map<String, String> infoBuffer)
    {
        return infoBuffer;
    }

    public Map<String, String> getInfo()
    {
        return getUptimeInfo(getStorageInfo(getMachineInfo(getProcessorInfo(new HashMap<>()))));
    }

    @Autowired
    public InfoService(SystemInfo systemInfo)
    {
        this.systemInfo = systemInfo;
    }
}