package edu.alenkin.repository;

import edu.alenkin.model.Event;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static edu.alenkin.repository.RepositoryTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class EventRepositoryImplTest extends RepositoryTest {
    private EventRepository repo = new EventRepositoryImpl();

    @Test
    public void getTest() {
        assertThat(repo.get(event11_id, chuck_id))
                .usingRecursiveComparison()
                .ignoringFields("user", "storedFile")
                .isEqualTo(chEvent);
    }

    @Test
    public void getNotExistTest() {
        Assert.assertNull(repo.get(event11_id, lee_id));
    }

    @Test
    public void getAllForUserTest() {
        assertThat(repo.getAllForUser(chuck_id))
                .usingRecursiveComparison()
                .ignoringFields("storedFile", "user")
                .isEqualTo(List.of(chEvent));
    }

    @Test
    public void getAllTest() {
        assertThat(repo.getAll())
                .usingRecursiveComparison()
                .ignoringFields("storedFile", "user")
                .isEqualTo(List.of(chEvent, leEvent));
    }

    @Test
    public void deleteTest() {
        repo.delete(repo.get(event11_id, chuck_id));
        Assert.assertNull(repo.get(event11_id, chuck_id));
    }

    @Test
    public void createTest() {
        Event newEvent = RepositoryTestData.newEvent();
        Long newId = repo.create(newEvent).getId();
        newEvent.setId(newId);
        assertThat(repo.get(newId)).usingRecursiveComparison()
                .ignoringFields("storedFile", "user").isEqualTo(newEvent);
    }

    @Test
    public void createExistTest() {
        assertThat(repo.create(chEvent))
                .usingRecursiveComparison()
                .ignoringFields("event", "user").isEqualTo(chEvent);
    }
}