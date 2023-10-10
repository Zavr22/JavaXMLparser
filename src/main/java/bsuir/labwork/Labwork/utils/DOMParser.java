package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.models.CommercialOffer;
import bsuir.labwork.Labwork.models.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {

    public List<CommercialOffer> parseCommercialOffers(String filePath) throws Exception {
        List<CommercialOffer> commercialOffers = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);

        NodeList nList = doc.getElementsByTagName("commercial_offer");

        for (int i = 0; i < nList.getLength(); i++) {
            System.out.println(i+" - "+nList.getLength());
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                CommercialOffer commercialOffer = new CommercialOffer();
                commercialOffer.setCommercialOfferName(eElement.getElementsByTagName("offer_name").item(0).getTextContent());
                List<Product> products = new ArrayList<>();
                Product product = new Product();
                product.setName(eElement.getElementsByTagName("product_name").item(0).getTextContent());
                product.setPrice(eElement.getElementsByTagName("product_price").item(0).getTextContent());
                commercialOffer.setCommercialOfferPrice(eElement.getElementsByTagName("offer_price").item(0).getTextContent());
                products.add(product);
                commercialOffer.setProducts(products);
                commercialOffers.add(commercialOffer);
                }
            }

        return commercialOffers;
    }
}