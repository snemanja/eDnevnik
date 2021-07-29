package com.project.elektronski_dnevnik.entities;

import com.project.elektronski_dnevnik.entities.persons.Person;
import com.project.elektronski_dnevnik.enums.ROLE;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String userName;
	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private ROLE role;

	@OneToOne
	private Person person;

}
