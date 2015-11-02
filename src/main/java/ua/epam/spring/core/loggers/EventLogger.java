package ua.epam.spring.core.loggers;

import ua.epam.spring.core.events.Event;

/**
 * Created by Dmytro_Adonin on 10/29/2015.
 */
public interface EventLogger {

    void logEvent(Event event);
}
