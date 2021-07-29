package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.school_activities.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Integer> {
}
