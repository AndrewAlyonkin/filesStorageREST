package edu.alenkin.repository;

import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static edu.alenkin.util.HibernateUtil.closeSession;
import static edu.alenkin.util.HibernateUtil.getSession;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

@Slf4j
public class EventRepositoryImpl extends BaseRepository<Long, Event> implements EventRepository{
    public EventRepositoryImpl() {
        this.setClass(Event.class);

    }
    @Override
    public Event get(Long id, Long userId) {
        log.info("Get {}", id);
        Event entity = (Event) getSession().
                createQuery("from Event where id =: id and user.id =: userId")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .uniqueResult();
        closeSession();
        return entity;
    }

    @Override
    public List<Event> getAllForUser(Long userId) {
        log.info("Get all for {}", userId);
        List<Event> events = (List<Event>) getSession().
                createQuery("from Event where user.id =: userId")
                .setParameter("userId", userId)
                .getResultList();
        closeSession();
        return events;
    }

    @Override
    protected void swapFields(Event existed, Event renewing) {
        existed.setDownloadDateTime(renewing.getDownloadDateTime());
        existed.setStoredFile(renewing.getStoredFile());
    }
}
