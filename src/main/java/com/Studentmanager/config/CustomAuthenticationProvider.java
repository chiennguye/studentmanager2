package com.Studentmanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class); // Đảm bảo đã
                                                                                                      // import Logger
                                                                                                      // và
                                                                                                      // LoggerFactory

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); // Lấy email từ authentication
        String password = authentication.getCredentials().toString();

        logger.info("Attempting to authenticate user with email: {}", email); // Ghi log email

        // Kiểm tra xem email có tồn tại không
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(email);
        } catch (Exception e) {
            logger.warn("Authentication failed: {}", e.getMessage()); // Ghi log cảnh báo
            throw new BadCredentialsException("Sai email"); // Email không tồn tại
        }

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            logger.warn("Authentication failed for email: {} - Incorrect password", email); // Ghi log cảnh báo
            throw new BadCredentialsException("Sai mật khẩu"); // Mật khẩu sai
        }

        logger.info("Authentication successful for email: {}", email); // Ghi log thành công

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                .getSession();
        session.setAttribute("username", userDetails.getUsername()); // Lưu tên người dùng vào session

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
