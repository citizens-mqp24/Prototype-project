package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.ValueObjects.msgVO;
import com.citizensmqp.backend.ValueObjects.userVO;
import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.services.msgService;
import com.citizensmqp.backend.services.userService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.datadog.DatadogMeterRegistry;
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
    private final DatadogMeterRegistry ddRegistry;

    @Timed
    @GetMapping
    public List<msgVO> getAllMsgs() {
        ddRegistry.counter("msgs.requested").increment();
        List<msgVO> msgs = msgService.getMsgs().stream().map(msgModel -> new msgVO().mapMessageCore(msgModel)).toList();
        return msgs;
    }


    @Timed
    @GetMapping("/{id}")
    public ResponseEntity<msgVO> getMsgById(@PathVariable Long id) {
        Optional<msgVO> msg = msgService.getMessageByIdFull(id).map(msgModel -> new msgVO().mapMessageCore(msgModel).mapLikedUsers(msgModel).mapComments(msgModel));
        return msg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Calls method in msgService to create new message in db
    @Timed
    @PostMapping
    public ResponseEntity<String> createMsg(@RequestBody msgModel msg) throws Exception {
        msgService.newMessage(msg);
        ddRegistry.counter("msgs.created").increment();
        return ResponseEntity.ok("created message");
    }

    @Timed
    @PostMapping("/comment/{id}")
    public ResponseEntity<String> createMsg(@RequestBody msgModel msg,@PathVariable Long id) throws Exception {
        msgService.addComment(id,msg);
        return ResponseEntity.ok("created message");
    }

    @Timed
    @PostMapping("/likes/{msgId}")
    public ResponseEntity<msgVO> likesMsg(@PathVariable Long msgId,@RequestBody userVO usr) {
        Optional<userModel> fullUser = userService.getUserByEmailWithLikes(usr.getEmail());
        if (fullUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        msgModel likedMessage =   msgService.likeMsg(msgId, fullUser.get());
        msgVO responseMsg = new msgVO();
        responseMsg.mapMessageCore(likedMessage).mapLikedUsers(likedMessage);
        return ResponseEntity.ok(responseMsg);
    }
}
