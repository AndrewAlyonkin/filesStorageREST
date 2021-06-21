package edu.alenkin.service;

import edu.alenkin.model.Event;
import edu.alenkin.repository.EventRepository;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@NoArgsConstructor
public class EventServiceImpl extends BaseService<Long, Event> implements EventService {
    public EventServiceImpl(EventRepository repository) {
        super(repository);
    }

    @Override
    public List<Event> getAllForUser(Long userId) {
        return ((EventRepository) repository).getAllForUser(userId);
    }
}
