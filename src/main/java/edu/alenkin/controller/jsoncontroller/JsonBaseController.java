package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.BaseEntity;
import edu.alenkin.service.Service;
import edu.alenkin.util.JsonConverter;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public abstract class JsonBaseController<ID extends Number, T extends BaseEntity> implements Controller<String, ID, T> {
    private final Service<ID, T> service;
    private final JsonConverter<T> jsonConverter;

    protected JsonBaseController(Service<ID, T> service, JsonConverter<T> jsonConverter) {
        this.service = service;
        this.jsonConverter = jsonConverter;
    }

    public String get(ID id) {
        T entity = service.get(id);
        try {
            return jsonConverter.toJson(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public String getAll() {
        List<T> entities = service.getAll();
        try {
            return jsonConverter.toJson(entities);
        } catch (Exception e) {
            return null;
        }
    }

    public String getAll(Long userId) {
        List<T> entities = service.getAll(userId);
        try {
            return jsonConverter.toJson(entities);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String json) {
        T entity = jsonConverter.fromJson(json);
        service.delete(entity);
    }

    public void delete(ID id) {
        service.delete(id);
    }

    public T create(String entityJson) {
        try {
            T entity = jsonConverter.fromJson(entityJson);
            return service.create(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> createList(String entityJson) {
        try {
            List<T> entities = jsonConverter.fromJsonList(entityJson);
            entities.forEach(service::create);
            return entities;
        } catch (Exception e) {
            return null;
        }
    }

    public T update(String json) {
        try {
            T entity = jsonConverter.fromJson(json);
            return service.update(entity);
        } catch (Exception e) {
            return null;
        }
    }
}
