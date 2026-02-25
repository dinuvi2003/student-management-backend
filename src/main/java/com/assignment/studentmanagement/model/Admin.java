package com.assignment.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Admin {

    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Boolean isActive;

    public Admin(Integer id, String username, String password,
                 String fullName, String email, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }

    // Constructor used when creating new admin (no id)
    public Admin(String username, String password,
                 String fullName, String email, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }

    public Integer getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsActive() { return true; }
}
