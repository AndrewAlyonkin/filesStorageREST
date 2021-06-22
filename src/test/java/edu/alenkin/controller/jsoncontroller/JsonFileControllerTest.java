package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.model.StoredFile;

import static edu.alenkin.TestData.chFile1;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonFileControllerTest extends JsonBaseControllerTest<Long, StoredFile> {

    public JsonFileControllerTest() {
        super(new JsonFileController(),
                "{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000,\"id\":10002}",
                "{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}",
                "[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000,\"id\":10002}," +
                        "{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000,\"id\":10002}]",
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