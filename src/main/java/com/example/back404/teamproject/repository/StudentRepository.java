package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByStudentUsername(String studentUsername);
    Optional<Student> findByStudentEmail(String studentEmail);
    boolean existsByStudentUsername(String studentUsername);
    boolean existsByStudentEmail(String studentEmail);
    boolean existsByStudentNumber(String studentNumber);

    Optional<Student> findByStudentNameAndStudentEmailAndStudentNumber(String studentName, String studentEmail, String studentNumber);
    Optional<Student> findByStudentUsernameAndStudentEmailAndStudentNumber(String studentUsername, String studentEmail, String studentNumber);

    @Query("SELECT s FROM Student s WHERE " +
           "(:studentNumber IS NULL OR s.studentNumber LIKE %:studentNumber%) AND " +
           "(:studentName IS NULL OR s.studentName LIKE %:studentName%) AND " +
           "(:studentGrade IS NULL OR s.studentGrade = :studentGrade) AND " +
           "(:studentAffiliation IS NULL OR s.studentAffiliation = :studentAffiliation)")
    List<Student> searchStudents(@Param("studentNumber") String studentNumber,
                                @Param("studentName") String studentName,
                                @Param("studentGrade") String studentGrade,
                                @Param("studentAffiliation") SubjectAffiliation studentAffiliation);
}