package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    Optional<Teacher> findByTeacherEmail(String email);
    Optional<Teacher> findByTeacherUsername(String username);
    boolean existsByTeacherUsername(String username);
    boolean existsByTeacherEmail(String email);
    
    Optional<Teacher> findByTeacherNameAndTeacherEmailAndTeacherPhoneNumber(String name, String email, String phoneNumber);
    Optional<Teacher> findByTeacherUsernameAndTeacherEmailAndTeacherName(String username, String email, String name);
    
    List<Teacher> findBySchool(School school);
}