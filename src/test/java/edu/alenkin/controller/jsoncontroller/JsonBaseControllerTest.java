package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.controller.Controller;
import edu.alenkin.model.BaseEntity;
import edu.alenkin.service.Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

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
    private final String resultJsonFromEntity;
    private final String jsonForParsingToEntity;
    private final String listResultJsonFromEntities;
    private final String jsonForParsingToEntities;

    private ArgumentCaptor<Long> idCaptor;

    public JsonBaseControllerTest(Controller controller,
                                  String resultJsonFromEntity,
                                  String jsonForParsingToEntity,
                                  String listResultJsonFromEntities,
                                  String jsonForParsingToEntities) {
        this.controller = controller;
        this.resultJsonFromEntity = resultJsonFromEntity;
        this.jsonForParsingToEntity = jsonForParsingToEntity;
        this.listResultJsonFromEntities = listResultJsonFromEntities;
        this.jsonForParsingToEntities = jsonForParsingToEntities;
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
        assertEquals(resultJsonFromEntity, controller.get(1L));
    }

    @Test
    public void getAll() {
        Mockito.when(serviceMock.getAll()).thenReturn(List.of(entity, entity));
        assertEquals(listResultJsonFromEntities, controller.getAll());
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
        assertThat(controller.create(jsonForParsingToEntity)).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    public void update() {
        Mockito.when(serviceMock.create(Mockito.any())).thenReturn(entity);
        assertEquals(entity, controller.create(jsonForParsingToEntity));
    }
}