package com.Studentmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Studentmanager.Model.Teacher;
import com.Studentmanager.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.orElseThrow(() -> new RuntimeException("Teacher not found!!"));
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public List<Teacher> searchTeachers(String keyword) {
        // Kiểm tra xem keyword có rỗng không
        if (keyword == null || keyword.trim().isEmpty()) {
            return teacherRepository.findAll(); // Hoặc trả về danh sách rỗng
        }
        return teacherRepository.findByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(keyword, keyword);
    }

    public long getTotalTeacher() {
        return teacherRepository.count();
    }

}
