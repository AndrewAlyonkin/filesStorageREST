package edu.alenkin.rest;

import edu.alenkin.controller.Controller;
import edu.alenkin.controller.jsoncontroller.JsonFileController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class StoredFileRestController extends HttpServlet implements RestService {
    private Controller fileController;

    @Override
    public void init() throws ServletException {
        super.init();
        fileController = new JsonFileController();
    }
}
