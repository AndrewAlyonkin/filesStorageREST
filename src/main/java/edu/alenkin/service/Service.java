package edu.alenkin.service;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface Service <ID, T>{
    T get(ID Tid);
    List<T> getAll();
    T update(T t);
    T create(T t);
    void delete(T t);
}
