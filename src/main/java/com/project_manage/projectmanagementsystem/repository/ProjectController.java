package com.project_manage.projectmanagementsystem.repository;


import com.project_manage.projectmanagementsystem.model.Project;
import com.project_manage.projectmanagementsystem.model.User;
import com.project_manage.projectmanagementsystem.service.ProjectService;
import com.project_manage.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public ResponseEntity<List<Project>>getProjects(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization")String jwt
            ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user,category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
