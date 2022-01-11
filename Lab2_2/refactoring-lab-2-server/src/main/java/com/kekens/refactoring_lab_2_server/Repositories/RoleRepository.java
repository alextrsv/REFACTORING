package com.kekens.refactoring_lab_2_server.Repositories;

import com.kekens.refactoring_lab_2_server.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
