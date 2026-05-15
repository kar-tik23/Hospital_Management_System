package com.DrApp.FirstTry.AppointmentApplication.services;


import com.DrApp.FirstTry.AppointmentApplication.model.user;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.UserReposetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposetry repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}