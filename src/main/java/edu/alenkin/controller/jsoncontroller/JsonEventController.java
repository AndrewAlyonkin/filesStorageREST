package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.model.Event;
import edu.alenkin.repository.EventRepositoryImpl;
import edu.alenkin.service.EventServiceImpl;
import edu.alenkin.service.Service;
import edu.alenkin.util.JsonConverter;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonEventController extends JsonBaseController<Long, Event>{
    public JsonEventController() {
        super(new EventServiceImpl( new EventRepositoryImpl()), new JsonConverter<Event>(Event.class));
    }


}
