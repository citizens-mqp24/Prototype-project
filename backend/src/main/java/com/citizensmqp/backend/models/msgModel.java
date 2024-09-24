package com.citizensmqp.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Entity
@Data // generates Getters, Setters, toString, equals, and hashCode
@Table(name="Messages")
public class msgModel {
    @Id
    private Long msg_ID;

    @Column(nullable = false)
    private String msg_content;
    private userModel author;

    public Long getId() {
        return id;
    }

    public msgModel(String cont, userModel auth){
        this.msg_content = cont;
        this.author = auth;
    }


}

