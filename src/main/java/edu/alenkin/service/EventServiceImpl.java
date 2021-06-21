package edu.alenkin.service;

import edu.alenkin.model.Event;
import edu.alenkin.repository.EventRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class EventServiceImpl extends BaseService<Long, Event> implements EventService {
    public EventServiceImpl(EventRepository repository) {
        super(repository);
    }
}
