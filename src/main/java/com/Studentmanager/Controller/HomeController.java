package com.Studentmanager.Controller;

import com.Studentmanager.Model.User; // Đảm bảo bạn đã import model User
import com.Studentmanager.repository.UserRepository; // Import UserRepository
import com.Studentmanager.service.CourseService;
import com.Studentmanager.service.StudentService;
import com.Studentmanager.service.TeacherService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/StudentManager")
public class HomeController {

    @Autowired
    private UserRepository userRepository; // Tiêm UserRepository

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/HomePage.html")
    public String home(Model model) {
        // Lấy thông tin người dùng đã đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/StudentManager/login.html"; // Chuyển hướng đến trang đăng nhập
        }
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        } else {
            model.addAttribute("username", "Người dùng không tồn tại"); // Xử lý nếu không tìm thấy
        }

        long totalStudents = studentService.getTotalStudents();
        model.addAttribute("totalStudents", totalStudents);
        long totalTeachers = teacherService.getTotalTeacher();
        model.addAttribute("totalTeachers", totalTeachers);
        long totalCourses = courseService.getTotalSourses();
        model.addAttribute("totalCourses", totalCourses);
        Map<String, Long> gradeClassification = studentService.getStudentCountByClassification();
        model.addAttribute("gradeClassification", gradeClassification);

        return "StudentManager/HomePage"; // Trả về tên file HTML mà bạn muốn hiển thị
    }
}
