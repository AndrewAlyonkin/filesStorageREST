package edu.alenkin.repository;

import edu.alenkin.model.User;
import edu.alenkin.util.HibernateUtil;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.Collections;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class UserRepositoryImpl extends BaseRepository<Long, User> implements UserRepository{

    public UserRepositoryImpl() {
        this.setClass(User.class);
    }

    @Override
    public User getWithFilesAndEvents(Long userId) {
        User entity = HibernateUtil.getSession().get(User.class, userId);
        if (entity.getEvents().isEmpty()) {
            entity.setEvents(Collections.emptyList());
        }
        if (entity.getStoredFiles().isEmpty()) {
            entity.setStoredFiles(Collections.emptyList());
        }
        HibernateUtil.closeSession();
        return entity;
    }

    @Override
    public User get(Long Tid, Long UserId) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void swapFields(User existed, User renewing) {
        existed.setName(renewing.getName());
    }
}
