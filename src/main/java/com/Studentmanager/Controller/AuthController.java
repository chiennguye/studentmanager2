package com.Studentmanager.Controller;

import com.Studentmanager.Model.User;
import com.Studentmanager.reponsitory.UserRepository;
import com.Studentmanager.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/StudentManager")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository; // Tiêm UserRepository

    @GetMapping("/index.html")
    public String showIndexPage() {
        return "StudentManager/index"; // Trả về view index
    }

    // Hiển thị form đăng ký
    @GetMapping("/register.html")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "StudentManager/register"; // Trả về view đăng ký
    }

    // Xử lý đăng ký
    @PostMapping("/register.html")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("Đã gọi postregist ");

        if (result.hasErrors()) {
            return "StudentManager/register"; // Trả lại trang đăng ký cùng với thông báo lỗi
        }

        String message = userService.register(user);
        model.addAttribute("message", message); // Đưa thông báo vào model để hiển thị
        return "StudentManager/register"; // Trả về view đăng ký cùng với thông điệp
    }

    @GetMapping("ManagePage/ManageTeacher.html")
    public String manageTeacher(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        model.addAttribute("page", "ManageTeacher");
        return "StudentManager/ManagePage/ManageTeacher";
    }

    @GetMapping("ManagePage/ManageClass.html")
    public String manageClass(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        model.addAttribute("page", "ManageClass");
        return "StudentManager/ManagePage/ManageClass";
    }

    @GetMapping("ManagePage/ManageGrade.html")
    public String managegrade(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        model.addAttribute("page", "ManageGrade");
        return "StudentManager/ManagePage/ManageGrade";
    }

    @GetMapping("ManagePage/ViewReport.html")
    public String viewreport(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        model.addAttribute("page", "ViewReport");
        return "StudentManager/ManagePage/ViewReport";
    }

    @GetMapping("ManagePage/ManageCourse.html")
    public String manageCourse(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }
        model.addAttribute("page", "ManageCourse");
        return "StudentManager/ManagePage/ManageCourse";
    }

}
