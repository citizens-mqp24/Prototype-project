package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.services.msgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/msg")
@RequiredArgsConstructor
@Slf4j
public class msgController {
    private final msgService service;

    @GetMapping
    public List<msgModel> getAllMsgs() {
        return service.getMsgs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<msgModel> getMsgById(@PathVariable Long id) {
        Optional<msgModel> msg = service.getMsgById(id);
        return msg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Calls method in msgService to create new message in db
    @PostMapping
    public void createMsg(@RequestBody msgModel msg) throws Exception {
        service.newMessage(msg);
    }

    @PostMapping("/likes/{id}")
    public msgModel likesMsg(@PathVariable Long msgId) {
        Optional <msgModel> msg = service.getMsgById(msgId);
        if (msg.isPresent()) {
           return service.likeMsg(msg.get());
        }
        return null;
    }
}
