package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.model.User;
import edu.alenkin.util.JsonConverter;
import org.junit.Test;

import java.util.List;

import static edu.alenkin.TestData.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonUserControllerTest extends JsonBaseControllerTest<Long, User> {


    public JsonUserControllerTest() {
        super(new JsonUserController(),
                "{\"name\":\"Chuck Norris\",\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000},{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000}]," +
                        "\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"}]}",

                "{\"name\":\"Chuck Norris\",\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10003}]," +
                        "\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\",\"user\":{\"name\":\"Chuck Norris\"," +
                        "\"id\":10000},\"id\":10006}],\"id\":10000}",

                "[{\"name\":\"Chuck Norris\",\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000},{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000}]," +
                        "\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"}]},{\"name\":\"Chuck Norris\"," +
                        "\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000}]," +
                        "\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"}]}]",

                "[{\"name\":\"Chuck Norris\",\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000}," +
                        "\"id\":10003}],\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\"," +
                        "\"size\":9000,\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\",\"user\":{\"name\":\"Chuck Norris\",\"id\":10000}," +
                        "\"id\":10006}],\"id\":10000},{\"name\":\"Chuck Norris\"," +
                        "\"storedFiles\":[{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002}," +
                        "{\"fileURI\":\"Chuck://directory/dir/file.txt\",\"size\":15000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10003}]," +
                        "\"events\":[{\"storedFile\":{\"fileURI\":\"Chuck://test/testDir/testFile.pdf\",\"size\":9000," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10002},\"downloadDateTime\":\"Jun 20, 2021, 7:10:25 PM\"," +
                        "\"user\":{\"name\":\"Chuck Norris\",\"id\":10000},\"id\":10006}],\"id\":10000}]");
    }

    @Override
    protected User getNew() {
        User newUser = new User(CHUCK);
        newUser.setStoredFiles(List.of(chFile1, chFile2));
        newUser.setEvents(List.of(chEvent));
        return newUser;
    }
}