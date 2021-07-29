package com.project.elektronski_dnevnik.entities.persons;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="students")
@Getter
@Setter
public class Student extends Person {

	
	@ManyToOne
	private Parent parent;
}