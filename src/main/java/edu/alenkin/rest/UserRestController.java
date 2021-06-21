package edu.alenkin.rest;

import edu.alenkin.controller.jsoncontroller.JsonUserController;

import javax.servlet.ServletException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class UserRestController extends BaseRestController {

    @Override
    public void init() throws ServletException {
        this.controller = new JsonUserController();
        super.init();
    }
}
