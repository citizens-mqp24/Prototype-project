package com.citizensmqp.backend.ValueObjects;

import com.citizensmqp.backend.models.userModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SessionVO {
    googleUserInfoVO googleInfo;
    userVO userInfo;

    public SessionVO makeSessionWithLikes(googleUserInfoVO googleInfo, userModel user) {
        userVO userInfo = new userVO();
        userInfo.setUser_id(user.getUser_id());
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPicture(user.getPicture());
        userInfo.setLikes(user.getLikes().stream().map(msgModel -> new msgVO().mapMessageCore(msgModel)).collect(Collectors.toSet()));
        this.setUserInfo(userInfo);
        this.setGoogleInfo(googleInfo);
        return this;
    }

    public SessionVO makeSessionWithoutLikes(googleUserInfoVO googleInfo, userModel user) {
        userVO userInfo = new userVO().setupUserWithoutLikes(user);
        this.setUserInfo(userInfo);
        this.setGoogleInfo(googleInfo);
        return this;
    }
}
