package com.DrApp.FirstTry.AppointmentApplication.controller;

import com.DrApp.FirstTry.AppointmentApplication.model.user;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.UserReposetry;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AuthController {
    @Autowired
    private UserReposetry repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute user user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");

        repo.save(user);

        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {

        String role = auth.getAuthorities().iterator().next().getAuthority();

        if(role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/user/dashboard";
    }
}
