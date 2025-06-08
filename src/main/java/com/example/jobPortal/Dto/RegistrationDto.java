package com.example.jobPortal.Dto;

import com.example.jobPortal.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Username should not empty or null ")
    private String userName;
    @NotEmpty(message = "Password should not empty or null ")
    private String password;
    @Email(message = "Email should be in proper format")
    @NotEmpty(message = "Email should not empty or null ")
    private String email;
    @NotNull(message = "Role should be provided")
    private Role role  ;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public RegistrationDto() {
    }

    public RegistrationDto(Long id, String userName, String password, String email, Role role, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
