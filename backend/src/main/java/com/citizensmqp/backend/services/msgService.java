package com.citizensmqp.backend.services;

import com.citizensmqp.backend.ValueObjects.msgVO;
import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.repositorys.msgRepository;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class msgService {
    private final msgRepository msgRepo;
    private final userService userService;

    public void newMessage(msgModel msg) throws Exception {
        Optional<userModel> user = userService.getUserByEmail(msg.getUser().getEmail());
        if(user.isEmpty()) {
            throw new Exception(""); //TODO make custom excption
        }
        msg.setUser(user.get());
        msg.setLikes(0L);
        msg.setComment_count(0L);
        msgRepo.save(msg);
    }

    public List<msgModel> getMsgs(){
        return msgRepo.findAll();
    }

    public Optional<msgModel> getMsgById(long id){return(msgRepo.findById(id));}

    public Optional<msgModel> getMsgByIdFull(long id){return(msgRepo.getMessageByIdFull(id));}



    public void addComment(long id, msgModel comment) throws Exception {
        Optional<userModel> user = userService.getUserByEmail(comment.getUser().getEmail());
        if(user.isEmpty()) {
            throw new Exception(""); //TODO make custom excption
        }
        comment.setUser(user.get());
        Optional<msgModel> mainMsg = msgRepo.getMessageWithComments(id);
        if (mainMsg.isEmpty()) {
            throw new Exception("No message found"); // TODO make custom exception
        }
        msgRepo.save(comment);
        mainMsg.get().addComment(comment);
        msgRepo.save(mainMsg.get());
    }


    public msgModel likeMsg(Long id, userModel usr) {
        //TODO add prevention for if the user has already liked the message
        Optional<msgModel> msg = msgRepo.getMessageWithLikes(id);
        if(msg.isEmpty()) {
            return null;
        }
        msg.get().addLike(usr);
        return msgRepo.save(msg.get());

    }

    public Optional<msgModel>  getMessageByIdFull(Long id) {
        return msgRepo.getMessageByIdFull(id);
    }
}