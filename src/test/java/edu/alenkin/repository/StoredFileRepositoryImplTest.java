package edu.alenkin.repository;

import edu.alenkin.model.StoredFile;
import edu.alenkin.model.User;
import edu.alenkin.service.StoredFileService;
import edu.alenkin.service.StoredFileServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static edu.alenkin.repository.RepositoryTestData.*;
import static edu.alenkin.util.HibernateUtil.closeSession;
import static edu.alenkin.util.HibernateUtil.getSession;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class StoredFileRepositoryImplTest extends RepositoryTest {
    private StoredFileRepository repo = new StoredFileRepositoryImpl();

    @Test
    public void getTest() {
        assertThat(repo.get(file11_id, chuck_id))
                .usingRecursiveComparison()
                .ignoringFields("event", "user")
                .isEqualTo(chFile1);
    }

    @Test
    public void getNotExistTest() {
        Assert.assertNull(repo.get(file11_id, lee_id));
    }

    @Test
    public void getAllForUserTest() {
        assertThat(repo.getAllForUser(chuck_id))
                .usingRecursiveComparison()
                .ignoringFields("event", "user")
                .isEqualTo(List.of(chFile1, chFile2));
    }

    @Test
    public void getAllTest() {
        assertThat(repo.getAll())
                .usingRecursiveComparison()
                .ignoringFields("event", "user")
                .isEqualTo(List.of(chFile1, chFile2, leFile1, leFile2));
    }

    @Test
    public void deleteTest() {
        repo.delete(repo.get(file11_id, chuck_id));
        Assert.assertNull(repo.get(file11_id, chuck_id));
    }

    @Test
    public void createTest() {
        StoredFile newFile = RepositoryTestData.newFile();
        Long newId = repo.create(newFile).getId();
        newFile.setId(newId);
        assertThat(repo.get(newId)).usingRecursiveComparison().ignoringFields("event", "user").isEqualTo(newFile);
    }

    @Test
    public void createExistTest() {
        assertThat(repo.create(leFile1))
                .usingRecursiveComparison()
                .ignoringFields("event", "user").isEqualTo(leFile1);
    }


}