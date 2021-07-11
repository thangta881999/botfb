package com.TP.service;

import com.TP.DAO.UserDAO;
import com.TP.DTO.MyUser;
import com.TP.entity.RoleEntity;
import com.TP.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 	 UserEntity userEntity = userDAO.loadUserbyUsername(username,1);
		
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		  for (RoleEntity role: userEntity.getRoles()) { authorities.add(new
		  SimpleGrantedAuthority(role.getCode())); }
		 
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), 
							true, true, true, true, authorities);
		myUser.setFullName(userEntity.getFullName());
		myUser.setUserId(userEntity.getId());
		myUser.setDiachi(userEntity.getDiachi());
		myUser.setPhone(userEntity.getPhone());
		return myUser;
	}

	
}
