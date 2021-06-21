package edu.alenkin.service;

import edu.alenkin.model.BaseEntity;
import edu.alenkin.repository.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * The service layer that transfers entities data from repository and contains business-logic
 *
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

@Slf4j
public abstract class BaseService<ID extends Serializable, T extends BaseEntity> implements Service<ID, T> {

    protected Repository<ID, T> repository;

    public BaseService(Repository<ID, T> repository) {
        this.repository = repository;
    }

    @Override
    public T get(ID Tid) {
        log.info("Get {}", Tid);
        if (notNullable(Tid)) {
            return repository.get(Tid);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        log.info("Get all");
        return repository.getAll();
    }

    @Override
    public T update(T t) {
        if (notNullable(t.getId())) {
            return repository.create(t);
        }
        return null;
    }

    @Override
    public T create(T t) {
        log.info("Create new {}", t);
        if (notNullable(t.getId())) {
            return repository.create(t);
        }
        return null;
    }

    @Override
    public void delete(T t) {
        log.info("Delete {}", t);
        if (notNullable(repository.get((ID) t.getId()))) {
            repository.delete(t);
        }
    }

    private boolean notNullable(Object obj) {
        return obj != null;
    }
}
