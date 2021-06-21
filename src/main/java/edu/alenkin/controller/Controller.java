package edu.alenkin.controller;

import edu.alenkin.model.BaseEntity;
import edu.alenkin.util.JsonConverter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface Controller<R, ID extends Number, T extends BaseEntity> {
    R get(ID id);

   R getAll();

   void delete(R t);

   void delete(ID id);

    T create(String entityJson);

    T update(R t);
}
