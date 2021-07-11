package com.TP.IService;

import com.TP.DTO.MyUser;
import com.TP.DTO.SanPhamDTO;
import com.TP.entity.UserEntity;

import java.util.List;

public interface IUser {
	List<MyUser> findAll(int offset, int limit);
	UserEntity loadUserbyUsername(String username,int status);
	UserEntity findUserById(int id);
	boolean save(UserEntity UserEntity);
}
