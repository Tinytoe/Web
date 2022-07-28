package com.ducnt.projectdemo.model;

import java.util.List;

import lombok.Data;
@Data
public class UserDTO {
	private int id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private List<String> roles;
}
