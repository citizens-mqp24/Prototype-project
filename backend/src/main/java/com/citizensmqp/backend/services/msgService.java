package com.citizensmqp.backend.services;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.repositorys.msgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class msgService {
    private final msgRepository msgRepo;

    public void saveMsg(msgModel msg){
        msgRepo.save(msg);
    }

    public List<msgModel> getMsgs(){
        return msgRepo.findAll();
    }

    public Optional<msgModel> getMsgById(long id){return(msgRepo.findById(id));}
}