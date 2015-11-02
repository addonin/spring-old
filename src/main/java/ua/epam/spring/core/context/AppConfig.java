package ua.epam.spring.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import ua.epam.spring.core.App;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.enums.EventType;
import ua.epam.spring.core.events.Event;
import ua.epam.spring.core.events.impl.CustomEvent;
import ua.epam.spring.core.loggers.EventLogger;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Adonin on 10/30/2015.
 */
@Configuration
@Import(value = LoggerConfig.class)
@ComponentScan(value = {"ua.epam.spring.core.beans", "ua.epam.spring.core.loggers"})
public class AppConfig {

    @Value("${id}")
    private String id;

    @Value("${name}")
    private String name;

    @Value("${greeting}")
    private String greeting;

    @Autowired
    private EventLogger consoleEventLogger;

    @Autowired
    private EventLogger combinedEventLogger;

    @Autowired
    private EventLogger cacheFileEventLogger;

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("client.properties"));
        configurer.setIgnoreResourceNotFound(true);
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return configurer;
    }

    @Bean
    public Client client() {
        Client client = new Client(id, name);
        client.setGreetings(greeting);
        return client;
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    @Scope(value = "prototype")
    public Event event() {
        CustomEvent event = new CustomEvent(new Date(), dateFormat());
        event.setMessage("MSG");
        return event;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> loggers = new HashMap<EventType, EventLogger>();
        loggers.put(EventType.INFO, consoleEventLogger);
        loggers.put(EventType.ERROR, combinedEventLogger);
        return loggers;
    }

    @Bean
    public App app() {
        return new App(client(), cacheFileEventLogger, loggerMap());
    }
}
