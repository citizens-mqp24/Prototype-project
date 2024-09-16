package com.citizensmqp.backend.services;

import com.citizensmqp.backend.models.testModel;
import com.citizensmqp.backend.repositorys.testRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class exampleService {

    private final testRepository repository;

    public List<testModel> GetAll() {
        return repository.findAll();
    }

}
