package edu.alenkin.service;

import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;
import edu.alenkin.model.User;
import edu.alenkin.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository repoMock;

    @InjectMocks
    private EventService service = new EventServiceImpl();
    private ArgumentCaptor<Long> idCaptor;
    private ArgumentCaptor<Event> eventCaptor;

    private Long testId;
    private Event testEvent;
    private Event newEvent;
    private Event savedNewEvent;
    private List<Event> testEventsList;


    @Before
    public void setUp() {
        Mockito.reset(repoMock);
        testId = 1000L;
        testEvent = new Event(testId, Timestamp.valueOf("2021-06-20 19:10:25"), new StoredFile(), new User());
        newEvent = new Event(Timestamp.valueOf("2021-06-20 19:10:25"), new StoredFile(), new User());
        savedNewEvent = new Event(1L, Timestamp.valueOf("2021-06-20 19:10:25"), new StoredFile(), new User());
        idCaptor = ArgumentCaptor.forClass(Long.class);
        eventCaptor = ArgumentCaptor.forClass(Event.class);
        testEventsList = List.of(testEvent);
    }

    @Test
    public void getTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testEvent);
        Event current = service.get(testId);
        Mockito.verify(repoMock).get(idCaptor.capture());
        assertEquals(testId, idCaptor.getValue());
        assertEquals(testEvent, current);
    }

    @Test
    public void getAllTest() {
        Mockito.when(repoMock.getAll()).thenReturn(testEventsList);
        List<Event> currentList = service.getAll();
        Mockito.verify(repoMock).getAll();
        assertEquals(testEventsList, currentList);
    }

    @Test
    public void getAllForUserTest() {
        Mockito.when(repoMock.getAllForUser(testId)).thenReturn(testEventsList);
        List<Event> currentList = service.getAllForUser(testId);
        Mockito.verify(repoMock).getAllForUser(testId);
        assertEquals(testEventsList, currentList);
    }

    @Test
    public void updateTest() {
        Mockito.when(repoMock.create(testEvent)).thenReturn(testEvent);
        Event current = service.update(testEvent);
        Mockito.verify(repoMock).create(eventCaptor.capture());
        assertEquals(testEvent, eventCaptor.getValue());
        assertEquals(testEvent, current);
    }

    @Test
    public void createTest() {
        Mockito.when(repoMock.create(newEvent)).thenReturn(savedNewEvent);
        Event saved = service.create(newEvent);
        Mockito.verify(repoMock).create(eventCaptor.capture());
        assertEquals(newEvent, eventCaptor.getValue());
        assertEquals(savedNewEvent, saved);
    }

    @Test
    public void deleteTest() {
        Mockito.when(repoMock.get(testId)).thenReturn(testEvent);
        service.delete(testEvent);
        Mockito.verify(repoMock).get(idCaptor.capture());
        Mockito.verify(repoMock).delete(eventCaptor.capture());
        assertEquals(testEvent, eventCaptor.getValue());
        assertEquals(testId, idCaptor.getValue());
    }
}