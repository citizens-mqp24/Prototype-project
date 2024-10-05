package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.ValueObjects.SessionVO;
import com.citizensmqp.backend.ValueObjects.googleUserInfoVO;
import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
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
    private final userService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionVO> getUserInfo(@CookieValue(value = "access_token") String access_token) throws UnsupportedEncodingException {
        log.info("get user info");
        log.info(access_token);
        googleUserInfoVO userInfo = userService.fetchUserInfo(access_token);
        SessionVO session = new SessionVO();
        Optional<userModel> dbUser = userService.getUserByEmailWithLikes(userInfo.email);
        if(dbUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        session.makeSessionWithLikes(userInfo,dbUser.get());
        return ResponseEntity.ok(session);
    }

    @GetMapping
    public List<userModel> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<userModel> getUserById(@PathVariable Long id) {
        Optional<userModel> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public userModel createUser(@RequestBody userModel user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
