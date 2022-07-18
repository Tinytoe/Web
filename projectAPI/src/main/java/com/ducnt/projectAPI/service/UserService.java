package com.ducnt.projectAPI.service;

import java.util.List;

import com.ducnt.projectAPI.model.UserDTO;

public interface UserService {
	
	void create(UserDTO userDTO);
	
	void update(UserDTO userDTO);
	
	void updatePassword(UserDTO userDTO);
	
	void delete(int id);
	
	List<UserDTO> getAll();
	
	UserDTO getOne(int id);
	
}
