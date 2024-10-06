package com.citizensmqp.backend.ValueObjects;

import com.citizensmqp.backend.models.msgModel;
import com.citizensmqp.backend.models.userModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class msgVO {
    private Long message_id;

    private String message_text;

    private Long likes;

    private userVO user;

    Set<userVO> usersLiked;

    Long comment_count;

    List<msgVO> comments;

    msgVO mainMessage;

    //this is for lazy fetching that way we dont always need to get all the liked users cause with alot of users it could get slow
    public msgVO mapMessageCore(msgModel msg) {
        this.setMessage_id(msg.getMessage_id());
        this.setUser(new userVO().setupUserWithoutLikes(msg.getUser()));
        this.setLikes(msg.getLikes());
        this.setComment_count(msg.getComment_count());
        this.setMessage_text(msg.getMessage_text());
        if(msg.getMainMessage() != null) {
            this.setMainMessage(new msgVO().mapMessageCore(msg.getMainMessage()));
        }
        return this;
    }

    public msgVO mapComments(msgModel msg) {
        this.setComments(msg.getComments().stream().map(msgModel -> new msgVO().mapMessageCore(msgModel)).toList());
        return this;
    }

    public msgVO mapLikedUsers(msgModel msg) {
        this.setUsersLiked(msg.getUsersLiked().stream().map(usr -> new userVO().setupUserWithoutLikes(usr)).collect(Collectors.toSet()));
        return this;
    }

}
