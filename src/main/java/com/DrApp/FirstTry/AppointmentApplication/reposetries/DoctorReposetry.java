package com.DrApp.FirstTry.AppointmentApplication.reposetries;

import com.DrApp.FirstTry.AppointmentApplication.model.doctor;
import com.DrApp.FirstTry.AppointmentApplication.model.user;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorReposetry extends JpaRepository<doctor,Long> {

}
