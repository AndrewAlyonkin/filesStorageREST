package edu.alenkin.rest;

import edu.alenkin.controller.jsoncontroller.JsonEventController;

import javax.servlet.ServletException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class EventRestController extends BaseRestController{

    @Override
    public void init() throws ServletException {
        this.controller = new JsonEventController();
        super.init();
    }


}
