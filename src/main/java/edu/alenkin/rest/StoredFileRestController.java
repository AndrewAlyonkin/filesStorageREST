package edu.alenkin.rest;

import edu.alenkin.controller.jsoncontroller.JsonFileController;

import javax.servlet.ServletException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class StoredFileRestController extends BaseRestController {
    @Override
    public void init() throws ServletException {
        this.controller = new JsonFileController();
        super.init();
    }
}
