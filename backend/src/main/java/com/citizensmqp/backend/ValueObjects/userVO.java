package com.citizensmqp.backend.ValueObjects;

import com.citizensmqp.backend.models.msgModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class userVO {

    private String user_id;

    private String name;

    private String email;

    private String picture;

    Set<msgVO> likes;
}
