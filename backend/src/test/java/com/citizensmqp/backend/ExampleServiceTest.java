package com.citizensmqp.backend;

import com.citizensmqp.backend.repositorys.exampleRepository;
import com.citizensmqp.backend.services.exampleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExampleServiceTest {

    @Mock
    private exampleRepository repository;

    @InjectMocks
    private exampleService es;

    @Test
    public void testGetALl() {
        // db clean and setup code
        List<String> expectsList = new LinkedList<>();
        expectsList.add("A");
        expectsList.add("B");
        expectsList.add("C");
        when(repository.findAll()).thenReturn(expectsList);
        assertIterableEquals(es.GetAll(),expectsList);
    }
}
