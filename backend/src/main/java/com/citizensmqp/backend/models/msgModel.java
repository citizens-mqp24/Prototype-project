package com.citizensmqp.backend.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
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
        Set<userModel> savingSet = new HashSet<>();
        savingSet.add(usr);
        this.usersLiked = savingSet;
    }
}

