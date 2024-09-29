package com.citizensmqp.backend.services;

import com.citizensmqp.backend.ValueObjects.googleUserInfoVO;
import com.citizensmqp.backend.controller.UnauthorizedException;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.repositorys.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;
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

    public googleUserInfoVO fetchUserInfo(String accessToken) throws UnauthorizedException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        URI userInfoUri = URI.create("https://www.googleapis.com/oauth2/v2/userinfo");

        ResponseEntity<googleUserInfoVO> responseEntity = restTemplate.exchange(
                userInfoUri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                googleUserInfoVO.class
        );

        System.out.println(responseEntity);
        if (responseEntity.getStatusCode().is2xxSuccessful() == false) {
            throw new UnauthorizedException();
        }
        googleUserInfoVO userInfo = responseEntity.getBody();
        return userInfo;
    }
}
