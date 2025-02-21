package com.project_manage.projectmanagementsystem.repository;

import com.project_manage.projectmanagementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    List<Comment> findByIssueId(Long issueId);
}
