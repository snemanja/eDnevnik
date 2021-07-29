package com.project.elektronski_dnevnik.entities.school_activities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student_classes")
@Entity
@Getter
@Setter
public class StudentClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private TeacherSubject teacherSubject;

	@OneToMany(mappedBy = "studentClass")
	private List<Semester> semesters = new ArrayList<>();;
}