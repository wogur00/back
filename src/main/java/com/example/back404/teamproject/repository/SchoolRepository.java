package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    boolean existsBySchoolCode(Integer schoolCode);
    boolean existsBySchoolAdminEmail(String email);
    boolean existsBySchoolAdminUsername(String username);
    
    Optional<School> findBySchoolCode(Integer schoolCode);
    Optional<School> findBySchoolAdminUsername(String username);
    Optional<School> findBySchoolAdminEmail(String email);
}