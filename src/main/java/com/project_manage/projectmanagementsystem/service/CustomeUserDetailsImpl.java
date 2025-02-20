package com.project_manage.projectmanagementsystem.service;

import com.project_manage.projectmanagementsystem.model.User;
import com.project_manage.projectmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomeUserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user ==null){
            throw new UsernameNotFoundException("user not found with email" +username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();


        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),authorities);
    }
}
