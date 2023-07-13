package com.launchcode.recipeproject.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity{

    private String username;

    private String email;

    private String passwordHash;

    private String roles; // comma separated list of roles "ROLE_USER,ROLE_ADMIN" default is "ROLE_USER"

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // static so all classes can use

    public User(){}

    public User(String username, String email, String password, String roles){
        this.username = username;
        this.email = email;
        this.passwordHash = passwordEncoder.encode(password);
        this.roles = roles;
    }

    public Boolean isPasswordMatching(String password){
        return passwordEncoder.matches(password,passwordHash); // can't use .equals because of salting
    }

    public void setPassword(String password) {
        this.passwordHash = passwordEncoder.encode(password);
    }

    public String getPassword() {
        return passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}