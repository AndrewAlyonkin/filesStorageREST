package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.model.User;
import edu.alenkin.repository.UserRepositoryImpl;
import edu.alenkin.service.UserServiceImpl;
import edu.alenkin.util.JsonConverter;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonUserController extends JsonBaseController<Long, User> {
    public JsonUserController() {
        super(new UserServiceImpl(new UserRepositoryImpl()), new JsonConverter<User>(User.class));
    }
}
