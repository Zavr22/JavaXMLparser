package bsuir.labwork.Labwork.utils;

import static org.junit.jupiter.api.Assertions.*;

import bsuir.labwork.Labwork.models.CommercialOffer;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DOMParserTest {

    @Test
    public void testParseCreditCards() throws Exception {
        DOMParser parser = new DOMParser();
        String filePath = "../test_commercial_offer.xml";

        List<CommercialOffer> commercialOffers = parser.parseCommercialOffers(filePath);

        assertEquals(1, commercialOffers.size());

        assertEquals("Commercial offer 1", commercialOffers.get(0).getCommercialOfferName());
        assertEquals("Optoma T500", commercialOffers.get(0).getProducts().get(0).getName());
        assertEquals("100", commercialOffers.get(0).getProducts().get(0).getPrice());
        assertEquals("200", commercialOffers.get(0).getCommercialOfferPrice());
    }
}
