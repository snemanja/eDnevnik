package com.project.elektronski_dnevnik.repositories;

import com.project.elektronski_dnevnik.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
