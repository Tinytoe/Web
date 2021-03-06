package com.ducnt.project3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ducnt.project3.entity.User;
import com.ducnt.project3.repo.UserRepo;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepo.findByUsername(username);

		if(u == null) {
			throw new UsernameNotFoundException("not found");
		}

		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();

		for (String role : u.getRoles()) {
			list.add(new SimpleGrantedAuthority(role));
		}

		// tao user cua security //user dang nhap hien tai
		org.springframework.security.core.userdetails.User currentUser =
				new org.springframework.security.core.userdetails.User(username, u.getPassword(), list);

		return currentUser;

	}

}
