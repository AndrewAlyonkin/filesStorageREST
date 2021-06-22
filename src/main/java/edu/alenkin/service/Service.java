package edu.alenkin.service;

import edu.alenkin.model.BaseEntity;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface Service <ID extends Number, T extends BaseEntity>{
    T get(ID Tid);
    List<T> getAll();
    T update(T t);
    T create(T t);
    void delete(T t);
    void delete(ID id);
    List<T> getAll(Long id);
}
