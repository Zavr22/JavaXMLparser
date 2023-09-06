import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DOMParser {

    public static List<CommercialOffer> parseCommercialOffers(String filePath) throws Exception {
        List<CommercialOffer> commercialOffers = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);

        NodeList nList = doc.getElementsByTagName("commercial_offer");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                CommercialOffer commercialOffer = new CommercialOffer();
                commercialOffer.setCommercialOfferName(eElement.getElementsByTagName("offer_name").item(0).getTextContent());
                List<Product> products = new ArrayList<>();
                NodeList productsList = eElement.getElementsByTagName("product");
                for (i = 0; i < productsList.getLength(); i++){
                    Node pNode = productsList.item(i);


                    if (pNode.getNodeType() == Node.ELEMENT_NODE){
                        Element pElement = (Element) pNode;
                        Product product = new Product();
                        product.setName(pElement.getElementsByTagName("product_name").item(0).getTextContent());
                        product.setPrice(pElement.getElementsByTagName("product_price").item(0).getTextContent());

                        products.add(product);
                        commercialOffer.setProducts(products);
                    }
                }
                commercialOffer.setCommercialOfferPrice(eElement.getElementsByTagName("offer_price").item(0).getTextContent());

                commercialOffers.add(commercialOffer);
            }
        }
        return commercialOffers;
    }

    public static void main(String[] args) {
        try {
            List<CommercialOffer> commercialOffers = parseCommercialOffers("commercial_offers.xml");
            writeCommercialOffersToFile(commercialOffers);
            validateAndPrintCommercialOffers(commercialOffers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeCommercialOffersToFile(List<CommercialOffer> commercialOffers) throws IOException {
        List<String> outputLines = commercialOffers.stream().map(CommercialOffer::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }

    private static void validateAndPrintCommercialOffers(List<CommercialOffer> commercialOffers) {
        for (CommercialOffer commercialOffer : commercialOffers) {
            try {
                commercialOffer.validate();
                System.out.println("Valid commercial offer: " + commercialOffer);
            } catch (Exception e) {
                System.out.println("Invalid commercial offer detected: " + commercialOffer + " Reason: " + e.getMessage());
            }
        }
    }
}