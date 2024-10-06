package com.citizensmqp.backend.ValueObjects;

import com.citizensmqp.backend.models.userModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class userVO {

    private String user_id;

    private String name;

    private String email;

    private String picture;

    Set<msgVO> likes;
    public userVO setupUserWithoutLikes(userModel usr) {
        this.setUser_id(usr.getUser_id());
        this.setName(usr.getName());
        this.setPicture(usr.getPicture());
        this.setPicture(usr.getEmail());
        return this;
    }
    public userVO setupUserLikes(userModel usr) {
        this.setupUserWithoutLikes(usr)
        .setLikes(usr.getLikes().stream().map(msg -> {return new msgVO().mapMessageCore(msg);}).collect(Collectors.toSet()));
        return this;
    }
}
