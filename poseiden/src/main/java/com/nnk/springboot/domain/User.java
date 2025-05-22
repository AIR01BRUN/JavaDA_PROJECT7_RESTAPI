package com.nnk.springboot.domain;

import javax.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Column
    private String username;
    // @Size(min = 2, message = "Password must be at least 8 character")
    // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password
    // must contain at least one letter and one number")
    @Column
    private String password;
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
