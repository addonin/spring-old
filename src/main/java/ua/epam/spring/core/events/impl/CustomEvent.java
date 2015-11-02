package ua.epam.spring.core.events.impl;

import ua.epam.spring.core.events.Event;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Dmytro_Adonin on 10/29/2015.
 */
public class CustomEvent implements Event {

    private int id;
    private String message;
    private Date date;
    private DateFormat df;

    public CustomEvent(Date date, DateFormat df) {
        this.id = new Random().nextInt();
        this.date = date;
        this.df = df;

    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "\n" + this.getId() + " : " + this.getMessage() + " : " + df.format(this.getDate());
    }
}
