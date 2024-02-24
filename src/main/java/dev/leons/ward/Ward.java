package dev.leons.ward;

import dev.leons.ward.services.SetupService;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

/**
 * Ward is a Spring Boot application class
 *
 * @author Rudolf Barbu
 * @version 1.0.4
 */
@SpringBootApplication
public class Ward extends SpringBootServletInitializer {
    /**
     * Constant for determine settings file name
     */
    public static final String SETUP_FILE_PATH = "setup.ini";

    /**
     * Constant for determine initial application port
     */
    public static final int INITIAL_PORT = 4000;

    /**
     * Holder for determine first launch of application
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
    public static void main(final String[] args) {

        isFirstLaunch = true;
        configurableApplicationContext = SpringApplication.run(Ward.class, args);

        File setupFile = new File(Ward.SETUP_FILE_PATH);

        if (System.getenv("WARD_NAME") != null || (System.getenv("WARD_THEME") != null) || (System.getenv("WARD_PORT") != null) || (System.getenv("WARD_FOG") != null)) {
            SetupService.envSetup();
        } else if (setupFile.exists()) {
            restart();
        }
    }

    /**
     * Restarts application
     */
    public static void restart() {
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