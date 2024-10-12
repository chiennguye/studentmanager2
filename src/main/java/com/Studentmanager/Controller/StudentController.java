package com.Studentmanager.Controller;

import com.Studentmanager.Model.Student;
import com.Studentmanager.Model.User;
import com.Studentmanager.reponsitory.StudentRepository;
import com.Studentmanager.reponsitory.UserRepository;
import com.Studentmanager.service.StudentService;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/StudentManager")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("ManagePage/ManageStudent.html")
    public String manageStudent(Model model) {
        // Thêm tên người dùng vào mô hình
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }

        // Lấy danh sách học sinh từ CSDL và thêm vào model
        List<Student> students = studentRepository.findAll(); // Lấy danh sách học sinh
        model.addAttribute("page", "ManageStudent"); // Truyền giá trị page để biết trang hiện tại
        model.addAttribute("students", students); // Thêm danh sách học sinh vào model

        return "StudentManager/ManagePage/ManageStudent"; // Trả về tên view
    }

    @GetMapping("/ManagePage/FormInput/AddStudent.html")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student()); // Truyền đối tượng Student mới vào model
        return "StudentManager/ManagePage/FormInput/AddStudent"; // Trả về tên view
    }

    @PostMapping("/ManagePage/FormInput/AddStudent.html")
    public String addStudent(@ModelAttribute("student") Student student, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Lưu thông tin sinh viên vào CSDL
            studentRepository.save(student);
            redirectAttributes.addFlashAttribute("successMessage", "Add success !!!"); // Thông báo thành công
            return "redirect:/StudentManager/ManagePage/ManageStudent.html";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Erorr: " + ex.getRootCause().getMessage()); // Thông báo lỗi
            model.addAttribute("student", student); // Giữ lại thông tin sinh viên đã nhập
            return "StudentManager/ManagePage/FormInput/AddStudent"; // Trả về trang thêm sinh viên
        }
    }

    @PostMapping("/ManagePage/DeleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id); // Xóa sinh viên theo ID
        return "redirect:/StudentManager/ManagePage/ManageStudent.html"; // Chuyển hướng về trang quản lý sinh viên
    }

    // Hiển thị form sửa thông tin sinh viên
    @GetMapping("/ManagePage/FormInput/EditStudent.html")
    public String showEditStudentForm(@RequestParam("id") Long id, Model model) {
        Student student = studentService.getStudentById(id); // Lấy thông tin sinh viên theo id
        model.addAttribute("student", student);
        return "StudentManager/ManagePage/FormInput/EditStudent"; // Trả về tên view
    }

    // Xử lý cập nhật thông tin sinh viên
    @PostMapping("/ManagePage/FormInput/UpdateStudent")
    public String updateStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            // Cập nhật thông tin sinh viên
            studentRepository.save(student);
            redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating student: " + e.getMessage());
        }
        return "redirect:/StudentManager/ManagePage/ManageStudent.html";
    }

    // Tìm kiếm học sinh
    @GetMapping("/ManagePage/ManageStudent/search")
    public String searchStudents(@RequestParam String keyword, Model model) {
        List<Student> students = studentService.searchStudents(keyword);
        model.addAttribute("students", students);
        return "/StudentManager/ManagePage/ManageStudent :: studentTableBody"; // Trả về tên view chính
    }

}
