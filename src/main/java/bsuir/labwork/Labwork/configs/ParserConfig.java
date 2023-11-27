package bsuir.labwork.Labwork.configs;

public class ParserConfig {
    private static ParserConfig instance;

    private String eventsPath;

    private ParserConfig() {
        this.eventsPath = "/Users/mihailkulik/Documents/3year/PKP/XMLParser/events.xml";
    }

    public static ParserConfig getInstance() {
        if (instance == null) {
            instance = new ParserConfig();
        }
        return instance;
    }

    public String getEventsPath() {
        return eventsPath;
    }
}
