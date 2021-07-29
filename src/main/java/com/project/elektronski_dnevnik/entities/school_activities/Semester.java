package com.project.elektronski_dnevnik.entities.school_activities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "semesters")
@Entity
@Getter
@Setter
public class Semester {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private StudentClass studentClass;

	@OneToMany(mappedBy = "semester")
	private List<Grade> grades = new ArrayList<>();
}