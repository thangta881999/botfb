package com.TP.IService;

import com.TP.entity.RoleEntity;

import java.util.List;

public interface IRole {
	List<RoleEntity> LayDanhSachRole();
	List<RoleEntity> findAll(int offset, int limit);

	boolean xoaRoletheoid(int id);
	boolean themRole(RoleEntity roleEntity);
	boolean capnhatRole(RoleEntity RoleEntity);
	int save(RoleEntity RoleUser);
}
