package com.project_manage.projectmanagementsystem.request;

import java.time.LocalDate;


@Data
public class IssueRequest {

    private String title;


    private String description;


    private String status;


    private Long projectID;


    private String priority;


    private LocalDate dueData;


}
