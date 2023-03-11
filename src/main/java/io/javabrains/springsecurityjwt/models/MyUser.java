package io.javabrains.springsecurityjwt.models;

import jakarta.persistence.*;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String password;

    public MyUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public MyUser() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
