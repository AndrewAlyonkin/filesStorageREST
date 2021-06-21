package edu.alenkin.service;

import edu.alenkin.model.User;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserService extends Service<Long, User> {
    User getWithFilesAndEvents(Long userId);

}
