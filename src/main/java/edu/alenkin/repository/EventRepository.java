package edu.alenkin.repository;

import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface EventRepository extends Repository<Long, Event> {
    List<Event> getAllForUser(Long userId);
}
