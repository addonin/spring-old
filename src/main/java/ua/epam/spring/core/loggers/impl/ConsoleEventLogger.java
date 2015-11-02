package ua.epam.spring.core.loggers.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.core.events.Event;
import ua.epam.spring.core.loggers.EventLogger;

/**
 * Created by Dmytro_Adonin on 10/29/2015.
 */
@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.print(event);
    }
}
