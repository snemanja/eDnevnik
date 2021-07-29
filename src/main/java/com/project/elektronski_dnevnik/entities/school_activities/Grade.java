package com.project.elektronski_dnevnik.entities.school_activities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elektronski_dnevnik.entities.persons.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="grades")
@Getter
@Setter
public class Grade {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Min(1)
	@Max(5)
	private Integer mark;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Semester semester;

	@ManyToOne
	private Student student;

}
