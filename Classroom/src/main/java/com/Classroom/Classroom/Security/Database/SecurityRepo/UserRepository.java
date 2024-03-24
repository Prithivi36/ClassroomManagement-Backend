package com.Classroom.Classroom.Security.Database.SecurityRepo;

import com.Classroom.Classroom.Security.Database.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserName(String username);
}
