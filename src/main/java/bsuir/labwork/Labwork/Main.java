package bsuir.labwork.Labwork;

import bsuir.labwork.Labwork.configs.ParserConfig;
import bsuir.labwork.Labwork.models.Event;
import bsuir.labwork.Labwork.factories.DOMParserFactory;
import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.factories.SAXParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ParserFactory domFactory = new DOMParserFactory();
        Parser domParser = domFactory.createParser();

        ParserFactory saxFactory = new SAXParserFactory();
        Parser saxParser = saxFactory.createParser();

        try {
            ParserConfig config = ParserConfig.getInstance();
            String eventsPath = config.getEventsPath();

            List<Event> eventsDom = domParser.parseEvents(eventsPath);
            List<Event> eventsSaxes = saxParser.parseEvents(eventsPath);

            writeEventsToFile(eventsDom);
            validateAndPrintEvents(eventsSaxes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintEvents(List<Event> events) {
        for (Event event : events) {
            try {
                event.validate();
                System.out.println("Valid event: " + event);
            } catch (Exception e) {
                System.out.println("Invalid event detected: " + event + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeEventsToFile(List<Event> events) throws IOException {
        List<String> outputLines = events.stream().map(Event::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
