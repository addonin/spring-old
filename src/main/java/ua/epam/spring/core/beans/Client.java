package ua.epam.spring.core.beans;

/**
 * Created by Dmytro_Adonin on 10/29/2015.
 */
public class Client {

    private String id;
    private String fullName;
    private String greetings;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }
}
