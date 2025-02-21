package com.project_manage.projectmanagementsystem.repository;


import lombok.Data;

@Data
public class CreateCommentRequest {

    private Long issueId;

    private String content;

}
