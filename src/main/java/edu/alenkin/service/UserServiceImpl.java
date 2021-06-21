package edu.alenkin.service;

import edu.alenkin.model.User;
import edu.alenkin.repository.UserRepository;
import lombok.NoArgsConstructor;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@NoArgsConstructor
public class UserServiceImpl extends BaseService<Long, User> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User getWithFilesAndEvents(Long userId) {
        return ((UserRepository) repository).getWithFilesAndEvents(userId);
    }
}
