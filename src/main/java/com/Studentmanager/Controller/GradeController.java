package com.Studentmanager.Controller;

import com.Studentmanager.Model.User;
import com.Studentmanager.dto.StudentGradeDTO;
import com.Studentmanager.repository.GradeRepository;
import com.Studentmanager.repository.UserRepository;
import com.Studentmanager.service.GradeService;
import com.google.appengine.api.search.query.QueryParser.primitive_return;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/StudentManager")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GradeService gradeService;

    @GetMapping("ManagePage/ManageGrade")
    public String showStudentGrades(Model model) {
        // Lấy danh sách tất cả StudentGradeDTO từ repository
        List<StudentGradeDTO> studentGrades = gradeRepository.findAllStudentGrades();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Đây là email người dùng đã đăng nhập

        // Lấy thông tin người dùng từ DB
        User user = userRepository.findByEmail(email); // Tìm người dùng theo email
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm username vào model
        }

        // Thêm danh sách vào model để hiển thị trên view
        model.addAttribute("studentGrades", studentGrades);

        return "StudentManager/ManagePage/ManageGrade"; // Trả về tên view
    }

    @GetMapping("ManagePage/ViewGrade")
    public String showViewGrade(@RequestParam String studentcode, Model model) {
        // Lấy điểm của sinh viên theo studentcode
        List<StudentGradeDTO> studentGradeDTOs = gradeService.getStudentGradesByCode(studentcode);

        // Kiểm tra xem sinh viên có tồn tại không
        if (studentGradeDTOs.isEmpty()) {
            model.addAttribute("studentInfo", null); // Không tìm thấy thông tin sinh viên
        } else {
            // Nếu có thông tin sinh viên, lấy thông tin đầu tiên (trong trường hợp có nhiều
            // bản ghi)
            StudentGradeDTO studentInfo = studentGradeDTOs.get(0);
            model.addAttribute("studentInfo", studentInfo);
        }

        model.addAttribute("studentGradeDTOs", studentGradeDTOs);
        return "StudentManager/ManagePage/ViewGrade";
    }

}
