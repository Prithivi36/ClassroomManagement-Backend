package com.Classroom.Classroom.Security.Database.SecurityRepo;

import com.Classroom.Classroom.Security.Database.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RolesEntity,Long> {
    Optional<RolesEntity> findByRole(String role);
}
