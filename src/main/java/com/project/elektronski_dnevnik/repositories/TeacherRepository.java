package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.persons.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
	
	
}
