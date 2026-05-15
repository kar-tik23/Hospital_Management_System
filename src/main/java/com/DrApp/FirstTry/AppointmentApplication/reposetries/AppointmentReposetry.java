package com.DrApp.FirstTry.AppointmentApplication.reposetries;

import com.DrApp.FirstTry.AppointmentApplication.model.appointment;
import com.DrApp.FirstTry.AppointmentApplication.model.doctor;
import com.DrApp.FirstTry.AppointmentApplication.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentReposetry extends JpaRepository<appointment,Long> {
    int countByDoctorAndDate(doctor doctor, LocalDate date);
    List<appointment> findByDoctorAndDateOrderByTokenNumber(doctor doctor, LocalDate date);
    List<appointment> findByUserOrderByDateDesc(user user);
    List<appointment> findByDateOrderByDoctorAscTokenNumberAsc(LocalDate date);
    boolean existsByDoctorAndDateAndTime(
            doctor doctor,
            LocalDate date,
            LocalTime time


    );
    List<appointment> findByDoctorAndDateOrderByTokenNumberAsc(
            doctor doctor,
            LocalDate date
    );
    appointment findFirstByDoctorAndDateAndStatusOrderByTokenNumberAsc(
            doctor doctor,
            LocalDate date,
            String status
    );
    List<appointment> findByDoctorAndDateAndStatusOrderByTokenNumberAsc(
            doctor doctor,
            LocalDate date,
            String status
    );
    List<appointment> findByDoctorAndDateAndStatusAndTokenNumberGreaterThanOrderByTokenNumberAsc(
            doctor doctor,
            LocalDate date,
            String status,
            int tokenNumber
    );
}
