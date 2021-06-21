package edu.alenkin.repository;

import edu.alenkin.model.User;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserRepository extends Repository<Long, User> {
    User getWithFilesAndEvents(Long userId);
}
