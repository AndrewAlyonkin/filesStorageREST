package edu.alenkin.service;

import edu.alenkin.model.StoredFile;
import edu.alenkin.model.User;
import edu.alenkin.repository.StoredFileRepository;
import edu.alenkin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class StoredFileServiceImplTest {
    @Mock
    private StoredFileRepository repoMock;

    @InjectMocks
    private StoredFileService service = new StoredFileServiceImpl();
    private ArgumentCaptor<Long> idCaptor;
    private ArgumentCaptor<StoredFile> fileCaptor;

    private Long testId;
    private StoredFile testFile;
    private StoredFile newFile;
    private StoredFile savedNewFile;
    private List<StoredFile> testFilesList;


    @Before
    public void setUp() {
        Mockito.reset(repoMock);
        testId = 1000L;
        testFile = new StoredFile(1000L, "Chuck://test/testDir/testFile.pdf", 9000, new User());
        newFile = new StoredFile("NEW", 9000, new User());
        savedNewFile = new StoredFile(1L, "NEW", 9000, new User());
        idCaptor = ArgumentCaptor.forClass(Long.class);
        fileCaptor = ArgumentCaptor.forClass(StoredFile.class);
        testFilesList = List.of(testFile);
    }

    @Test
    public void getTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testFile);
        StoredFile current = service.get(testId);
        Mockito.verify(repoMock).get(idCaptor.capture());
        assertEquals(testId, idCaptor.getValue());
        assertEquals(testFile, current);
    }

    @Test
    public void getAllTest() {
        Mockito.when(repoMock.getAll()).thenReturn(testFilesList);
        List<StoredFile> currentList = service.getAll();
        Mockito.verify(repoMock).getAll();
        assertEquals(testFilesList, currentList);
    }

    @Test
    public void getAllForUserTest() {
        Mockito.when(repoMock.getAllForUser(testId)).thenReturn(testFilesList);
        List<StoredFile> currentList = service.getAllForUser(testId);
        Mockito.verify(repoMock).getAllForUser(testId);
        assertEquals(testFilesList, currentList);
    }

    @Test
    public void updateTest() {
        Mockito.when(repoMock.create(testFile)).thenReturn(testFile);
        StoredFile current = service.update(testFile);
        Mockito.verify(repoMock).create(fileCaptor.capture());
        assertEquals(testFile, fileCaptor.getValue());
        assertEquals(testFile, current);
    }

    @Test
    public void createTest() {
        Mockito.when(repoMock.create(newFile)).thenReturn(savedNewFile);
        StoredFile saved = service.create(newFile);
        Mockito.verify(repoMock).create(fileCaptor.capture());
        assertEquals(newFile, fileCaptor.getValue());
        assertEquals(savedNewFile, saved);
    }

    @Test
    public void deleteTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testFile);
        service.delete(testFile);
        Mockito.verify(repoMock).get(idCaptor.capture());
        Mockito.verify(repoMock).delete(fileCaptor.capture());
        assertEquals(testFile, fileCaptor.getValue());
        assertEquals(testId, idCaptor.getValue());
    }
    @Test
    public void deleteByIdTest() {
        service.delete(testId);
        Mockito.verify(repoMock).delete(idCaptor.capture());
        assertEquals(testId, idCaptor.getValue());
    }

}