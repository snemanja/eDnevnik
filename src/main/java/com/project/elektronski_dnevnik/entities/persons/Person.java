package com.project.elektronski_dnevnik.entities.persons;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Column
	private String name;

	@Column
	private String surName;

}
