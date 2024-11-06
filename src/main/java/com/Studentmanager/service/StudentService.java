
package com.Studentmanager.service;

import com.Studentmanager.Model.Student;
import com.Studentmanager.dto.StudentGradeDTO;
import com.Studentmanager.repository.StudentGradeRepository;
import com.Studentmanager.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    // Lấy thông tin sinh viên theo ID
    public Student getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Cập nhật thông tin sinh viên
    public void updateStudent(Student student) {
        studentRepository.save(student); // Lưu thông tin sinh viên đã cập nhật
    }

    public List<Student> searchStudents(String keyword) {
        // Kiểm tra xem keyword có rỗng không
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentRepository.findAll(); // Hoặc trả về danh sách rỗng
        }
        return studentRepository.findByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(keyword, keyword); // Ví
    }

    public long getTotalStudents() {
        return studentRepository.count();
    }

    public Map<String, Long> getStudentCountByClassification() {
        List<StudentGradeDTO> studentGrades = studentGradeRepository.findAll();
        Map<String, Long> classificationMap = new HashMap<>();

        for (StudentGradeDTO student : studentGrades) {
            String classification = student.getClassification();
            classificationMap.put(classification, classificationMap.getOrDefault(classification, 0L) + 1);
        }
        return classificationMap;
    }
}