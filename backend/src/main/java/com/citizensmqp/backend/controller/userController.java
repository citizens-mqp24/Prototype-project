package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.ValueObjects.googleUserInfoVO;
import com.citizensmqp.backend.models.testModel;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.services.exampleService;
import com.citizensmqp.backend.services.userService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class userController {
    private final userService service;

    @PostMapping("/info")
    public googleUserInfoVO getUserInfo(@CookieValue(value = "access_token") String access_token) throws UnsupportedEncodingException {
        log.info("get user info");
        log.info(access_token);
        googleUserInfoVO userInfo = service.fetchUserInfo(access_token);
        return userInfo;
    }

    @GetMapping
    public List<userModel> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<userModel> getUserById(@PathVariable Long id) {
        Optional<userModel> user = service.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public userModel createUser(@RequestBody userModel user) {
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
