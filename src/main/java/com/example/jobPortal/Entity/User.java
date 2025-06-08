package com.example.jobPortal.Entity;

import com.example.jobPortal.Dto.UserDto;
import com.example.jobPortal.Enum.Role;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User(Long id, String userName, String email, String password, Role role, Date createdAt, Date updatedAt) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public User() {

	}

	public UserDto converToDto() {
		return new UserDto(this.id != null ? this.id : null, this.userName != null ? this.userName : null,
				this.email != null ? this.email : null, this.role != null ? this.role : null,
				this.createdAt != null ? this.createdAt : null, this.updatedAt != null ? this.updatedAt : null);
	}
}
