package com.utcn.sd_project.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String role;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;

        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;

        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;

        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;

        return this;
    }
}
