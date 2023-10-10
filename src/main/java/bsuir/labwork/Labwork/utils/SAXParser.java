package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.models.CommercialOffer;
import bsuir.labwork.Labwork.models.Product;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements Parser {

    public List<CommercialOffer> parseCommercialOffers(String filePath) throws Exception {
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
}