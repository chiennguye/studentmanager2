package com.Studentmanager.service;

import com.Studentmanager.Model.User;
import com.Studentmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Sử dụng PasswordEncoder đã tiêm từ SecurityConfig

    // Đăng ký người dùng mới
    public String register(User user) {
        // Kiểm tra nếu email đã tồn tại
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists !!!"; // Trả về thông báo lỗi
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // Đặt mật khẩu đã mã hóa vào đối tượng User
        userRepository.save(user); // Lưu người dùng vào CSDL

        return "Registed successfully !!!"; // Trả về thông báo thành công
    }

    // Tìm người dùng theo email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Xác thực người dùng khi đăng nhập
    public boolean checkLogin(String rawPassword, String encodedPasswordFromDB) {
        return passwordEncoder.matches(rawPassword, encodedPasswordFromDB);
    }

}
