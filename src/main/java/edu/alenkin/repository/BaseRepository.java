package edu.alenkin.repository;

import edu.alenkin.model.BaseEntity;
import edu.alenkin.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import static edu.alenkin.util.HibernateUtil.closeSession;
import static edu.alenkin.util.HibernateUtil.getSession;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public abstract class BaseRepository <ID extends Serializable, T extends BaseEntity> implements Repository<ID, T>{

    private Class<T> clazz;

    public void setClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get(ID Tid) {
        T entity =  getSession().get(clazz, Tid);
        closeSession();
        return entity;
    }

    @Override
    public List<T> getAll() {
        List<T> entities = getSession().createQuery("From" + clazz.getName(), clazz).getResultList();
        closeSession();
        return entities;
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
        closeSession();
    }

    @Override
    public T create(T t) {
        T entity = t;
        if (t.getId() == null) {
            t = (T) getSession().save(t);
        } else {
            getSession().update(t);
        }
       closeSession();
        return entity;
    }
}
