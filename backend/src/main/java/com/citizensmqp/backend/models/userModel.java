package com.citizensmqp.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="User")
public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;
}
