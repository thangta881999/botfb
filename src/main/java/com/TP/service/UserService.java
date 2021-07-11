package com.TP.service;

import com.TP.DAO.UserDAO;
import com.TP.DTO.MyUser;
import com.TP.DTO.SanPhamDTO;
import com.TP.IService.IUser;
import com.TP.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUser{

	@Autowired
	UserDAO userDAO;



	public UserEntity loadUserbyUsername(String username,int status)
	{
		return userDAO.loadUserbyUsername(username, status);
	}

	@Override
	public UserEntity findUserById(int id) {
		return userDAO.findUserById(id);
	}


	@Override
	public boolean save(UserEntity UserEntity) {
		// TODO Auto-generated method stub
		return userDAO.save(UserEntity);
	}

	public List<MyUser> findAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return userDAO.findAll(offset, limit);
	}
}
