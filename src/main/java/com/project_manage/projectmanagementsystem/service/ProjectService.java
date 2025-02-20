package com.project_manage.projectmanagementsystem.service;


import com.project_manage.projectmanagementsystem.model.Chat;
import com.project_manage.projectmanagementsystem.model.Project;
import com.project_manage.projectmanagementsystem.model.User;
import lombok.Data;

import java.util.List;

public interface ProjectService {
    Project createProject(Project projects, User user) throws Exception;

    List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;


    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updatedProject, Long id) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removeFromProject(Long projectId, Long userId) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProjects(String keyword, User user)throws Exception;
}
