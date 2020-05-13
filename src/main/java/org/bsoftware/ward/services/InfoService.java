package org.bsoftware.ward.services;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService
{
    private Map<String, String> getProcessorInfo(Map<String, String> infoBuffer)
    {
        CentralProcessor centralProcessor = new SystemInfo().getHardware().getProcessor();
        infoBuffer.put("modelName", centralProcessor.getProcessorIdentifier().getName());
        String coreCountPostfix = (centralProcessor.getLogicalProcessorCount() > 1) ? " Cores" : " Core";
        infoBuffer.put("coreCount", centralProcessor.getLogicalProcessorCount() + coreCountPostfix);
        infoBuffer.put("maxClockSpeed", Math.round((centralProcessor.getMaxFreq() / 1E+9) * 100.0) / 100.0 + " GHz");
        String cpuBitDepthPrefix = centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32";
        infoBuffer.put("cpuBitDepth", cpuBitDepthPrefix + "-bit");
        return infoBuffer;
    }

    private Map<String, String> getMachineInfo(Map<String, String> infoBuffer)
    {
        return infoBuffer;
    }

    private Map<String, String> getStorageInfo(Map<String, String> infoBuffer)
    {
        return infoBuffer;
    }

    public Map<String, String> getInfo()
    {
        return getStorageInfo(getMachineInfo(getProcessorInfo(new HashMap<>())));
    }
}