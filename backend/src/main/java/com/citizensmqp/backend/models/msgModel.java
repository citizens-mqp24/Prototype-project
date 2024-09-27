package com.citizensmqp.backend.models;
import jakarta.persistence.*;

@Entity
@Table(name="Message")
public class msgModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long message_id;

    @Column(nullable = false)
    private String message_text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private userModel user_id;



    public Long getId() {
        return message_id;
    }

    public msgModel(String cont, userModel auth){
        this.message_text = cont;
        this.user_id = auth;
    }

    public msgModel(){
        this.message_text = "";
        this.user_id = new userModel();
    }

    public String getContent() {
        return message_text;
    }
}

