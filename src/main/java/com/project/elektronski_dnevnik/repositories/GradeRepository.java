package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.persons.Student;
import com.project.elektronski_dnevnik.entities.school_activities.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Integer> {

	List<Grade> findAllByStudentId(Integer studentId);

	List<Grade> findAllByStudentIdAndMark(Integer studentId, Integer mark);

	List<Grade> findAllByStudentIn(List<Student> students);

	List<Grade> findAllByStudentInAndMark(List<Student> students, Integer mark);
}
