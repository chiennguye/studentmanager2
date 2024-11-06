package com.Studentmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Studentmanager.dto.StudentGradeDTO;

public interface StudentGradeRepository extends JpaRepository<StudentGradeDTO, Long> {

    List<StudentGradeDTO> findAll(); // Lấy tất cả sinh viên

}
