package com.TP.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user")
public class UserEntity {
	public static final BCryptPasswordEncoder encode= new BCryptPasswordEncoder();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	private String userName;

	private String password;

	private String fullName;
	private String phone;
	private String diachi;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Integer status;

	
	  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	  
	  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"),
	  inverseJoinColumns = @JoinColumn(name = "roleid"))
	  private List<RoleEntity> roles = new ArrayList<>();
	 

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
		this.password =encode.encode(password);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	  public List<RoleEntity> getRoles() { return roles; }
	  
	  public void setRoles(List<RoleEntity> roles) { this.roles = roles; }
	 
}
