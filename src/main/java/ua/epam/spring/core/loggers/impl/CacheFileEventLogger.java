package ua.epam.spring.core.loggers.impl;

import ua.epam.spring.core.events.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmytro_Adonin on 10/30/2015.
 */
public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new LinkedList<Event>();

    public CacheFileEventLogger(String fileName) {
        super(fileName);
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void setCache(List<Event> cache) {
        this.cache = cache;
    }
}
