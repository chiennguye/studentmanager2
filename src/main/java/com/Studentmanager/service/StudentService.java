package com.Studentmanager.service;

import com.Studentmanager.Model.Student;
import com.Studentmanager.reponsitory.StudentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

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
                                                                                                                      // dụ
                                                                                                                      // tìm
                                                                                                                      // kiếm
                                                                                                                      // theo
                                                                                                                      // họ
    }

}
