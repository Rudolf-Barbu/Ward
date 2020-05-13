package org.bsoftware.ward.services;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Map;

@Service
public class InfoService
{
    public Map<String, String> getProcessorInfo()
    {
        HardwareAbstractionLayer hardwareAbstractionLayer = (new SystemInfo()).getHardware();
        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
        System.out.println(centralProcessor.getMaxFreq());
        System.out.println(centralProcessor.getLogicalProcessorCount());
        System.out.println(centralProcessor.getProcessorIdentifier().getName());
        return null;
    }
}