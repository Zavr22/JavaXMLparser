package bsuir.labwork.Labwork.configs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserConfig {
    private static ParserConfig instance;

    private String commercialOffersPath;

    private ParserConfig() {
        this.commercialOffersPath = "/Users/mihailkulik/Documents/3 курс/ПКП/XMLParser/commercial_offers.xml";
    }

    public static ParserConfig getInstance() {
        if (instance == null) {
            instance = new ParserConfig();
        }
        return instance;
    }
}
