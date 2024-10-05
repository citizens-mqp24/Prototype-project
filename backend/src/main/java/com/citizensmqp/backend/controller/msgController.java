package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.ValueObjects.msgVO;
import com.citizensmqp.backend.ValueObjects.userVO;
import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.services.msgService;
import com.citizensmqp.backend.services.userService;
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
    private final msgService msgService;
    private final userService userService;

    @GetMapping
    public List<msgVO> getAllMsgs() {
        List<msgVO> msgs = msgService.getMsgs().stream().map(msgModel -> new msgVO().mapMessageNoUsersLiked(msgModel)).toList();
        return msgs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<msgVO> getMsgById(@PathVariable Long id) {
        Optional<msgVO> msg = msgService.getMsgById(id).map(msgModel -> new msgVO().mapFullMessage(msgModel));
        return msg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Calls method in msgService to create new message in db
    @PostMapping
    public ResponseEntity<String> createMsg(@RequestBody msgModel msg) throws Exception {
        msgService.newMessage(msg);
        return ResponseEntity.ok("created message");
    }

    @PostMapping("/likes/{msgId}")
    public ResponseEntity<msgVO> likesMsg(@PathVariable Long msgId,@RequestBody userVO usr) {
        Optional<userModel> fullUser = userService.getUserByEmailWithLikes(usr.getEmail());
        if (fullUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<msgModel> msg = msgService.getMsgById(msgId); // we need to fetch with likes here so the context is initialized
        if(msg.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        msgModel likedMessage =   msgService.likeMsg(msg.get(), fullUser.get());
        msgVO responseMsg = new msgVO();
        responseMsg.mapFullMessage(likedMessage);
        return ResponseEntity.ok(responseMsg);
    }
}
