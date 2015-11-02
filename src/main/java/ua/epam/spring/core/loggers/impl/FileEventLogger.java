package ua.epam.spring.core.loggers.impl;

import org.apache.commons.io.FileUtils;
import ua.epam.spring.core.events.Event;
import ua.epam.spring.core.loggers.EventLogger;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dmytro_Adonin on 10/30/2015.
 */
public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);
        //if (!file.canWrite()) throw new IOException(fileName + " closed to write");
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
