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

import com.Studentmanager.Model.ClassEntity;
import com.Studentmanager.Model.User;
import com.Studentmanager.repository.ClassRepository;
import com.Studentmanager.repository.UserRepository;
import com.Studentmanager.service.ClassService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/StudentManager")
public class ClassController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassService classService;

    @GetMapping("ManagePage/ManageClass")
    public String manageClass(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        List<ClassEntity> classes = classRepository.findAll();
        model.addAttribute("page", "ManageClass");
        model.addAttribute("classes", classes);
        return "StudentManager/ManagePage/ManageClass";
    }

    @GetMapping("ManagePage/FormInput/AddClass")
    public String showAddClassForm(Model model) {
        model.addAttribute("class", new ClassEntity());
        return "StudentManager/ManagePage/FormInput/AddClass";
    }

    @SuppressWarnings("null")
    @PostMapping("ManagePage/FormInput/AddClass")
    public String addClass(@ModelAttribute("student") ClassEntity classes, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Lưu thông tin sinh viên vào CSDL
            classRepository.save(classes);
            redirectAttributes.addFlashAttribute("successMessage", "Add success !!!"); // Thông báo thành công
            return "redirect:/StudentManager/ManagePage/ManageClass";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Erorr: " + ex.getRootCause().getMessage()); // Thông báo lỗi
            model.addAttribute("class", classes); // Giữ lại thông tin sinh viên đã nhập
            return "StudentManager/ManagePage/FormInput/AddClass"; // Trả về trang thêm sinh viên
        }
    }

    @PostMapping("ManagePage/DeleteClass/{id}")
    public String deleteClass(@PathVariable Long id) {
        classRepository.deleteById(id); // Xóa sinh viên theo ID
        return "redirect:/StudentManager/ManagePage/ManageClass"; // Chuyển hướng về trang quản lý sinh viên
    }

    @GetMapping("ManagePage/FormInput/EditClass")
    public String showEditStudentForm(@RequestParam("id") Long id, Model model) {
        ClassEntity classes = classService.getClassById(id); // Lấy thông tin sinh viên theo id

        model.addAttribute("class", classes);
        return "StudentManager/ManagePage/FormInput/EditClass"; // Trả về tên view
    }

    // Xử lý cập nhật thông tin sinh viên
    @PostMapping("ManagePage/FormInput/UpdateClass")
    public String updateStudent(@ModelAttribute ClassEntity classes, RedirectAttributes redirectAttributes) {
        try {
            // Cập nhật thông tin sinh viên
            classRepository.save(classes);
            redirectAttributes.addFlashAttribute("successMessage", "Class updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating class: " + e.getMessage());
        }
        return "redirect:/StudentManager/ManagePage/ManageClass";
    }

}
