package com.citizensmqp.backend.ValueObjects;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class msgVO {
    private Long message_id;

    private String message_text;

    private Long likes;

    private userModel user;

    Set<userModel> usersLiked;

    //this is for lazy fetching that way we dont always need to get all the liked users cause with alot of users it could get slow
    public msgVO mapMessageNoUsersLiked(msgModel msg) {
        this.setMessage_id(msg.getMessage_id());
        this.setUser(msg.getUser());
        this.setLikes(msg.getLikes());
        this.setMessage_text(msg.getMessage_text());
        return this;
    }

    public msgVO mapFullMessage(msgModel msg) {
        this.setMessage_id(msg.getMessage_id());
        this.setUsersLiked(msg.getUsersLiked());
        this.setLikes(msg.getLikes());
        this.setUser(msg.getUser());
        this.setMessage_text(msg.getMessage_text());
        return this;
    }
}
