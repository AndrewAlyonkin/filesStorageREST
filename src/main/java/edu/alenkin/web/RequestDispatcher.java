package edu.alenkin.web;

import edu.alenkin.rest.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class RequestDispatcher extends HttpServlet {

    private final static NotFoundRest NOT_FOUND_REST = new NotFoundRest();
    private final Map<String, RestService> services = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        services.put("/users", new UserRestController());
        services.put("/files", new StoredFileRestController());
        services.put("/events", new EventRestController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();
        services.getOrDefault(path, NOT_FOUND_REST).service(req, resp);
    }
}
