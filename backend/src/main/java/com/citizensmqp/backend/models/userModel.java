package com.citizensmqp.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Data // generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="User")
public class userModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    Set<msgModel> likes;

    public void addLike(msgModel msg) {
        likes.add(msg);
    }
}
