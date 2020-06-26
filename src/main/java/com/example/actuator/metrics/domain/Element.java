package com.example.actuator.metrics.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(updatable = false)
    private LocalDateTime created;

    @PrePersist
    public void setCreated() {
        this.created = LocalDateTime.now();
    }

}
