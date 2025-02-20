package com.project_manage.projectmanagementsystem.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteRequest {
    private Long projectId;
    private String email;
}
