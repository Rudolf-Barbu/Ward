package org.bsoftware.ward;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Ward is a Spring Boot application class
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@SpringBootApplication
public class Ward extends SpringBootServletInitializer
{
    /**
     * Holder for determine first launch of applicatuion
     */
    @Getter
    private static boolean isFirstLaunch;

    /**
     * Holder for application context
     */
    private static ConfigurableApplicationContext configurableApplicationContext;

    /**
     * Entry point of Ward application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args)
    {
        isFirstLaunch = true;
        configurableApplicationContext = SpringApplication.run(Ward.class, args);
    }

    /**
     * Restarts application
     */
    public static void restart()
    {
        isFirstLaunch = false;
        ApplicationArguments args = configurableApplicationContext.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() ->
        {
            configurableApplicationContext.close();
            configurableApplicationContext = SpringApplication.run(Ward.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }
}