package org.bsoftware.ward.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import oshi.SystemInfo;

@Configuration
public class BeanConfiguration
{
    @Bean
    public SystemInfo systemInfo()
    {
        return new SystemInfo();
    }

    @Bean
    public HttpHeaders httpHeaders()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}