package com.citizensmqp.backend.services;

import com.citizensmqp.backend.models.userModel;
import com.citizensmqp.backend.repositorys.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class msgService {
    private final msgRepository msgRepo;

    public void saveMsg(msgModel msg){
        return msgRepo.save(msg);
    }

    public List<msgModel> getMsgs(){
        return msgRepo.findAll();
    }
}