package edu.alenkin.service;

import edu.alenkin.model.User;
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

import static org.junit.Assert.assertEquals;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository repoMock;

    @InjectMocks
    private UserService service = new UserServiceImpl();
    private ArgumentCaptor<Long> idCaptor;
    private ArgumentCaptor<User> userCaptor;

    private Long testId;
    private User testUser;
    private User newUser;
    private User savedNewUser;
    private List<User> testUsersList;


    @Before
    public void setUp() {
        Mockito.reset(repoMock);
        testId = 1000L;
        testUser = new User("Test", testId);
        newUser = new User("New");
        savedNewUser = new User("New", 100L);
        idCaptor = ArgumentCaptor.forClass(Long.class);
        userCaptor = ArgumentCaptor.forClass(User.class);
        testUsersList = List.of(testUser);
    }

    @Test
    public void getWithFilesAndEventsTest() {
        Mockito.when(repoMock.getWithFilesAndEvents(testId)).thenReturn(testUser);
        User current = service.getWithFilesAndEvents(testId);
        Mockito.verify(repoMock).getWithFilesAndEvents(idCaptor.capture());
        assertEquals(testId, idCaptor.getValue());
        assertEquals(testUser, current);
    }

    @Test
    public void getTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testUser);
        User current = service.get(testId);
        Mockito.verify(repoMock).get(idCaptor.capture());
        assertEquals(testId, idCaptor.getValue());
        assertEquals(testUser, current);
    }

    @Test
    public void getAllTest() {
        Mockito.when(repoMock.getAll()).thenReturn(testUsersList);
        List<User> currentList = service.getAll();
        Mockito.verify(repoMock).getAll();
        assertEquals(testUsersList, currentList);
    }

    @Test
    public void updateTest() {
        Mockito.when(repoMock.create(testUser)).thenReturn(testUser);
        User current = service.update(testUser);
        Mockito.verify(repoMock).create(userCaptor.capture());
        assertEquals(testUser, userCaptor.getValue());
        assertEquals(testUser, current);
    }

    @Test
    public void createTest() {
        Mockito.when(repoMock.create(newUser)).thenReturn(savedNewUser);
        User saved = service.create(newUser);
        Mockito.verify(repoMock).create(userCaptor.capture());
        assertEquals(newUser, userCaptor.getValue());
        assertEquals(savedNewUser, saved);
    }

    @Test
    public void deleteTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testUser);
        service.delete(testUser);
        Mockito.verify(repoMock).get(idCaptor.capture());
        Mockito.verify(repoMock).delete(userCaptor.capture());
        assertEquals(testUser, userCaptor.getValue());
        assertEquals(testId, idCaptor.getValue());
    }
}