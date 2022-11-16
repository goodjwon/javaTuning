package com.ezezbiz.demo.jpa.daos;

import com.ezezbiz.demo.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
