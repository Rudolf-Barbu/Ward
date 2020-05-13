package org.bsoftware.ward.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

@Configuration
public class BeanConfiguration
{
    @Bean
    public SystemInfo getSystemInfo()
    {
        return new SystemInfo();
    }
}