package com.ducnt.project2.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	private int studentId;
	private String studentCode;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	List<Score> scores;
}
