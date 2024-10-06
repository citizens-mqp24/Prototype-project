package com.citizensmqp.backend.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Message")
public class msgModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long message_id;

    @Column(nullable = false)
    private String message_text;


    @Column(nullable = false)
    private Long likes;


    @ManyToOne()
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id"
            , nullable = false)
    private userModel user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<userModel> usersLiked;

    private Long comment_count;

    @ManyToOne
    @JoinTable(
            name = "Comments",
            joinColumns = @JoinColumn(name = "comment_message_id"),
            inverseJoinColumns = @JoinColumn(name = "main_message_id"))
    msgModel MainMessage;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Comments",
            joinColumns = @JoinColumn(name = "main_message_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_message_id"))
    Set<msgModel> comments;


    public Long getMessage_id() {
        return message_id;
    }

    public msgModel(String cont, userModel auth){
        this.message_text = cont;
        this.user = auth;
    }

    public msgModel(){
        this.message_text = "";
        this.user = new userModel();
    }

    public String getMessage_text() {
        return message_text;
    }

    public void addLike(userModel usr) {
        this.likes++;
        this.usersLiked.add(usr);
    }
    public void addComment(msgModel msg) {
        this.comment_count++;
        this.comments.add(msg);
    }

}

