package com.project_manage.projectmanagementsystem.repository;

import com.project_manage.projectmanagementsystem.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<Chat, Long> {
}
