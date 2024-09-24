package com.citizensmqp.backend.services;

import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.repositorys.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userService {
    private final userRepository repository;

    public List<userModel> getAll() {
        return repository.findAll();
    }

    public Optional<userModel> getUserById(Long id) {
        return repository.findById(id);
    }

    public userModel saveUser(userModel user) {
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
