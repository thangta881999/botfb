package com.TP.DAO;

import com.TP.IService.IRole;
import com.TP.entity.RoleEntity;
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
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
@Transactional
public class RoleDAO implements IRole {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean themRole(RoleEntity roleEntity) {
		Session session = sessionFactory.getCurrentSession();
		int id = (Integer) session.save(roleEntity);
		return true;
	}

	@Override
	public boolean capnhatRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(roleEntity);
		return true;

	}

	public boolean xoaRoletheoid(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		RoleEntity roleEntity = session.get(RoleEntity.class, id);
		session.createQuery("delete ROLE WHERE id=" + id).executeUpdate();
		return true;
	}

	@Transactional
	public List<RoleEntity> LayDanhSachRole() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query="from ROLE";
		List<RoleEntity> roleUsers=(List<RoleEntity>)session.createQuery(query).getResultList();
		
		return roleUsers;
	}

	@Override
	@Transactional
	public int save(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query="FROM ROLE " +
				"WHERE name='"+roleEntity.getName()+"'";
		List<RoleEntity> roleEntities = session.createQuery(query).getResultList();
		
		 
		if (roleEntities.size()>0)
		{
			return roleEntities.get(0).getId();
		}
		return (int) session.save(roleEntity);
	
	
	}
	@Transactional
	public List<RoleEntity> findAll(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from ROLE";
		List<RoleEntity> danhsachRoles = new ArrayList<RoleEntity>();

		if (offset<0)
		{
			danhsachRoles = session.createQuery(query).getResultList();
		}
		else
		{
			danhsachRoles  = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}


		return danhsachRoles;
	}

}
