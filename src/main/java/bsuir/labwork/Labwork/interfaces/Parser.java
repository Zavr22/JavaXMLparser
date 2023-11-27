package bsuir.labwork.Labwork.interfaces;

import bsuir.labwork.Labwork.models.Event;


import java.util.List;

public interface Parser {
    List<Event> parseEvents(String filePath) throws Exception;
}

