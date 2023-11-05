package com.utcn.sd_project.repository;

import com.utcn.sd_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    User findByUsername(@Param("username")String username);
}
