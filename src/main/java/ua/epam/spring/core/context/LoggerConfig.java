package ua.epam.spring.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.spring.core.loggers.EventLogger;
import ua.epam.spring.core.loggers.impl.CacheFileEventLogger;
import ua.epam.spring.core.loggers.impl.CombinedEventLogger;
import ua.epam.spring.core.loggers.impl.ConsoleEventLogger;
import ua.epam.spring.core.loggers.impl.FileEventLogger;

import java.util.Arrays;

/**
 * Created by Dmytro_Adonin on 10/30/2015.
 */
@Configuration
public class LoggerConfig {

    @Autowired
    private ConsoleEventLogger consoleEventLogger;

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger("event.log");
    }

    @Bean
    public EventLogger cacheFileEventLogger() {
        CacheFileEventLogger logger = new CacheFileEventLogger("events.log");
        logger.setCacheSize(3);
        return logger;
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(consoleEventLogger, fileEventLogger()));
    }
}
