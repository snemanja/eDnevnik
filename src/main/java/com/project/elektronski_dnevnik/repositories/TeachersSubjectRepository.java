package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.school_activities.TeacherSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersSubjectRepository extends CrudRepository<TeacherSubject, Integer> {
}
