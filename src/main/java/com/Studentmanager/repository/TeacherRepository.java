package com.Studentmanager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Studentmanager.Model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(String lastname, String firstname);

    long count();

}
