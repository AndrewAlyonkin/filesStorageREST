package edu.alenkin.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.alenkin.model.BaseEntity;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Util class that provides methods for converting object to JSON and back
 */
public class JsonConverter<T> {
    private final Gson parser = new Gson();

    public JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    private final Class<T> clazz;

    public <T> String toJson(T entity) {
        return parser.toJson(entity);
    }

    public <T> String toJson(List<T> entities) {
        return parser.toJson(entities);
    }

    public T fromJson(String json) {
        return (T) parser.fromJson(json, clazz);
    }

    public <T> List<T> fromJsonList(String json) {
        return parser.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }
}
