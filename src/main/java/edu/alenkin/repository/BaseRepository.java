package edu.alenkin.repository;

import edu.alenkin.model.BaseEntity;
import edu.alenkin.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.From;
import java.io.Serializable;
import java.util.List;

import static edu.alenkin.util.HibernateUtil.closeSession;
import static edu.alenkin.util.HibernateUtil.getSession;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * The base implementation of {@link Repository}. Generic abstract super class, that provides CRUD operations
 * with hibernate
 */
@Slf4j
public abstract class BaseRepository<ID extends Serializable, T extends BaseEntity> implements Repository<ID, T> {

    private Class<T> clazz;

    public void setClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get(ID Tid) {
        log.info("Get {}", Tid);
        T entity = getSession().get(clazz, Tid);
        closeSession();
        return entity;
    }

    @Override
    public List<T> getAll() {
        log.info("Get all");
        List<T> entities = getSession().createQuery("From " + clazz.getSimpleName(), clazz).getResultList();
        closeSession();
        return entities;
    }

    @Override
    public void delete(T t) {
        log.info("Delete {}", t);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        closeSession();
    }

    @Override
    public void delete(ID id) {
        log.info("Delete {}", id);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from " + clazz.getSimpleName() + " where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        closeSession();
    }

    @Override
    public T create(T t) {
        T entity = t;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        if (t.getId() == null) {
            session.save(t);
            log.info("Create new {}", t);
        } else {
            T existed = session.get(clazz, t.getId());
            swapFields(existed, t);
            getSession().update(t);
            log.info("Update existing {}", t);
        }
        transaction.commit();
        closeSession();
        return entity;
    }

    protected abstract void swapFields(T existed, T renewing);
}
