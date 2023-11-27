package bsuir.labwork.Labwork.models;

import bsuir.labwork.Labwork.exceptions.InvalidEventEcxeption;

import java.util.regex.Pattern;

public class Event {

    private static final String EVENT_NAME_PATTERN = "[A-Za-z\\s\\d]+";
    private static final String ORGANIZER_NAME_PATTERN = "[A-Za-z\\s\\d]+";
    private static final String EVENT_PRICE_PATTERN = "\\d+";

    private String eventName;
    private String eventPrice;
    private String organizerName;


    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventPrice='" + eventPrice + '\'' +
                ", organizerName='" + organizerName + '\'' +
                '}';
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void validate() throws InvalidEventEcxeption {
        if (!Pattern.matches(EVENT_NAME_PATTERN, eventName)) {
            throw new InvalidEventEcxeption("invalid event name");
        }
        if (!Pattern.matches(ORGANIZER_NAME_PATTERN, organizerName)) {
            throw new InvalidEventEcxeption("invalid organizer name");
        }
        if (!Pattern.matches(EVENT_PRICE_PATTERN, eventPrice)) {
            throw new InvalidEventEcxeption("invalid price");
        }
    }

}