package com.citizensmqp.backend.models;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="Messages")
public class msgModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long msg_ID;

    @Column(nullable = false)
    private String msg_content;

    @Column(nullable = false)
    private userModel author;

    public Long getId() {
        return msg_ID;
    }

    public msgModel(String cont, userModel auth){
        this.msg_content = cont;
        this.author = auth;
    }

    public String getContent() {
        return msg_content;
    }
}

