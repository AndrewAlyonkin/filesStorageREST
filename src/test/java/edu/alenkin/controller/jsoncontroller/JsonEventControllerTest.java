package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;
import edu.alenkin.service.EventServiceImpl;
import edu.alenkin.service.Service;
import edu.alenkin.util.JsonConverter;
import org.junit.Test;

import static edu.alenkin.TestData.*;
import static org.junit.Assert.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonEventControllerTest extends JsonBaseControllerTest<Long, Event> {

    @Override
    protected Event getNew() {
        return new Event(chEvent);
    }

    public JsonEventControllerTest() {

        super(new JsonEventController(),
                "{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"}",
                "{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10006}",
                "[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"},{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000},\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"}]",
                "[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\",\"user\":{\"name\":\"Chuck Norris\"," +
                        "\"id\":10000},\"id\":10006},{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\",\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10006}]"
        );
    }

}