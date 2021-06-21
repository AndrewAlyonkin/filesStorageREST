package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.BaseEntity;
import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;
import edu.alenkin.model.User;
import edu.alenkin.service.Service;
import edu.alenkin.util.JsonConverter;
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

import static edu.alenkin.TestData.CHUCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class JsonBaseControllerTest<ID extends Number, T extends BaseEntity> {

    @Mock
    private Service<Long, T> serviceMock;

    @InjectMocks
    private Controller<String, Long, T> controller;

    private T entity;
    private final String entityJson;
    private final String listEntityJson;

    private ArgumentCaptor<Long> idCaptor;

    public JsonBaseControllerTest(Controller<String, Long, T> controller, String entityJson, String listEntityJson) {
        this.controller = controller;
        this.entityJson = entityJson;
        this.listEntityJson = listEntityJson;
    }

    @Before
    public void setUp() {
        Mockito.reset(serviceMock);
        idCaptor = ArgumentCaptor.forClass(Long.class);
        entity = getNew();
    }

    protected abstract T getNew();

    @Test
    public void get() {
        Mockito.when(serviceMock.get(Mockito.any())).thenReturn(entity);
        System.out.println(controller.get(1L));
        assertEquals(entityJson, controller.get(1L));
    }
    @Test
    public void getAll() {
        Mockito.when(serviceMock.getAll()).thenReturn(List.of(entity, entity));
        assertEquals(listEntityJson, controller.getAll());
    }
    @Test
    public void delete() {
        controller.delete(1L);
        Mockito.verify(serviceMock).delete(idCaptor.capture());
        assertEquals(Long.valueOf(1L), idCaptor.getValue());
    }
    @Test
    public void create() {
        Mockito.when(serviceMock.create(Mockito.any())).thenReturn(entity);
        assertThat(controller.create(entityJson)).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    public void update() {
        Mockito.when(serviceMock.create(Mockito.any())).thenReturn(entity);
        assertEquals(entity, controller.create(entityJson));
    }


}