package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.models.Event;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements Parser {

    public List<Event> parseEvents(String filePath) throws Exception {
        List<Event> events = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            Event event;


            String currentElement;


            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElement = qName;
                if ("event".equals(currentElement)) {
                    event = new Event();
                }
            }

            public void endElement(String uri, String localName, String qName) {
                if ("event".equals(qName)) {
                    events.add(event);
                }
                currentElement = "";
            }

            public void characters(char[] ch, int start, int length) {
                String value = new String(ch, start, length).trim();
                if (value.isEmpty()) return;

                if ("event_name".equals(currentElement)) {
                    event.setEventName(value);
                } else if ("organizer_name".equals(currentElement)) {
                   event.setOrganizerName(value);
                } else if ("event_price".equals(currentElement)) {
                    event.setEventPrice(value);
                }
            }
        };

        saxParser.parse(filePath, handler);
        return events;
    }
}