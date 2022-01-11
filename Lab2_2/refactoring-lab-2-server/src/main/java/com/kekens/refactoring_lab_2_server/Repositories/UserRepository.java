package com.kekens.refactoring_lab_2_server.Repositories;

import com.kekens.refactoring_lab_2_server.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.username = :username ")
    User findByUsername(@Param("username") String username);

}
