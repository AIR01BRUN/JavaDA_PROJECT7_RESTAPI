package com.nnk.springboot.domain;

import javax.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Username for authentication
     */
    @NotBlank(message = "Username is mandatory")
    @Column
    private String username;

    /**
     * User's password (will be encrypted)
     */
    @Column
    private String password;

    /**
     * User's full name
     */
    @NotNull(message = "FullName is mandatory")
    @Column
    private String fullname;
    @NotNull(message = "Role is mandatory")
    @Column
    private String role;
    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean enabled = true;

    public User(String username,
            String password,
            String fullname,
            String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
