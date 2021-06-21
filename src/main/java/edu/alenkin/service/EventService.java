package edu.alenkin.service;

import edu.alenkin.model.Event;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface EventService extends Service<Long, Event> {
    public List<Event> getAllForUser(Long userId);
}
