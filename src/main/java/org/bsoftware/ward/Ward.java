package org.bsoftware.ward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Ward is a Spring Boot application class
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 * @since 2020-05-17
 */
@SpringBootApplication
public class Ward extends SpringBootServletInitializer
{
    /**
     * Entry point of Ward application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Ward.class, args);
    }
}