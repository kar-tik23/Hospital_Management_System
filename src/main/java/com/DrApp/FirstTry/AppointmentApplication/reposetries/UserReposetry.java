package com.DrApp.FirstTry.AppointmentApplication.reposetries;

import com.DrApp.FirstTry.AppointmentApplication.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposetry extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);
}
