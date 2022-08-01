package com.ducnt.projectdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Member {
	
	@Id
	private int id;
	
	private String memberCode;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;
}
