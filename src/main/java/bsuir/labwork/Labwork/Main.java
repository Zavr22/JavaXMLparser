package bsuir.labwork.Labwork;

import bsuir.labwork.Labwork.configs.ParserConfig;
import bsuir.labwork.Labwork.models.CommercialOffer;
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
            String commercialOffersPath = config.getCommercialOffersPath();

            List<CommercialOffer> commercialOffersDom = domParser.parseCommercialOffers(commercialOffersPath);
            List<CommercialOffer> commercialOffersSax = saxParser.parseCommercialOffers(commercialOffersPath);

            writeOffersToFile(commercialOffersDom);
            validateAndPrintOffers(commercialOffersSax);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintOffers(List<CommercialOffer>  commercialOffers) {
        for (CommercialOffer commercialOffer : commercialOffers) {
            try {
                commercialOffer.validate();
                System.out.println("Valid commercialOffer: " + commercialOffer);
            } catch (Exception e) {
                System.out.println("Invalid commercialOffer detected: " + commercialOffer + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeOffersToFile(List<CommercialOffer> commercialOffers) throws IOException {
        List<String> outputLines = commercialOffers.stream().map(CommercialOffer::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
