package com.Studentmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Studentmanager.Model.Grade;
import com.Studentmanager.dto.StudentGradeDTO;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT new com.Studentmanager.dto.StudentGradeDTO(s.studentcode, s.lastname, s.firstname, c.classname, co.coursename, "
            + "COALESCE(sc.midletest, 0) AS midletest, "
            + "COALESCE(sc.finaltest, 0) AS finaltest, "
            + "COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) AS average, "
            + "CASE WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 9 THEN 'Excellent' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 8 THEN 'Good' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 6.5 THEN 'Fair' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 5 THEN 'Satisfactory' "
            + "ELSE 'Poor' END) "
            + "FROM Student s "
            + "LEFT JOIN s.classEntity c "
            + "LEFT JOIN Grade sc ON s.id = sc.student.id "
            + "LEFT JOIN sc.course co "
            + "WHERE s.studentcode = :studentcode")
    List<StudentGradeDTO> findStudentGradesByStudentCode(@Param("studentcode") String studentcode);

    // Phương thức này có thể dùng để lấy tất cả điểm sinh viên
    @Query("SELECT new com.Studentmanager.dto.StudentGradeDTO(s.studentcode, s.lastname, s.firstname, c.classname, co.coursename, "
            + "COALESCE(sc.midletest, 0) AS midletest, "
            + "COALESCE(sc.finaltest, 0) AS finaltest, "
            + "COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) AS average, "
            + "CASE WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 9 THEN 'Excellent' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 8 THEN 'Good' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 6.5 THEN 'Fair' "
            + "WHEN COALESCE((COALESCE(sc.midletest, 0) + COALESCE(sc.finaltest, 0)) / 2, 0) >= 5 THEN 'Satisfactory' "
            + "ELSE 'Poor' END) "
            + "FROM Student s "
            + "LEFT JOIN s.classEntity c "
            + "LEFT JOIN Grade sc ON s.id = sc.student.id "
            + "LEFT JOIN sc.course co")
    List<StudentGradeDTO> findAllStudentGrades();
}
