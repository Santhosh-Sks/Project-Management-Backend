package com.project_manage.projectmanagementsystem.repository;

import com.project_manage.projectmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}