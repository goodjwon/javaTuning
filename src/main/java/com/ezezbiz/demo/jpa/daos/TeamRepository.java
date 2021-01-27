package com.ezezbiz.demo.jpa.daos;

import com.ezezbiz.demo.jpa.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository <Team, Long> {
}
