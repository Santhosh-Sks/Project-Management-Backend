package com.project_manage.projectmanagementsystem.service;

import com.project_manage.projectmanagementsystem.model.User;

public interface UserService {

    User findUserProfileByJwt(String jwt)throws Exception;

    User findUserByEmail(String  email)throws Exception;

    User findUserById(Long UserId)throws Exception;

    User updateUserProjectSize(User user,int number);
}
