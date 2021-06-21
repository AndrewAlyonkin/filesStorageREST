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
        return jsonConverter.toJson(entity);
    }

    public String getAll() {
        List<T> entities = service.getAll();
        return jsonConverter.toJson(entities);
    }

    public void delete(String json) {
        T entity = jsonConverter.fromJson(json);
        service.delete(entity);
    }

    public void delete(ID id) {
        service.delete(id);
    }

    public T create(String entityJson) {
        T entity = jsonConverter.fromJson(entityJson);
        return service.create(entity);
    }

    public T update(String json) {
        T entity = jsonConverter.fromJson(json);
        return service.update(entity);
    }
}
