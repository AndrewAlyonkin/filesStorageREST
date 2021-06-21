package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.StoredFile;
import edu.alenkin.service.Service;
import edu.alenkin.service.StoredFileServiceImpl;

import static edu.alenkin.TestData.chFile1;
import static org.junit.Assert.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonFileControllerTest extends JsonBaseControllerTest<Long, StoredFile>{

    public JsonFileControllerTest() {
        super(new JsonFileController(),
                "{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}",
                "[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000}," +
                        "\"id\":10002},{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}]");
    }

    @Override
    protected StoredFile getNew() {
        return new StoredFile(chFile1);
    }



}