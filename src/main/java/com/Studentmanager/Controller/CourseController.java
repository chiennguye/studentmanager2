package com.Studentmanager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.Studentmanager.Model.Course;
import com.Studentmanager.Model.User;
import com.Studentmanager.repository.CourseRepository;
import com.Studentmanager.repository.UserRepository;

@Controller
@RequestMapping("/StudentManager")
public class CourseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("ManagePage/ManageCourse")
    public String manageCourse(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("page", "ManageCourse");
        model.addAttribute("courses", courses);
        return "StudentManager/ManagePage/ManageCourse";
    }

}
