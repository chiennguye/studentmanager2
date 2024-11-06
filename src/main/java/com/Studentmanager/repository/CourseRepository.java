package com.Studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Studentmanager.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    long count();

}
