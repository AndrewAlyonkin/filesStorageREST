package edu.alenkin.rest;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.BaseEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public abstract class BaseRestController extends HttpServlet implements RestService {
    protected Controller<String, Long, ? extends BaseEntity> controller;

    /**
     * Method GET
     * URL pattern: /"entities"/ - for getting all entities from repository
     * or /"entities"/id={ } - for getting one required entity.
     * return BAD_REQUEST if entity with required id not found
     * Set parameter "id" to get user by it id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String userId = req.getParameter("userId");
        String responseData = null;
        if (hasLength(id)) {
            try {
                Long entityId = Long.parseLong(id);
                responseData = controller.get(entityId);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            responseData = controller.getAll();
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        resp.getOutputStream().print(responseData);
    }

    /**
     * Method POST
     * URL pattern: /"entities"/
     * Request body example: "{"storedFile":{"fileURI":"Chuck://test/testDir/testFile.pdf","size":9000,"user":{"name":"Chuck Norris",
     * "id":10000},"id":10002},"downloadDateTime":"Jun 20, 2021, 7:10:25 PM",user":{"name":"Chuck Norris","id":10000},"id":null}"
     * <p>
     * Set parameter "count" to "one" if you create one entity or "many" if you create a list of entities!
     * Set json string to parameter "body" !
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String count = req.getParameter("count");
        String body = req.getParameter("entity");
        if (hasLength(count) && hasLength(body)) {
            Object entity = null;
            switch (count) {
                case "one": {
                    entity = controller.create(body);
                }
                case "many": {
                    entity = controller.createList(body);
                }
            }
            if (entity == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getOutputStream().print("Created");
            }
        }
    }

    /**
     * Method PUT
     * URL pattern: /"entities"/
     * Request body example: "{"storedFile":{"fileURI":"Chuck://test/testDir/testFile.pdf","size":9000,"user":{"name":"Chuck Norris",
     * "id":10000},"id":10002},"downloadDateTime":"Jun 20, 2021, 7:10:25 PM",user":{"name":"Chuck Norris","id":10000},"id":10006}"
     * <p>
     * Set json string to parameter "body" !
     * You can update only one entity with this method.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getParameter("body");
        Object entity = null;
        if (hasLength(body)) {
            entity = controller.update(body);
        }
        if (entity == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getOutputStream().print("Updated");
        }
    }

    /**
     * Method DELETE
     * URL pattern: /"entities"/id={ }
     * <p>
     * Deletes only entity with required id
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (hasLength(id)) {
            try {
                Long entityId = Long.parseLong(id);
                controller.delete(entityId);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getOutputStream().print("Deleted");
        }
    }

    private boolean hasLength(String s) {
        return (s != null && !s.isEmpty());
    }
}
