package ua.epam.spring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.context.AppConfig;
import ua.epam.spring.core.enums.EventType;
import ua.epam.spring.core.events.Event;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Map;

/**
 * Created by Dmytro_Adonin on 10/29/2015.
 */
public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event event ) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("app-config.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        //ctx.register(LoggerConfig.class);
        ctx.refresh();

        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, ctx.getBean("event", Event.class));
        app.logEvent(EventType.ERROR, ctx.getBean("event", Event.class));
        ctx.close();

    }
}
