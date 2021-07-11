package com.TP.DAO;

import com.TP.DTO.MyUser;
import com.TP.DTO.SanPhamDTO;
import com.TP.IService.IUser;
import com.TP.entity.SanPham;
import com.TP.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDAO implements IUser{
	@Autowired
	SessionFactory sessionFactory;


	@Transactional
	public UserEntity loadUserbyUsername(String username,int status) {
		Session session = sessionFactory.getCurrentSession();
		try {
			UserEntity user =(UserEntity) session.createQuery("from user where username='" + username + "' and status='" + status + "'").getSingleResult();


			if (user!=null) {
				return user;
			}

			else {
				return null;
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	@Transactional
	public UserEntity findUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			UserEntity user =(UserEntity) session.createQuery("from user where id='" + id + "'").getSingleResult();


			if (user!=null) {
				return user;
			}

			else {
				return null;
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Transactional
	public UserEntity loadUserbyUsernameAndPassword(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		try {
			UserEntity user =(UserEntity) session.createQuery("from user where username='" + username + "' and  password='" +  password + "'").getSingleResult();


			if (user!=null) {
				return user;
			}

			else {
				return null;
			}
		} catch (Exception e) {
			return null;

		}
	}
	@Override
	public List<MyUser> findAll(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		List<MyUser> models = new ArrayList<MyUser>();
		List<UserEntity> listUsers = new ArrayList<UserEntity>();
		String query = "from USER";
		if (offset < 0) {
			listUsers = session.createQuery(query).getResultList();
		} else {
			listUsers = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}
//		for (UserEntity item : listUsers) {
//			SanPhamDTO userDTO = userConverter.toDTO(item);
//			models.add(userDTO);
//		}
		return models;
	}
	@Transactional

	@Override
	public boolean save(UserEntity UserEntity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		if (UserEntity.getId() >0)
		{
			session.update(UserEntity);
		}
		int id = (Integer) session.save(UserEntity);
		if(id > 0)
			return true;
		else
			return false;
	}
}
