import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SAXParser {

    public static List<CommercialOffer> parseCreditCommercialOffers(String filePath) throws Exception {
        List<CommercialOffer> commercialOffers = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            CommercialOffer commercialOffer;

            Product product;
            String currentElement;


            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElement = qName;
                if ("commercial_offer".equals(currentElement)) {
                    commercialOffer = new CommercialOffer();
                } else if ("product".equals(currentElement)) {
                    product = new Product();
                }
            }

            public void endElement(String uri, String localName, String qName) {
                if ("commercial_offer".equals(qName)) {
                    commercialOffers.add(commercialOffer);
                } else if ("product".equals(qName)) {
                    commercialOffer.getProducts().add(product);
                }
                currentElement = "";
            }

            public void characters(char[] ch, int start, int length) {
                String value = new String(ch, start, length).trim();
                if (value.isEmpty()) return;

                if ("offer_name".equals(currentElement)) {
                    commercialOffer.setCommercialOfferName(value);
                } else if ("product_name".equals(currentElement)) {
                    product.setName(value);
                } else if ("product_price".equals(currentElement)) {
                    product.setPrice(value);
                } else if ("offer_price".equals(currentElement)) {
                    commercialOffer.setCommercialOfferPrice(value);
                }
            }
        };

        saxParser.parse(filePath, handler);
        return commercialOffers;
    }

    public static void main(String[] args) {
        try {
            List<CommercialOffer> commercialOffers = parseCreditCommercialOffers("commercial_offers.xml");
            writeCommercialOffersToFile(commercialOffers);
            validateAndPrintCommercialOffers(commercialOffers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintCommercialOffers(List<CommercialOffer> commercialOffers) {
        for (CommercialOffer commercialOffer : commercialOffers) {
            try {
                commercialOffer.validate();
                System.out.println("Valid commercial offer: " + commercialOffer);
            } catch (Exception e) {
                System.out.println("Invalid commercialOffer detected: " + commercialOffer + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeCommercialOffersToFile(List<CommercialOffer> commercialOffers) throws IOException {
        List<String> outputLines = commercialOffers.stream().map(CommercialOffer::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}