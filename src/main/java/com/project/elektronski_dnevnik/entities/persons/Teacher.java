package com.project.elektronski_dnevnik.entities.persons;

import com.project.elektronski_dnevnik.entities.school_activities.TeacherSubject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teachers")
@Getter
@Setter
public class Teacher extends Person {


	@OneToMany(mappedBy = "teacher")
	private List<TeacherSubject> teacherSubjects = new ArrayList<>();
}
