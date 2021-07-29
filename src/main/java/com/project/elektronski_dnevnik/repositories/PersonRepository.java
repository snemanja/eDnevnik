package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.persons.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
	
}
