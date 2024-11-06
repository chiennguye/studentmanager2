package com.Studentmanager.service;

import com.Studentmanager.Model.Grade;
import com.Studentmanager.dto.StudentGradeDTO;
import com.Studentmanager.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<StudentGradeDTO> getAllStudentGradesWithRank() {
        // Gọi phương thức từ repository để lấy dữ liệu danh sách điểm và xếp hạng
        return gradeRepository.findAllStudentGrades();
    }

    public List<StudentGradeDTO> getStudentGradesByCode(String studentcode) {
        return gradeRepository.findStudentGradesByStudentCode(studentcode);
    }

}
