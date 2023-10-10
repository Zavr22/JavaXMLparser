package bsuir.labwork.Labwork.interfaces;

import bsuir.labwork.Labwork.models.CommercialOffer;


import java.util.List;

public interface Parser {
    List<CommercialOffer> parseCommercialOffers(String filePath) throws Exception;
}

