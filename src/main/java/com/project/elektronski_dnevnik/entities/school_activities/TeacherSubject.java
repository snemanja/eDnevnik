package com.project.elektronski_dnevnik.entities.school_activities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.elektronski_dnevnik.entities.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subjects")
@Getter
@Setter
public class TeacherSubject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String subjectName; //biologija

	@Column
	private Integer weeklyFond;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Teacher teacher;

	@OneToMany(mappedBy = "teacherSubject")
	private List<StudentClass> studentClasses = new ArrayList<>();;
}
