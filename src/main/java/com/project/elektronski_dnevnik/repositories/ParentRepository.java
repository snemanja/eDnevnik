package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.persons.Parent;
import com.project.elektronski_dnevnik.entities.persons.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends CrudRepository<Parent, Integer> {

	Parent findByStudents(Student student);
}
