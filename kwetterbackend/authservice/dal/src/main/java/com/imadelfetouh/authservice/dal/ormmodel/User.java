package com.imadelfetouh.authservice.dal.ormmodel;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private User() {

    }

    private User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }
}
