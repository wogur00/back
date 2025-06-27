package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.CourseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseHistoryRepository extends JpaRepository<CourseHistory, Long> {
}