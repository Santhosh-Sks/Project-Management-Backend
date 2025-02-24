package com.project_manage.projectmanagementsystem.repository;


import com.project_manage.projectmanagementsystem.model.Project;
import com.project_manage.projectmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByOwner(User user);

     List<Project>findByNameContainingAndTeamContains(String partialName,User user);

     @Query("SELECT p From Project p join p.team t Where t=:user")
    List<Project> findProjectByTeam(@Param("user")User user);

     List<Project> findByTeamContainingOrOwner(User user, User owner);

}
