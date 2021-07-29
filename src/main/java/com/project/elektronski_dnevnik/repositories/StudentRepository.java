package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.persons.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	List<Student> getAllByIdIn(List<Integer> ids);
}
