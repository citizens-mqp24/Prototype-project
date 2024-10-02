package com.citizensmqp.backend.models;

import jakarta.persistence.*;
import lombok.*;
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
}
