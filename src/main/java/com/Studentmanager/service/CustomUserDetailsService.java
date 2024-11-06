package com.Studentmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Studentmanager.Model.User;
import com.Studentmanager.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Trying to load user by email: {}", email); // Log email

        // Tìm người dùng theo email từ DB
        final User user = userRepository.findByEmail(email); // Gọi đúng phương thức từ repository
        if (user == null) {
            logger.warn("User not found with email: {}", email); // Log cảnh báo nếu không tìm thấy người dùng
            throw new UsernameNotFoundException("Người dùng không tồn tại");
        }

        // Chuyển đổi đối tượng User thành đối tượng UserDetails của Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList() // Không cần quyền (authorities)
        );
    }
}
