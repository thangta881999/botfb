package com.TP.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "role")

public class RoleEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
//	
	@ManyToMany(mappedBy = "roles")
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "roleid"), 
//								  inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<UserEntity> users = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
