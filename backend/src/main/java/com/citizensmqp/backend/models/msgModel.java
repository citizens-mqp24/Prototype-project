package com.citizensmqp.backend.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Message")
public class msgModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long message_id;

    @Column(nullable = false)
    private String message_text;

    @Getter
    @Setter
    @Column(nullable = false)
    private Long likes;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private userModel user;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    Set<userModel> usersLiked;

    public void addLike(userModel user) {
        usersLiked.add(user);
    }

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
}

