package edu.alenkin.repository;

import edu.alenkin.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static edu.alenkin.repository.RepositoryTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

public class UserRepositoryImplTest extends RepositoryTest {

    UserRepository repo = new UserRepositoryImpl();

    @Test
    public void getTest() {
        assertThat(repo.get(chuck_id)).usingRecursiveComparison().ignoringFields("events", "storedFiles").isEqualTo(CHUCK);
    }

    @Test
    public void getWithLazyFieldsTest() {
        chFile1.setEvent(chEvent);
        CHUCK.setStoredFiles(List.of(chFile1, chFile2));
        CHUCK.setEvents(List.of(chEvent));
        assertThat(repo.getWithFilesAndEvents(chuck_id)).usingRecursiveComparison().isEqualTo(CHUCK);
    }

    @Test
    public void getAllTest() {
        assertThat(repo.getAll()).usingRecursiveComparison().ignoringFields("events", "storedFiles").isEqualTo(List.of(CHUCK, LEE));
    }

    @Test
    public void deleteTest() {
        repo.delete(CHUCK);
        Assert.assertNull(repo.get(chuck_id));

    }

    @Test
    public void createTest() {
        User newUser = RepositoryTestData.newUser();
        Long newId = repo.create(newUser).getId();
        newUser.setId(newId);
        assertThat(repo.get(newId)).usingRecursiveComparison().ignoringFields("events", "storedFiles").isEqualTo(newUser);
    }

    @Test
    public void createExistTest() {
        assertThat(repo.create(CHUCK)).usingRecursiveComparison().ignoringFields("events", "storedFiles").isEqualTo(CHUCK);
    }

    @Test
    public void updateTest() {
        User clone = new User(CHUCK);
        clone.setName("Aristarch");
        repo.create(clone);
        assertThat(repo.get(chuck_id)).usingRecursiveComparison().ignoringFields("events", "storedFiles").isEqualTo(clone);
    }

}