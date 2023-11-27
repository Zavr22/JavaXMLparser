package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.models.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {

    public List<Event> parseEvents(String filePath) throws Exception {
        List<Event> events = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);

        NodeList nList = doc.getElementsByTagName("event");

        for (int i = 0; i < nList.getLength(); i++) {
            System.out.println(i+" - "+nList.getLength());
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                Event event = new Event();
                event.setEventName(eElement.getElementsByTagName("event_name").item(0).getTextContent());
                event.setOrganizerName(eElement.getElementsByTagName("organizer_name").item(0).getTextContent());
                event.setEventPrice(eElement.getElementsByTagName("event_price").item(0).getTextContent());
                events.add(event);
                }
            }

        return events;
    }
}