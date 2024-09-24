package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.models.testModel;
import com.citizensmqp.backend.services.exampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class msgController {
    private final msgService service;

    @GetMapping
    public List<msgModel> getAllMsgs() {
        return service.getMsgs();
    }
}
