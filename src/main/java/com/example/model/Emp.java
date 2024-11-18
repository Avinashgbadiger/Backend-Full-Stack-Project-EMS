package com.example.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String lastname;
    private String email;

    @Override
    public String toString() {
        return "Emp{id=" + id + ", username='" + username + "', lastname='" + lastname + "', email='" + email + "'}";
    }
}
