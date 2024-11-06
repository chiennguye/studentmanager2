package com.Studentmanager.repository;

import com.Studentmanager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Phương thức tìm User theo email
    User findByEmail(String email);
}
