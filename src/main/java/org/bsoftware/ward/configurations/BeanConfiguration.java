package org.bsoftware.ward.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

/**
 * BeanConfiguration provides bean configuration for classes, which are not components
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Configuration
public class BeanConfiguration
{
    /**
     * @return SystemInfo object
     */
    @Bean
    public SystemInfo systemInfo()
    {
        return new SystemInfo();
    }
}