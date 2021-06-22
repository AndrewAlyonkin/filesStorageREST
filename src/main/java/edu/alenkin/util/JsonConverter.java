package edu.alenkin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Util class that provides methods for converting object to JSON and back
 */
public class JsonConverter<T> {
    private final Gson entityToJson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private final Gson fromJsonToEntity = new GsonBuilder().create();

    public JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    private final Class<T> clazz;

    public <T> String toJson(T entity) {
        return entityToJson.toJson(entity);
    }

    public <T> String toJson(List<T> entities) {
        return entityToJson.toJson(entities);
    }

    public T fromJson(String json) {
        return (T) fromJsonToEntity.fromJson(json, clazz);
    }

    public <T> List<T> fromJsonList(String json) {
        return fromJsonToEntity.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }
}
