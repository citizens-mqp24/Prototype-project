package com.citizensmqp.backend.services;

import com.citizensmqp.backend.repositorys.exampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class exampleService {

    private final exampleRepository repository;

    public List<String> GetAll() {
        return repository.findAll();
    }

}
