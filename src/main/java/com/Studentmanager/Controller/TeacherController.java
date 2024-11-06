package com.Studentmanager.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Studentmanager.Model.Teacher;
import com.Studentmanager.Model.User;
import com.Studentmanager.repository.TeacherRepository;
import com.Studentmanager.repository.UserRepository;
import com.Studentmanager.service.TeacherService;

@Controller
@RequestMapping("/StudentManager")
public class TeacherController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("ManagePage/ManageTeacher")
    public String manageTeacher(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("page", "ManageTeacher");
        model.addAttribute("teachers", teachers);
        return "StudentManager/ManagePage/ManageTeacher";
    }

    @GetMapping("ManagePage/FormInput/AddTeacher")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "StudentManager/ManagePage/FormInput/AddTeacher";
    }

    @SuppressWarnings("null")
    @PostMapping("ManagePage/FormInput/AddTeacher")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // lưu giáo viên vào csdl;
            teacherRepository.save(teacher);
            redirectAttributes.addFlashAttribute("successMessage", "Add success !!!");
            return "redirect:/StudentManager/ManagePage/ManageTeacher";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Error: " + ex.getRootCause().getMessage());
            model.addAttribute("teacher", teacher);
            return "StudentManager/ManagePage/FormInput/AddTeacher";

        }

    }

    @PostMapping("ManagePage/DeleteTeacher/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id); // Xóa giáo viên theo ID
        return "redirect:/StudentManager/ManagePage/ManageTeacher"; // Chuyển hướng về trang quản lý giáo viên
    }

    @GetMapping("ManagePage/FormInput/EditTeacher")
    public String showEditStudentForm(@RequestParam("id") Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id); // Lấy thông tin giáo viên theo id

        model.addAttribute("teacher", teacher);
        return "StudentManager/ManagePage/FormInput/EditTeacher"; // Trả về tên view
    }

    @PostMapping("ManagePage/FormInput/UpdateTeacher")
    public String updateStudent(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            // Cập nhật thông tin sinh viên
            teacherRepository.save(teacher);
            redirectAttributes.addFlashAttribute("successMessage", "Teacher updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating Teacher: " + e.getMessage());
        }
        return "redirect:/StudentManager/ManagePage/ManageTeacher";
    }

    // Tìm giáo viên
    @GetMapping("/ManagePage/ManageTeacher/search")
    public String searchStudents(@RequestParam String keyword, Model model) {
        List<Teacher> teachers = teacherService.searchTeachers(keyword);
        model.addAttribute("teachers", teachers);
        return "/StudentManager/ManagePage/ManageTeacher :: teacherTableBody"; // Trả về tên view chính
    }

}
