package com.citizensmqp.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="Test")
public class testModel {
    @Id
    private Long testID;
}
