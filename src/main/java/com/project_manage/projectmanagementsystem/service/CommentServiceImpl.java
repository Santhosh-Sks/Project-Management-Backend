package com.project_manage.projectmanagementsystem.service;


import com.project_manage.projectmanagementsystem.model.Comment;
import com.project_manage.projectmanagementsystem.model.Issue;
import com.project_manage.projectmanagementsystem.model.User;
import com.project_manage.projectmanagementsystem.repository.CommentRepository;
import com.project_manage.projectmanagementsystem.repository.IssueRepository;
import com.project_manage.projectmanagementsystem.repository.UserRepository;
import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty())
        {
            throw new Exception("Issue not found with id"+issueId);
        }
        if(userOptional.isEmpty())
        {
            throw new Exception("User not found with id"+userId);
        }
        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

         return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty())
        {
            throw new Exception("comment not found with id"+commentId);
        }
        if(userOptional.isEmpty())
        {
            throw new Exception("User not found with id"+userId);
        }
        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }
        else {
            throw new Exception("User does not have permission to delete comment");

        }
    }


    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
