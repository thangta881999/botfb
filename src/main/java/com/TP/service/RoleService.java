package com.TP.service;

import com.TP.DAO.RoleDAO;
import com.TP.IService.IRole;
import com.TP.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRole {
	@Autowired
	RoleDAO roleDAO;



	public List<RoleEntity> findAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return roleDAO.findAll(offset, limit);
	}
	@Override
	public int save(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		return roleDAO.save(roleEntity);
	}

	public boolean xoaRoletheoid(int id) {
		return 	roleDAO.xoaRoletheoid(id);
	}

	public boolean themRole(RoleEntity roleEntity) {
		return roleDAO.themRole(roleEntity);
	}

	public boolean capnhatRole(RoleEntity roleEntity) {
		return roleDAO.capnhatRole(roleEntity);
	}
	public List<RoleEntity> LayDanhSachRole() {

		return roleDAO.LayDanhSachRole();
	}

}
