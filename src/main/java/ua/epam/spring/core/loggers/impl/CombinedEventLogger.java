package ua.epam.spring.core.loggers.impl;

import ua.epam.spring.core.events.Event;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.List;

/**
 * Created by Dmytro_Adonin on 10/30/2015.
 */
public class CombinedEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
