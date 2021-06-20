package edu.alenkin.repository;

import edu.alenkin.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Provides methods for manipulating entities data in database storage
 */
public interface Repository<ID extends Serializable, T extends BaseEntity> {
    T get(ID Tid);

    List<T> getAll();

    void delete(T t);

    T create(T t);
}
