package com.citizensmqp.backend.services;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.repositorys.msgRepository;
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
        msgRepo.save(msg);
    }

    public List<msgModel> getMsgs(){
        return msgRepo.findAll();
    }

    public Optional<msgModel> getMsgById(long id){return(msgRepo.findById(id));}

    public msgModel likeMsg(msgModel msg,userModel usr) {
        msg.addLike(usr);
        msg.setLikes(msg.getLikes()+1);

        return msgRepo.save(msg);

    }

}