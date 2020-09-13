package org.bsoftware.ward.configurations;

import org.bsoftware.ward.Ward;
import org.bsoftware.ward.components.UtilitiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import oshi.SystemInfo;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

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
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    /**
     * @return SystemInfo object
     */
    @Bean
    public SystemInfo systemInfo()
    {
        return new SystemInfo();
    }

    /**
     * @return ResourceBundleMessageSource object
     */
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");

        return resourceBundleMessageSource;
    }

    /**
     * Sets default locale for Locale and LocaleResolver
     *
     * @return LocaleResolver which used for right language displaying in Thymeleaf templates
     * @throws IOException if file is unavailable, but was accessed
     */
    @Bean
    public LocaleResolver localeResolver() throws IOException
    {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        File setupFile = new File(Ward.SETUP_FILE_PATH);

        if (setupFile.exists())
        {
            Locale.setDefault(new Locale(utilitiesComponent.getFromIniFile(setupFile, "setup", "language")));
        }
        sessionLocaleResolver.setDefaultLocale(Locale.getDefault());

        return sessionLocaleResolver;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param utilitiesComponent autowired UtilitiesComponent object
     */
    @Autowired
    public BeanConfiguration(UtilitiesComponent utilitiesComponent)
    {
        this.utilitiesComponent = utilitiesComponent;
    }
}