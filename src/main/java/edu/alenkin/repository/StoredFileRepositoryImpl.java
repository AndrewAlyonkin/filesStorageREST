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
public class StoredFileRepositoryImpl extends BaseRepository<Long, StoredFile> implements StoredFileRepository {
    public StoredFileRepositoryImpl() {
        this.setClass(StoredFile.class);
    }

    @Override
    public StoredFile get(Long id, Long userId) {
        log.info("Get {}", id);
        StoredFile entity = (StoredFile) getSession().
                createQuery("from StoredFile where id =: id and user.id =: userId")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .uniqueResult();
        closeSession();
        return entity;
    }
    @Override
    public List<StoredFile> getAllForUser(Long userId) {
        log.info("Get all for {}", userId);
        List<StoredFile> entities = (List<StoredFile>) getSession().
                createQuery("from StoredFile where user.id =: userId")
                .setParameter("userId", userId)
                .getResultList();
        closeSession();
        return entities;
    }

    @Override
    protected void swapFields(StoredFile existed, StoredFile renewing) {
        existed.setEvent(renewing.getEvent());
        existed.setFileURI(renewing.getFileURI());
        existed.setSize(renewing.getSize());
        existed.setEvent(renewing.getEvent());
        existed.setUser(renewing.getUser());
    }

}
