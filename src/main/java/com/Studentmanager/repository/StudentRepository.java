package com.Studentmanager.repository;

import com.Studentmanager.Model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
    List<Student> findByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(String lastname, String firstname);

    long count();

}